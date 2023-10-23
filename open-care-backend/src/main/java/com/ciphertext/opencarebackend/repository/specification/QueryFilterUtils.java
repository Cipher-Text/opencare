package com.ciphertext.opencarebackend.repository.specification;

public class QueryFilterUtils {
    public static <T> Filter generateIndividualFilter(String field, QueryOperator queryOperator, T value) {
        return Filter.builder()
                .field(field)
                .operator(queryOperator)
                .value(value)
                .build();
    }

    public static <T> Filter generateJoinTableFilter(String field, String joinField, QueryOperator queryOperator, T value) {
        return Filter.builder()
                .field(field)
                .joinField(joinField)
                .operator(queryOperator)
                .value(value)
                .build();
    }
}
