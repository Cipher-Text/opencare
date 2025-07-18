# 🏥 Open Care Project - UI/UX Page Structure & Data Format

## 📱 **Page Tree Structure**

### **🔐 Authentication Module**
```
auth/
├── login
│   ├── Content: Login form, forgot password link, user type toggle
│   └── Data: { email, password, userType, rememberMe }
├── register
│   ├── Content: Multi-step form, user type selection, verification
│   └── Data: { userType, basicInfo, verification, profileSetup }
├── forgot-password
│   ├── Content: Email input, reset instructions
│   └── Data: { email, resetToken }
└── verify-account
    ├── Content: OTP input, resend option
    └── Data: { otp, userId, verificationType }
```

### **🏠 Public Pages**
```
public/
├── home
│   ├── Content: Hero banner, features, stats, testimonials, CTA
│   └── Data: { searchQuery, featuredDoctors, hospitalCount, patientCount }
├── about
│   ├── Content: Mission, vision, team, achievements
│   └── Data: { teamMembers, achievements, milestones }
├── contact
│   ├── Content: Contact form, address, phone, email
│   └── Data: { name, email, message, subject }
└── privacy-policy
    ├── Content: Policy text, last updated date
    └── Data: { policyVersion, lastUpdated }
```

### **🔍 Search & Directory**
```
directory/
├── search
│   ├── Content: Search bar, filters, results grid/list
│   └── Data: { query, filters, results, pagination }
├── doctors/
│   ├── list
│   │   ├── Content: Doctor cards, filters, pagination, map toggle
│   │   └── Data: { doctors[], filters, pagination, location }
│   ├── view/:id
│   │   ├── Content: Profile, schedule, reviews, book appointment
│   │   └── Data: { doctor, schedule, reviews[], availability }
│   └── edit/:id (Doctor only)
│       ├── Content: Profile form, schedule editor, document upload
│       └── Data: { profile, schedule, documents, specializations }
├── hospitals/
│   ├── list
│   │   ├── Content: Hospital cards, location filter, amenities
│   │   └── Data: { hospitals[], filters, locations, amenities }
│   ├── view/:id
│   │   ├── Content: Details, departments, doctors, reviews, tests
│   │   └── Data: { hospital, departments[], doctors[], tests[], reviews[] }
│   └── edit/:id (Hospital Admin only)
│       ├── Content: Info form, department management, doctor assignment
│       └── Data: { hospitalInfo, departments[], assignedDoctors[] }
└── institutions/
    ├── list
    │   ├── Content: Institution cards, type filter, location
    │   └── Data: { institutions[], types, locations }
    └── view/:id
        ├── Content: Details, services, affiliated doctors
        └── Data: { institution, services[], affiliatedDoctors[] }
```

### **👨‍⚕️ Doctor Dashboard**
```
doctor/
├── dashboard
│   ├── Content: Stats cards, upcoming appointments, earnings, notifications
│   └── Data: { stats, appointments[], earnings, notifications[] }
├── appointments/
│   ├── list
│   │   ├── Content: Appointment table, filters, status toggle
│   │   └── Data: { appointments[], filters, dateRange }
│   ├── view/:id
│   │   ├── Content: Patient details, appointment history, notes
│   │   └── Data: { appointment, patient, history[], notes }
│   └── schedule
│       ├── Content: Calendar view, time slot editor, availability toggle
│       └── Data: { schedule, timeSlots[], availability, holidays[] }
├── patients/
│   ├── list
│   │   ├── Content: Patient table, search, recent visits
│   │   └── Data: { patients[], searchQuery, recentVisits[] }
│   └── view/:id
│       ├── Content: Patient profile, medical history, prescriptions
│       └── Data: { patient, medicalHistory[], prescriptions[] }
├── prescriptions/
│   ├── create
│   │   ├── Content: Medicine search, dosage form, instructions
│   │   └── Data: { patientId, medicines[], dosages[], instructions }
│   └── list
│       ├── Content: Prescription history, patient filter
│       └── Data: { prescriptions[], filters, patients[] }
├── earnings
│   ├── Content: Revenue charts, payment history, tax documents
│   └── Data: { earnings, paymentHistory[], taxDocuments[] }
├── blog/
│   ├── create
│   │   ├── Content: Rich text editor, image upload, SEO fields
│   │   └── Data: { title, content, images[], tags[], seoData }
│   ├── list
│   │   ├── Content: Article list, status filter, analytics
│   │   └── Data: { articles[], filters, analytics }
│   └── edit/:id
│       ├── Content: Edit form, preview, publish settings
│       └── Data: { article, publishSettings, previewMode }
└── profile
    ├── Content: Personal info, qualifications, schedule, documents
    └── Data: { personalInfo, qualifications[], schedule, documents[] }
```

