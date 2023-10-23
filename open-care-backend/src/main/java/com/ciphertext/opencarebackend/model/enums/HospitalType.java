package com.ciphertext.opencarebackend.model.enums;

public enum HospitalType {
    GENERAL("সাধারণ", "General"),
    CANCER("ক্যান্সার", "Cancer"),
    CHEST_DISEASE("বক্ষব্যাধি", "Chest Disease"),
    COLLEGE("কলেজ", "College"),
    COMMUNITY_CLINIC("ইউনিয়ন সাব সেন্টার", "Community Clinic"),
    DENTAL("ডেন্টাল", "Dental"),
    DISTRICT("জেলা", "District"),
    EYE("চোখ", "Eye"),
    INFECTIOUS_DISEASE("সংক্রামক ব্যাধি", "Infectious Disease"),
    KIDNEY("কিডনি", "Kidney"),
    LEPROSY("কুষ্ঠ", "Leprosy"),
    MENTAL("মানসিক", "Mental"),
    TUBERCULOSIS("যক্ষ্মা", "Tuberculosis"),
    MOTHER_AND_CHILD("মা ও শিশু", "Mother And Child"),
    SPECIALIZED("বিশেষায়িত", "Specialized"),
    TRAUMA("ট্রমা", "Trauma"),
    UNIVERSITY("বিশ্ববিদ্যালয়", "University"),
    UPAZILA("উপজেলা", "Upazila"),
    UNION_SUBCENTER("ইউনিয়ন সাব সেন্টার", "Union Subcenter");

    private final String benglaName;
    private final String englishName;

    HospitalType(String benglaName, String englishName) {
        this.benglaName = benglaName;
        this.englishName = englishName;
    }

    public String getBenglaName() {
        return benglaName;
    }

    public String getEnglishName() {
        return englishName;
    }
}
