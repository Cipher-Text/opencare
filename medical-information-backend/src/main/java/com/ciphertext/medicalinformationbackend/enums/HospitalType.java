package com.ciphertext.medicalinformationbackend.enums;

public enum HospitalType {
    CANCER("ক্যান্সার"),
    CHEST_DISEASE("বক্ষব্যাধি"),
    COLLEGE("কলেজ"),
    COMMUNITY_CLINIC("ইউনিয়ন সাব সেন্টার"),
    DENTAL("ডেন্টাল"),
    DISTRICT("জেলা"),
    EYE("চোখ"),
    INFECTIOUS_DISEASE("সংক্রামক ব্যাধি"),
    KIDNEY("কিডনি"),
    LEPROSY("কুষ্ঠ"),
    MENTAL("মানসিক"),
    MOTHER_AND_CHILD("মা ও শিশু"),
    SPECIALIZED("বিশেষায়িত"),
    TRAUMA("ট্রমা"),
    UNIVERSITY("বিশ্ববিদ্যালয়"),
    UPAZILA("উপজেলা"),
    UNION_SUBCENTER("ইউনিয়ন সাব সেন্টার");

    private final String benglaName;

    HospitalType(String benglaName) {
        this.benglaName = benglaName;
    }

    public String getBenglaName() {
        return benglaName;
    }
}
