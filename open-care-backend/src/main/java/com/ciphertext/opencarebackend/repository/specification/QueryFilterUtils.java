package com.ciphertext.opencarebackend.repository.specification;

import java.util.List;
import java.util.Set;

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

    public static <T> Filter generateMultiJoinTableFilter(Set<MultiJoin> multiJoins,
                                                          QueryOperator queryOperator) {
        return Filter.builder()
                .multiJoins(multiJoins)
                .operator(queryOperator)
                .build();
    }

    public static Filter generateJoinTableInFilter(InJoin inJoin, QueryOperator queryOperator) {
        return Filter.builder()
                .inJoin(inJoin)
                .operator(queryOperator).build();
    }

    public static Filter generateMultiJoinTableInFilter(Set<MultiJoinIn> multiJoinIn,
                                                        QueryOperator queryOperator) {
        return Filter.builder()
                .multiJoinIns(multiJoinIn)
                .operator(queryOperator).build();
    }

    public static <T> Filter generateInFilter(String field, QueryOperator queryOperator,
                                              List<T> itemList) {
        return Filter.builder()
                .field(field)
                .values(itemList)
                .operator(queryOperator)
                .build();
    }

    public static <T> Filter generateNullOrNonNullFilter(String field, QueryOperator queryOperator) {
        return Filter.builder()
                .field(field)
                .operator(queryOperator)
                .build();
    }
}
