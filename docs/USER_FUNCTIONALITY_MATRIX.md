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

## 🚦 Implementation Status (April 2026)

This table tracks which features in the matrix above are actually implemented vs. still pending. Keep this updated as features ship.

| # | Feature | Backend | Frontend | Notes |
|---|---------|---------|----------|-------|
| 1 | View Doctor Profiles | ✅ | ✅ | Public listing live |
| 2 | Book Doctor Appointments | 🚧 | ❌ | DB schema complete (`appointment` table); entity/service/API layer not yet built |
| 3 | Video Consultation | ❌ | ❌ | No implementation started |
| 4 | Medical Report Storage | ✅ MinIO | ❌ | File upload/download works; no patient-facing report management UI |
| 5 | Prescription History View | 🚧 | ❌ | Temporary standalone service (`open-care-prescription-management`) handles early use; will be replaced by native feature built directly in main backend/frontend |
| 6 | Health Vitals Dashboard | ⚠️ | ⚠️ | Backend CRUD exists but has open IDOR vulnerability (any user can read any patient's data — see CODE_REVIEW.md security item 1); frontend only fetches latest, 7 endpoints not wired |
| 7 | Doctor Blog Reading | ❌ | ❌ | No blog entity, API, or UI |
| 8 | Blog Writing (Verified Doctor) | ❌ | ❌ | No blog entity, API, or UI |
| 10 | Emergency Contact & Location Map | ✅ Ambulance API | ⚠️ | API exists; map UI not confirmed |
| 11 | Hospital Amenity Search | ✅ | ✅ | Live |
| 12 | Patient Contribution Points | ⚠️ | ❌ | `contribution_points` column exists on Profile; no write logic |
| 13 | Notification System | ❌ | ❌ | No implementation started |
| 18 | Online Bill / Donation Collection | ❌ | ❌ | Payment gateways planned but not implemented |
| 20 | Doctor Verification System | ⚠️ | ❌ | Boolean flag only; no workflow, no document upload, no audit trail |
| 1 (reviews) | Patient Reviews / Quality Ratings | ⚠️ | ❌ | DB schema (`rating` table) exists; no write API, no UI — **not live despite being advertised** |
| 25 | Health Campaign Participation | ⚠️ | ❌ | `campaign` table exists; no entity or API |

**Legend:** ✅ Live · ⚠️ Partially built · 🚧 Schema/service exists, needs integration · ❌ Not started
