# OpenCare User Functionality Matrix

This document outlines the core and premium functionalities available to various user roles in the OpenCare platform.

## User Roles

* **Patient**
* **Doctor**
* **Hospital Admin**
* **Social Organization Admin**
* **Diagnostic Center Admin** (optional, derived from Hospital Admin)
* **System Admin (Internal)**

---

## 🔹 Functionality Overview

### ✅ Free Features

### 💎 Premium Features

| Feature                                              | Patient | Doctor | Hospital Admin | Social Org Admin | Notes                                              |
| ---------------------------------------------------- | ------- | ------ | -------------- | ---------------- | -------------------------------------------------- |
| **1. View Doctor Profiles & Reviews**                | ✅       | ✅      | ✅              | ✅                | Public listing                                     |
| **2. Book Doctor Appointments**                      | ✅       |        |                |                  | With calendar & availability                       |
| **3. Video Consultation (via in-app)**               | 💎      | 💎     |                |                  | Requires subscription or usage fee                 |
| **4. Medical Report Storage**                        | ✅       | ✅      | ✅              | ✅                | Patient-controlled access                          |
| **5. Prescription History View**                     | ✅       | ✅      |                |                  | Shows e-prescriptions                              |
| **6. Health Vitals Dashboard**                       | ✅       | ✅      |                |                  | Blood pressure, sugar, etc.                        |
| **7. Doctor Blog Reading**                           | ✅       | ✅      | ✅              | ✅                | Educational content                                |
| **8. Blog Writing (Verified Doctor)**                |         | ✅      |                |                  | Subject to moderation                              |
| **9. Ads Display (Contextual)**                      | ✅       | ✅      | ✅              | ✅                | Based on location                                  |
| **10. Emergency Contact & Location Map**             | ✅       | ✅      | ✅              | ✅                | Nearby hospitals, OTs, ICU                         |
| **11. Hospital Amenity Search (Cabins, OTs)**        | ✅       | ✅      | ✅              | ✅                | Filter by features                                 |
| **12. Patient Contribution Points / Gamification**   | ✅       |        |                |                  | Based on actions (add review, report issue, etc.)  |
| **13. Appointment / Report Notification System**     | ✅       | ✅      | ✅              | ✅                | SMS / email / in-app                               |
| **14. Custom Dashboard Widgets**                     | 💎      | 💎     | 💎             | 💎               | Health insights, usage metrics                     |
| **15. Sponsored Profile Promotion**                  |         | 💎     | 💎             | 💎               | Featured placement on home/search                  |
| **16. Admin Panel for Institution Management**       |         |        | ✅              | ✅                | Add/update facilities                              |
| **17. Dynamic Advertisement Control**                |         |        | 💎             | 💎               | Location-aware ads                                 |
| **18. Online Bill / Donation Collection**            | ✅       |        | ✅              | ✅                | Stripe, SSLCOMMERZ                                 |
| **19. Dynamic Form Submission (Complaint, etc.)**    | ✅       | ✅      | ✅              | ✅                | Contact us, feedback, reports                      |
| **20. Doctor Verification / Licensing System**       |         | ✅      | ✅              | ✅                | Doctor must be verified to access blog, video call |
| **21. Activity Log / Audit Trail**                   |         |        | 💎             | 💎               | View historical actions                            |
| **22. Geographic Insights Dashboard**                |         |        | 💎             | 💎               | Map-based visualization                            |
| **23. Role-Based Notification System**               | ✅       | ✅      | ✅              | ✅                | Custom alerting rules                              |
| **24. Institution Profile Analytics**                |         |        | 💎             | 💎               | Views, interactions, etc.                          |
| **25. Health Campaign Participation**                | ✅       | ✅      | ✅              | ✅                | Event listings, drives                             |
| **26. Appointment/Prescription Sharing with Family** | 💎      |        |                |                  | Private, consent-based                             |

---

## 🔐 Premium Package Ideas

| Package Name     | Target Audience       | Monthly Price (Suggested) | Includes                                    |
| ---------------- | --------------------- | ------------------------- | ------------------------------------------- |
| Patient Plus     | Patients              | \$2                       | Video consult, dashboard, share health data |
| Doctor Pro       | Doctors               | \$5                       | Promote profile, analytics, video call      |
| Hospital Premium | Hospitals/Diagnostics | \$15                      | Advanced dashboard, ads, amenity editing    |
| Org Connect      | Social Organizations  | \$10                      | Ads, profile, analytics, campaigns          |

---

## 📌 Notes

* All sensitive actions are protected with Keycloak auth and RBAC.
* Features should be exposed via feature flags or config-driven dashboard rendering.
* Consider A/B testing features for onboarding new users.

---

Let me know if you'd like this split into separate files (per role), or integrated into your admin config table or CMS.
