package com.ciphertext.medicalinformationbackend.enums;

public enum OrganizationType {
    GOVERNMENT("Government", "সরকারি", "Government Description"),
    MILITARY("Military", "মিলিটারি", "Military Description"),
    PRIVATE("Private", "বেসরকারি", "Private Description");

    private final String name;
    private final String benglaName;
    private final String description;

    OrganizationType(String name, String benglaName, String description) {
        this.name = name;
        this.benglaName = benglaName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getBenglaName() {
        return benglaName;
    }

    public String getDescription() {
        return description;
    }

}
