package com.ciphertext.opencarebackend.model.enums;

public enum BloodGroup {
    A_POSITIVE("A+", "এ পজিটিভ"),
    A_NEGATIVE("A-", "এ নেগেটিভ"),
    B_POSITIVE("B+", "বি পজিটিভ"),
    B_NEGATIVE("B-", "বি নেগেটিভ"),
    AB_POSITIVE("AB+", "এবি পজিটিভ"),
    AB_NEGATIVE("AB-", "এবি নেগেটিভ"),
    O_POSITIVE("O+", "ও পজিটিভ"),
    O_NEGATIVE("O-", "ও নেগেটিভ");

    private final String shortName;
    private final String banglaName;

    BloodGroup(String shortName, String banglaName) {
        this.shortName = shortName;
        this.banglaName = banglaName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getBanglaName() {
        return banglaName;
    }
}