### **🧑‍💼 Patient Dashboard**
```
patient/
├── dashboard
│   ├── Content: Health overview, upcoming appointments, recent reports
│   └── Data: { healthStats, appointments[], recentReports[] }
├── appointments/
│   ├── list
│   │   ├── Content: Appointment history, upcoming, canceled
│   │   └── Data: { appointments[], filters, status }
│   ├── book
│   │   ├── Content: Doctor selection, time slot, payment
│   │   └── Data: { selectedDoctor, timeSlot, paymentMethod }
│   └── view/:id
│       ├── Content: Appointment details, prescription, notes
│       └── Data: { appointment, prescription, doctorNotes }
├── health-records/
│   ├── list
│   │   ├── Content: Document timeline, category filter, search
│   │   └── Data: { documents[], categories, timeline }
│   ├── upload
│   │   ├── Content: File upload, category selection, notes
│   │   └── Data: { files[], category, notes, tags[] }
│   └── view/:id
│       ├── Content: Document viewer, sharing options, history
│       └── Data: { document, shareSettings, viewHistory[] }
├── prescriptions/
│   ├── list
│   │   ├── Content: Prescription history, active medications
│   │   └── Data: { prescriptions[], activeMedications[] }
│   └── view/:id
│       ├── Content: Prescription details, medication reminders
│       └── Data: { prescription, medications[], reminders[] }
├── following/
│   ├── doctors
│   │   ├── Content: Followed doctors, activity feed
│   │   └── Data: { followedDoctors[], activityFeed[] }
│   └── hospitals
│       ├── Content: Followed hospitals, updates
│       └── Data: { followedHospitals[], updates[] }
└── profile
    ├── Content: Personal info, emergency contacts, insurance
    └── Data: { personalInfo, emergencyContacts[], insurance }
```

### **🏥 Hospital Admin Dashboard**
```
hospital-admin/
├── dashboard
│   ├── Content: Revenue stats, appointment stats, staff overview
│   └── Data: { revenue, appointments, staff[], reports }
├── appointments/
│   ├── list
│   │   ├── Content: All appointments, doctor filter, status
│   │   └── Data: { appointments[], doctors[], filters }
│   └── analytics
│       ├── Content: Appointment trends, doctor performance
│       └── Data: { trends, doctorPerformance[], analytics }
├── staff/
│   ├── list
│   │   ├── Content: Staff directory, role filter, status
│   │   └── Data: { staff[], roles[], departments[] }
│   ├── add
│   │   ├── Content: Staff registration form, role assignment
│   │   └── Data: { staffInfo, role, department, permissions[] }
│   └── edit/:id
│       ├── Content: Staff profile editor, role management
│       └── Data: { staff, roles[], permissions[] }
├── departments/
│   ├── list
│   │   ├── Content: Department cards, doctor count, services
│   │   └── Data: { departments[], doctorCounts, services[] }
│   └── manage/:id
│       ├── Content: Department details, assigned doctors, services
│       └── Data: { department, assignedDoctors[], services[] }
├── reports
│   ├── Content: Financial reports, patient reports, export options
│   └── Data: { financialReports[], patientReports[], exportOptions }
└── settings
    ├── Content: Hospital info, billing settings, notifications
    └── Data: { hospitalInfo, billingSettings, notificationSettings }
```

### **💰 Fundraising Module**
```
fundraising/
├── campaigns/
│   ├── list
│   │   ├── Content: Campaign cards, category filter, search
│   │   └── Data: { campaigns[], categories, filters }
│   ├── view/:id
│   │   ├── Content: Campaign details, progress, donor wall, updates
│   │   └── Data: { campaign, donations[], updates[], donors[] }
│   ├── create
│   │   ├── Content: Multi-step form, document upload, story editor
│   │   └── Data: { patientInfo, medicalDocs[], story, goalAmount }
│   └── edit/:id
│       ├── Content: Campaign editor, update posting, document management
│       └── Data: { campaign, updates[], documents[] }
├── donate/:campaignId
│   ├── Content: Donation form, amount selection, payment methods
│   └── Data: { amount, paymentMethod, donorInfo, message }
├── my-campaigns (Patient only)
│   ├── Content: User's campaigns, status, analytics
│   └── Data: { campaigns[], analytics, donations[] }
└── admin/
    ├── pending-campaigns
    │   ├── Content: Verification queue, document review, approval
    │   └── Data: { pendingCampaigns[], verificationStatus }
    └── manage-campaigns
        ├── Content: All campaigns, status management, reports
        └── Data: { allCampaigns[], statusOptions, reports[] }
```

