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
    JOIN,
    MULTI_JOIN,
    IN_JOIN,
    MULTI_JOIN_IN,
    IS_NULL,
    IS_NOT_NULL

}