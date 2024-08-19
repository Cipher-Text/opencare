package com.ciphertext.opencarebackend.model.enums;

/**
 * @author sadman @Date ৩/৬/২৩
 */
public enum TeacherPosition {
    PROFESSOR("অধ্যাপক"),
    ASSOCIATE_PROFESSOR("সহযোগী অধ্যাপক"),
    ASSISTANT_PROFESSOR("সহকারী অধ্যাপক"),
    LECTURER("প্রভাষক");

    private final String banglaName;

    TeacherPosition(String banglaName) {
        this.banglaName = banglaName;
    }

    public String getBanglaName() {
        return banglaName;
    }
}