### **🩺 Medical Services**
```
services/
├── tests/
│   ├── list
│   │   ├── Content: Test categories, price comparison, availability
│   │   └── Data: { tests[], categories, priceComparison[], availability }
│   ├── view/:id
│   │   ├── Content: Test details, preparation, book test
│   │   └── Data: { test, preparation, availability, pricing[] }
│   └── book/:id
│       ├── Content: Booking form, home collection option, payment
│       └── Data: { testId, collectionAddress, timeSlot, payment }
├── ambulance/
│   ├── request
│   │   ├── Content: Emergency form, location picker, contact info
│   │   └── Data: { emergencyType, location, contactInfo, notes }
│   └── track/:id
│       ├── Content: Real-time tracking, ETA, driver contact
│       └── Data: { booking, driverInfo, location, eta }
└── pharmacy/
    ├── search
    │   ├── Content: Medicine search, availability, price comparison
    │   └── Data: { medicines[], availability[], prices[] }
    └── order
        ├── Content: Cart, prescription upload, delivery address
        └── Data: { cartItems[], prescription, deliveryAddress }
```

### **💬 Communication**
```
communication/
├── chat/
│   ├── list
│   │   ├── Content: Chat list, online status, recent messages
│   │   └── Data: { conversations[], onlineUsers[], recentMessages[] }
│   └── view/:id
│       ├── Content: Message history, file sharing, voice messages
│       └── Data: { messages[], participants[], attachments[] }
├── video-call/
│   ├── lobby/:id
│   │   ├── Content: Pre-call setup, equipment test, waiting room
│   │   └── Data: { callInfo, participants[], equipmentStatus }
│   └── room/:id
│       ├── Content: Video interface, screen share, chat, recording
│       └── Data: { callSession, participants[], chatMessages[] }
└── notifications/
    ├── list
    │   ├── Content: Notification feed, mark as read, filters
    │   └── Data: { notifications[], filters, unreadCount }
    └── settings
        ├── Content: Notification preferences, channels
        └── Data: { preferences, channels[], schedules[] }
```

### **📊 Analytics & Reports**
```
analytics/
├── dashboard
│   ├── Content: Key metrics, charts, trends
│   └── Data: { metrics, charts[], trends, dateRange }
├── reports/
│   ├── appointments
│   │   ├── Content: Appointment analytics, doctor performance
│   │   └── Data: { appointmentData[], doctorPerformance[], trends }
│   ├── revenue
│   │   ├── Content: Revenue charts, payment analysis
│   │   └── Data: { revenueData[], paymentMethods[], trends }
│   └── users
│       ├── Content: User growth, engagement metrics
│       └── Data: { userGrowth[], engagement[], demographics }
└── export
    ├── Content: Export options, date range, format selection
    └── Data: { exportOptions[], dateRange, formats[] }
```

### **⚙️ Admin Panel**
```
admin/
├── dashboard
│   ├── Content: System stats, user activity, alerts
│   └── Data: { systemStats, userActivity[], alerts[] }
├── users/
│   ├── list
│   │   ├── Content: User table, role filter, status, actions
│   │   └── Data: { users[], roles[], filters, actions[] }
│   ├── view/:id
│   │   ├── Content: User profile, activity history, permissions
│   │   └── Data: { user, activityHistory[], permissions[] }
│   └── roles
│       ├── Content: Role management, permissions matrix
│       └── Data: { roles[], permissions[], matrix }
├── content/
│   ├── doctors
│   │   ├── Content: Doctor approval queue, verification status
│   │   └── Data: { pendingDoctors[], verificationData[] }
│   ├── hospitals
│   │   ├── Content: Hospital management, approval workflow
│   │   └── Data: { hospitals[], approvalStatus[] }
│   └── blogs
│       ├── Content: Blog moderation, featured content
│       └── Data: { blogs[], moderationQueue[], featuredContent[] }
├── system/
│   ├── settings
│   │   ├── Content: System configuration, feature toggles
│   │   └── Data: { systemConfig, featureFlags[], settings }
│   ├── advertisements
│   │   ├── Content: Ad management, placement settings, analytics
│   │   └── Data: { ads[], placements[], analytics, pricing[] }
│   └── payments
│       ├── Content: Payment settings, gateway configuration
│       └── Data: { paymentGateways[], settings, transactionFees[] }
└── reports
    ├── Content: System reports, user analytics, revenue
    └── Data: { systemReports[], userAnalytics[], revenue[] }
```

