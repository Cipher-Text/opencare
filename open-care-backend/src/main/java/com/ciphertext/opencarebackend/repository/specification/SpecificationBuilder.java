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

            case IS_NULL -> ((root, query, cb) -> cb.isNull(root.get(input.getField())));

            case IS_NOT_NULL -> ((root, query, cb) -> cb.isNotNull(root.get(input.getField())));

            case JOIN -> (root, query, builder) -> {
                Join<?, ?> join = root.join(input.getJoinField());
                return builder.equal(join.get(input.getField()), input.getValue());
            };

            case LIKE_JOIN -> (root, query, builder) -> {
                Join<?, ?> join = root.join(input.getJoinField());
                return builder.like(join.get(input.getField()), "%" + input.getValue() + "%");
            };

            case MULTI_JOIN -> (root, query, builder) -> {
                Iterator<MultiJoin> iterator = input.getMultiJoins().iterator();
                MultiJoin multiJoin = iterator.next();
                final Join<?, ?>[] join = {root.join(multiJoin.joinTable())};
                final Predicate[] equal = {null};
                if (multiJoin.joinValue() != null) {
                    equal[0] = builder.equal(join[0].get(multiJoin.joinColumn()), multiJoin.joinValue());
                }
                iterator.forEachRemaining(mj -> {
                    join[0] = join[0].join(mj.joinTable());
                    if (mj.joinValue() != null) {
                        equal[0] = builder.equal(join[0].get(mj.joinColumn()), mj.joinValue());
                    }
                });
                return equal[0];
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

            case IN_JOIN -> (root, query, builder) -> {
                Join<?, ?> join = root.join(input.getInJoin().joinTable());
                return builder.in(join.get(input.getInJoin().inColumn())).value(input.getInJoin().values());
            };

            case MULTI_JOIN_IN -> (root, query, builder) -> {
                Iterator<MultiJoinIn> iterator = input.getMultiJoinIns().iterator();
                MultiJoinIn multiJoinIn = iterator.next();
                final Join<?, ?>[] join = {root.join(multiJoinIn.joinTable())};
                final Predicate[] equal = {null};
                if (multiJoinIn.joinValues() != null) {
                    equal[0] = builder.in(join[0].get(multiJoinIn.inColumn())).value(multiJoinIn.joinValues());
//          equal[0] = builder.in(join[0].get(input.getInJoin().inColumn())).value(input.getInJoin().values());
//              builder.in(join[0].get(multiJoinIn.joinColumn()), multiJoinIn.joinValues());
                }
                iterator.forEachRemaining(mj -> {
                    join[0] = join[0].join(mj.joinTable());
                    if (mj.joinValues() != null) {
                        equal[0] = builder.in(join[0].get(mj.inColumn())).value(mj.joinValues());
//            equal[0] = builder.equal(join[0].get(mj.joinColumn()), mj.joinValues());
                    }
                });
                return equal[0];
            };

        };
    }
}