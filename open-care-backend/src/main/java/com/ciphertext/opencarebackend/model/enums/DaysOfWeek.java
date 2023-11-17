package com.ciphertext.opencarebackend.model.enums;

public enum DaysOfWeek {
    SUNDAY("রবিবার", "sun"),
    MONDAY("সোমবার", "mon"),
    TUESDAY("মঙ্গলবার", "tue"),
    WEDNESDAY("বুধবার", "wed"),
    THURSDAY("বৃহস্পতিবার", "thu"),
    FRIDAY("শুক্রবার", "fri"),
    SATURDAY("শনিবার", "sat");

    private final String banglaName;
    private final String abbreviatedName;

    private DaysOfWeek(String banglaName, String abbreviatedName) {
        this.banglaName = banglaName;
        this.abbreviatedName = abbreviatedName;
    }

    public String getBanglaName() {
        return banglaName;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }
}
