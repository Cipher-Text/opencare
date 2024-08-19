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

    public Gender fromString(String value) {
        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided string doesn't match any enum constant
            return null; // or throw an exception or return a default value
        }
    }

    public String fromEnum(Gender gender){
        return gender.name();
    }
}
