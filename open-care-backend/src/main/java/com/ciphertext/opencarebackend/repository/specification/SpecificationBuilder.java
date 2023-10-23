package com.ciphertext.opencarebackend.repository.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.Iterator;

@NoArgsConstructor
public class SpecificationBuilder {

    public static <T> Specification<T> createSpecification(Filter input) {
        if (input == null) {
            return null;
        }

        return switch (input.getOperator()) {
            case EQUALS -> ((root, query, cb) -> cb.equal(root.get(input.getField()), input.getValue()));

            case JOIN -> (root, query, builder) -> {
                Join<?, ?> join = root.join(input.getJoinField());
                return builder.equal(join.get(input.getField()), input.getValue());
            };

            case LIKE_JOIN -> (root, query, builder) -> {
                Join<?, ?> join = root.join(input.getJoinField());
                return builder.like(join.get(input.getField()), "%" + input.getValue() + "%");
            };

            case NOT_EQUALS ->
                    (root, query, cb) -> cb.notEqual(root.get(input.getField()), input.getValue());

            case GREATER_THAN_EQUALS ->
                    (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(input.getField()),
                            (Date) input.getValue());

            case LESS_THAN_EQUALS -> (root, query, cb) -> cb.lessThanOrEqualTo(root.get(input.getField()),
                    (Date) input.getValue());

            case RANGE -> (root, query, cb) -> {
                Date startOfDay = (Date) input.getValue();
                Date endOfDay = (Date) input.getRangeSecondValue();
                return cb.between(root.get(input.getField()), startOfDay, endOfDay);
            };

            case LIKE -> (root, query, cb) ->
                    cb.like(root.get(input.getField()), "%" + input.getValue() + "%");

            case IN -> (root, query, cb) ->
                    cb.in(root.get(input.getField())).value(input.getValues());

        };
    }
}