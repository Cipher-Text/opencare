package com.ciphertext.opencarebackend.model.enums;

public enum SocialOrganizationType {
    NGO("Non-Governmental Organization (NGO)", "এনজিও"),
    NPO("Non-Profit Organization", "এনপিও"),
    CO("Charity Organization", "চ্যারিটি অর্গানাইজেশন"),
    CBO("Community-Based Organization (CBO)", "কমিউনিটি-ভিত্তিক অর্গানাইজেশন"),
    FBO("Faith-Based Organization (FBO)", "ধর্ম-ভিত্তিক অর্গানাইজেশন"),
    INGO("International Non-Governmental Organization (INGO)", "আন্তর্জাতিক অন-সরকারি সংগঠন"),
    GHA("Government Health Agency", "সরকারি স্বাস্থ্য সংস্থা"),
    RI("Research Institution", "গবেষণা প্রতিষ্ঠান"),
    MF("Medical Foundation", "মেডিকেল ফাউন্ডেশন"),
    HC("Healthcare Cooperative", "হেলথকেয়ার কোঅপারেটিভ"),
    PA("Professional Association", "পেশাদার সংঘ"),
    AG("Advocacy Group", "প্রচারণা গ্রুপ"),
    RO("Relief Organization", "রিলিফ অর্গানাইজেশন"),
    VHO("Voluntary Health Organization", "স্বেচ্ছাসেবী স্বাস্থ্য সংস্থা"),
    PF("Philanthropic Foundation", "ফিল্যান্থ্রপিক ফাউন্ডেশন"),
    PPP("Public-Private Partnership (PPP)", "পাবলিক-প্রাইভেট পার্টনারশিপ"),
    SE("Social Enterprise", "সোশ্যাল এন্টারপ্রাইজ"),
    MS("Medical Society", "মেডিকেল সোসাইটি"),
    HIP("Health Insurance Provider", "হেলথ ইন্সুরেন্স প্রোভাইডার"),
    CHN("Clinic or Hospital Network", "ক্লিনিক অথবা হাসপাতাল নেটওয়ার্ক"),
    TP("Telemedicine Provider", "টেলিমেডিসিন প্রোভাইডার"),
    HEO("Health Education Organization", "হেলথ এজুকেশন অর্গানাইজেশন"),
    DRO("Disaster Response Organization", "দুর্যোগ প্রতিক্রিয়া অর্গানাইজেশন"),
    MHS("Mental Health Support Group", "মেন্টাল হেলথ সাপোর্ট গ্রুপ"),
    RC("Rehabilitation Center", "রিহ্যাবিলিটেশন সেন্টার"),
    MMO("Medical Mission Organization", "মেডিকেল মিশন অর্গানাইজেশন"),
    HAC("Health Advocacy Coalition", "হেলথ এডভোকেসি কোলিশন"),
    PSO("Patient Support Organization", "পেশেন্ট সাপোর্ট অর্গানাইজেশন"),
    EHO("Environmental Health Organization", "এনভায়রনমেন্টাল হেলথ অর্গানাইজেশন"),
    GHI("Global Health Initiative", "গ্লোবাল হেলথ ইনিশিয়েটিভ"),
    OTHER("Other", "অন্যান্য");

    private String name;
    private String banglaName;

    SocialOrganizationType(String name, String banglaName) {
        this.name = name;
        this.banglaName = banglaName;
    }

    public String getName() {
        return name;
    }

    public String getBanglaName() {
        return banglaName;
    }
}
