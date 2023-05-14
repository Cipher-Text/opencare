package com.ciphertext.medicalinformationbackend.enums;

public enum DaysOfWeek {
    SUNDAY("রবিবার", "sun"),
    MONDAY("সোমবার", "mon"),
    TUESDAY("মঙ্গলবার", "tue"),
    WEDNESDAY("বুধবার", "wed"),
    THURSDAY("বৃহস্পতিবার", "thu"),
    FRIDAY("শুক্রবার", "fri"),
    SATURDAY("শনিবার", "sat");

    private final String benglaName;
    private final String abbreviatedName;

    private DaysOfWeek(String benglaName, String abbreviatedName) {
        this.benglaName = benglaName;
        this.abbreviatedName = abbreviatedName;
    }

    public String getBenglaName() {
        return benglaName;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }
}
