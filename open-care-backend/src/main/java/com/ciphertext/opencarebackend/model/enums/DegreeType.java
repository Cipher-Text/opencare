package com.ciphertext.opencarebackend.model.enums;

public enum DegreeType {
    UNDERGRADUATE("অস্নাতক"),
    GRADUATE("স্নাতক"),
    POSTGRADUATE("স্নাতকোত্তর");

    private final String banglaName;

    DegreeType(String banglaName) {
        this.banglaName = banglaName;
    }

    public String getBanglaName() {
        return banglaName;
    }
}