---

## 🎯 **Priority Implementation Order**

### **Phase 1: Core Foundation (Weeks 1-8)**
1. **auth/** - Complete authentication flow
2. **public/** - Landing pages and basic info
3. **directory/doctors/** - Doctor listing and profiles
4. **directory/hospitals/** - Hospital directory
5. **patient/dashboard** - Basic patient dashboard
6. **doctor/dashboard** - Basic doctor dashboard

### **Phase 2: Essential Features (Weeks 9-16)**
7. **patient/appointments/** - Appointment booking system
8. **doctor/appointments/** - Doctor appointment management
9. **patient/health-records/** - Document management
10. **doctor/prescriptions/** - Prescription system
11. **directory/search** - Advanced search functionality
12. **hospital-admin/dashboard** - Hospital admin basics

### **Phase 3: Advanced Features (Weeks 17-24)**
13. **fundraising/** - Complete fundraising module
14. **services/tests/** - Medical test booking
15. **communication/chat/** - Basic messaging
16. **doctor/blog/** - Blogging system
17. **analytics/dashboard** - Basic analytics
18. **admin/users/** - User management

### **Phase 4: Enhancement (Weeks 25-32)**
19. **communication/video-call/** - Video consultation
20. **services/ambulance/** - Emergency services
21. **services/pharmacy/** - Medicine ordering
22. **analytics/reports/** - Advanced reporting
23. **admin/system/** - System administration
24. **Mobile optimization** - All pages mobile-ready

---

## 🚨 **Critical Missing Elements**

### **Security & Compliance**
- **HIPAA compliance page** - Medical data handling policies
- **Audit logs** - Track all medical data access
- **Two-factor authentication** - Enhanced security for medical professionals
- **Session management** - Secure session handling
- **Data encryption status** - Show encryption indicators

### **Emergency Features**
- **Emergency contacts** - Quick access to emergency services
- **Medical alerts** - Critical health condition alerts
- **Emergency profile** - Essential medical info for emergencies
- **Panic button** - Quick emergency assistance

### **Legal & Regulatory**
- **Terms of service** - Legal compliance page
- **Medical disclaimers** - Liability disclaimers
- **Consent forms** - Digital consent management
- **License verification** - Doctor/hospital license display
- **Insurance integration** - Health insurance information

### **Accessibility**
- **Accessibility settings** - Font size, contrast, voice commands
- **Multi-language support** - Bengali/English toggle
- **Voice navigation** - For visually impaired users
- **Screen reader optimization** - Proper ARIA labels

### **Integration Points**
- **API documentation** - For third-party integrations
- **Webhook management** - Real-time data sync
- **Export/Import** - Data portability
- **Backup/Restore** - Data recovery options
- **Third-party SSO** - Google, Facebook login

---

## 📱 **Mobile-Specific Pages**

### **Quick Actions**
- **Emergency call** - Direct emergency services
- **Quick appointment** - Rapid booking
- **Medicine reminder** - Medication alerts
- **Health tracking** - Vital signs logging
- **Nearby services** - Location-based services

### **Offline Capabilities**
- **Offline appointments** - View without internet
- **Cached prescriptions** - Access without connection
- **Emergency contacts** - Always accessible
- **Health records** - Critical info offline

---

## 🔄 **Data Flow Connections**

### **User Journey Flows**
```
Patient Flow:
home → register → dashboard → doctor/list → doctor/view → appointments/book → prescriptions/view

Doctor Flow:
home → register → dashboard → appointments/list → patients/view → prescriptions/create

Hospital Flow:
home → register → dashboard → staff/list → appointments/analytics → reports

Admin Flow:
admin/dashboard → users/list → content/doctors → system/settings
```

### **Data Synchronization**
- **Real-time updates** - Appointment status, chat messages
- **Batch updates** - Analytics, reports
- **Conflict resolution** - Multiple edit scenarios
- **Cache invalidation** - Ensure data freshness

This structure provides a clear page hierarchy with content requirements and data structures for each page, making it much easier for developers to understand the scope and implementation requirements.