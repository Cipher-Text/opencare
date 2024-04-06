package com.ciphertext.opencarebackend.repository.specification;

import java.util.List;

public record MultiJoinIn<T>(String joinColumn, String joinTable, String inColumn, List<T> joinValues) {

}
