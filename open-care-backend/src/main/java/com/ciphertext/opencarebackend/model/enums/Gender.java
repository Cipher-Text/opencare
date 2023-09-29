package com.ciphertext.opencarebackend.model.enums;

public enum Gender {
    MALE("পুরুষ"),
    FEMALE("মহিলা"),
    OTHERS("অন্যান্য");

    private final String benglaName;

    Gender(String benglaName) {
        this.benglaName = benglaName;
    }

    public String getBenglaName() {
        return benglaName;
    }
}
