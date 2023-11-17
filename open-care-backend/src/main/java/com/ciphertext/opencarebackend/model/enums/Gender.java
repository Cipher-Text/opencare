package com.ciphertext.opencarebackend.model.enums;

public enum Gender {
    MALE("পুরুষ"),
    FEMALE("মহিলা"),
    OTHERS("অন্যান্য");

    private final String banglaName;

    Gender(String banglaName) {
        this.banglaName = banglaName;
    }

    public String getBanglaName() {
        return banglaName;
    }
}
