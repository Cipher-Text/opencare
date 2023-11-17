package com.ciphertext.opencarebackend.model.enums;

public enum OrganizationType {
    GOVERNMENT("Government", "সরকারি", "Government Description"),
    MILITARY("Military", "মিলিটারি", "Military Description"),
    PRIVATE("Private", "বেসরকারি", "Private Description");

    private final String name;
    private final String banglaName;
    private final String description;

    OrganizationType(String name, String banglaName, String description) {
        this.name = name;
        this.banglaName = banglaName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getBanglaName() {
        return banglaName;
    }

    public String getDescription() {
        return description;
    }

}
