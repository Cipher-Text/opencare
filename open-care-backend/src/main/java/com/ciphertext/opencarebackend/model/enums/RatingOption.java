package com.ciphertext.opencarebackend.model.enums;

public enum RatingOption {
    CLEAR_EXPLANATION("Doctor", "", ""),
    TIME_FOR_PATIENTS("DOCTOR", "", ""),
    ATTENTIVE_LISTEN("DOCTOR", "", ""),
    FRIENDLY_BEHAVIOR("DOCTOR", "", ""),
    CLEANLINESS("HOSPITAL", "", ""),
    STUFF_BEHAVIOR("HOSPITAL", "", ""),
    OTHER_FACILITIES("HOSPITAL", "", ""),
    MEDICAL_TEST_FACILITIES("HOSPITAL", "", "");

    RatingOption(String sourceName, String description, String banglaDescription) {
        this.sourceName = sourceName;
        this.description = description;
        this.banglaDescription = banglaDescription;
    }

    private final String sourceName;
    private final String description;
    private final String banglaDescription;

    public String getSourceName() {
        return sourceName;
    }

    public String getDescription() {
        return description;
    }

    public String getBanglaDescription() {
        return banglaDescription;
    }

}
