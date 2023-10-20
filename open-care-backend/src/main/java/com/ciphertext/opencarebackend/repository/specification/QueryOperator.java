package com.ciphertext.opencarebackend.repository.specification;

import lombok.Getter;

@Getter
public enum QueryOperator {

    GREATER_THAN_EQUALS,
    LESS_THAN_EQUALS,
    RANGE,
    EQUALS,
    LIKE,
    LIKE_JOIN,
    NOT_EQUALS,
    IN,
    JOIN

}