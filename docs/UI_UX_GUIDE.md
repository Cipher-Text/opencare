# 🏥 Open Care Backend - UI/UX Page Structure & Data Format

## 📱 **Page Tree Structure (Aligned with Backend Entities)**

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

### **🔍 Search & Directory (Based on Backend Entities)**
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
├── institutions/
│   ├── list
│   │   ├── Content: Institution cards, type filter, location
│   │   └── Data: { institutions[], types, locations }
│   ├── view/:id
│   │   ├── Content: Details, services, affiliated doctors
│   │   └── Data: { institution, services[], affiliatedDoctors[] }
│   └── edit/:id (Institution Admin only)
│       ├── Content: Info form, department management, doctor assignment
│       └── Data: { institutionInfo, departments[], assignedDoctors[] }
├── social-organizations/
│   ├── list
│   │   ├── Content: Organization cards, type filter (NGO, CHARITY, CLUB), location filter, affiliation status
│   │   └── Data: { organizations[], types[], locations[], affiliationFilter, searchQuery }
│   ├── view/:id
│   │   ├── Content: Organization profile, mission, projects, contact info, social links, affiliated campaigns
│   │   └── Data: { organization, projects[], campaigns[], contactInfo, socialMedia }
│   └── edit/:id (Organization Admin only)
│       ├── Content: Profile form, social links, project management, affiliation request
│       └── Data: { organizationInfo, socialLinks, projects[], affiliationStatus }
└── associations/
    ├── list
    │   ├── Content: Association cards, type filter (MEDICAL, RESEARCH, EDUCATIONAL), domain filter, location
    │   └── Data: { associations[], types[], domains[], locations[], searchQuery }
    ├── view/:id
    │   ├── Content: Association profile, members, activities, contact info, social links
    │   └── Data: { association, members[], activities[], contactInfo, socialMedia }
    └── edit/:id (Association Admin only)
        ├── Content: Profile form, member management, activity planning
        └── Data: { associationInfo, members[], activities[], settings }
```

### **👨‍⚕️ Doctor Dashboard (Based on Doctor Entity)**
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
├── associations/
│   ├── list
│   │   ├── Content: Association memberships, roles, activities
│   │   └── Data: { associations[], roles[], activities[] }
│   └── manage/:id
│       ├── Content: Association role management, activity participation
│       └── Data: { association, role, activities[], participation }
├── degrees/
│   ├── list
│   │   ├── Content: Academic degrees, certifications, verification status
│   │   └── Data: { degrees[], certifications[], verificationStatus }
│   └── add
│       ├── Content: Degree form, document upload, verification
│       └── Data: { degreeInfo, documents[], verificationData }
├── workplaces/
│   ├── list
│   │   ├── Content: Hospital/institution affiliations, roles, schedules
│   │   └── Data: { workplaces[], roles[], schedules[] }
│   └── manage/:id
│       ├── Content: Workplace settings, schedule management, role updates
│       └── Data: { workplace, schedule, role, settings }
└── profile
    ├── Content: Personal info, qualifications, schedule, documents
    └── Data: { personalInfo, qualifications[], schedule, documents[] }
```

### **🧑‍💼 Patient Dashboard (Based on Profile Entity)**
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
├── blood-donations/
│   ├── history
│   │   ├── Content: Donation history, badges, contribution points
│   │   └── Data: { donations[], badges[], contributionPoints, stats }
│   ├── schedule
│   │   ├── Content: Upcoming donations, eligibility check, reminders
│   │   └── Data: { upcomingDonations[], eligibility, reminders[] }
│   └── find-requests
│       ├── Content: Blood requisition requests, location filter, urgency
│       └── Data: { requests[], location, urgency, bloodGroup }
├── following/
│   ├── doctors
│   │   ├── Content: Followed doctors, activity feed
│   │   └── Data: { followedDoctors[], activityFeed[] }
│   └── hospitals
│       ├── Content: Followed hospitals, updates
│       └── Data: { followedHospitals[], updates[] }
├── volunteer-activities/
│   ├── list
│   │   ├── Content: Volunteer opportunities, participation history, impact
│   │   └── Data: { opportunities[], participation[], impact[] }
│   └── join
│       ├── Content: Organization selection, role application, commitment
│       └── Data: { organizations[], roles[], commitment }
└── profile
    ├── Content: Personal info, emergency contacts, insurance
    └── Data: { personalInfo, emergencyContacts[], insurance }
```

### **🏥 Hospital Admin Dashboard (Based on Hospital Entity)**
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
├── medical-tests/
│   ├── list
│   │   ├── Content: Available tests, pricing, scheduling
│   │   └── Data: { tests[], pricing[], scheduling[] }
│   ├── add
│   │   ├── Content: Test configuration, pricing, availability
│   │   └── Data: { testInfo, pricing, availability, requirements }
│   └── manage/:id
│       ├── Content: Test settings, pricing updates, availability
│       └── Data: { test, pricing, availability, settings }
├── amenities/
│   ├── list
│   │   ├── Content: Hospital amenities, availability, maintenance
│   │   └── Data: { amenities[], availability, maintenance[] }
│   └── manage/:id
│       ├── Content: Amenity details, availability settings, maintenance
│       └── Data: { amenity, availability, maintenance, settings }
├── ambulances/
│   ├── list
│   │   ├── Content: Ambulance fleet, availability, driver info
│   │   └── Data: { ambulances[], availability, drivers[] }
│   ├── add
│   │   ├── Content: Ambulance registration, driver assignment, type
│   │   └── Data: { ambulanceInfo, driver, type, location }
│   └── manage/:id
│       ├── Content: Ambulance details, driver management, maintenance
│       └── Data: { ambulance, driver, maintenance, settings }
├── reports
│   ├── Content: Financial reports, patient reports, export options
│   └── Data: { financialReports[], patientReports[], exportOptions }
└── settings
    ├── Content: Hospital info, billing settings, notifications
    └── Data: { hospitalInfo, billingSettings, notificationSettings }
```

### **🏛️ Institution Admin Dashboard (Based on Institution Entity)**
```
institution-admin/
├── dashboard
│   ├── Content: Student stats, faculty overview, academic performance
│   └── Data: { studentStats, faculty[], academicPerformance[] }
├── students/
│   ├── list
│   │   ├── Content: Student directory, enrollment status, performance
│   │   └── Data: { students[], enrollmentStatus, performance[] }
│   └── manage/:id
│       ├── Content: Student profile, academic records, enrollment
│       └── Data: { student, academicRecords[], enrollment }
├── faculty/
│   ├── list
│   │   ├── Content: Faculty directory, departments, specializations
│   │   └── Data: { faculty[], departments[], specializations[] }
│   └── manage/:id
│       ├── Content: Faculty profile, qualifications, assignments
│       └── Data: { faculty, qualifications[], assignments[] }
├── departments/
│   ├── list
│   │   ├── Content: Academic departments, faculty count, programs
│   │   └── Data: { departments[], facultyCounts, programs[] }
│   └── manage/:id
│       ├── Content: Department details, programs, faculty
│       └── Data: { department, programs[], faculty[] }
├── programs/
│   ├── list
│   │   ├── Content: Academic programs, curriculum, enrollment
│   │   └── Data: { programs[], curriculum[], enrollment[] }
│   └── manage/:id
│       ├── Content: Program details, curriculum management, enrollment
│       └── Data: { program, curriculum, enrollment, settings }
├── research/
│   ├── list
│   │   ├── Content: Research projects, publications, collaborations
│   │   └── Data: { projects[], publications[], collaborations[] }
│   └── manage/:id
│       ├── Content: Project details, team, funding, progress
│       └── Data: { project, team[], funding, progress }
└── settings
    ├── Content: Institution info, academic calendar, policies
    └── Data: { institutionInfo, academicCalendar, policies }
```

### **🤝 Social Organization Dashboard (Based on SocialOrganization Entity)**
```
social-organization/
├── dashboard
│   ├── Content: Stats cards, active campaigns, recent donations, volunteer count, project updates
│   └── Data: { stats, activeCampaigns[], donations[], volunteers[], projectUpdates[] }
├── campaigns/
│   ├── list
│   │   ├── Content: Organization's campaigns, status filter, performance metrics
│   │   └── Data: { campaigns[], filters, metrics[], totalRaised }
│   ├── create
│   │   ├── Content: Campaign creation form, beneficiary selection, goal setting
│   │   └── Data: { campaignInfo, beneficiaryDetails, goalAmount, timeline }
│   ├── view/:id
│   │   ├── Content: Campaign details, donor list, updates, analytics
│   │   └── Data: { campaign, donors[], updates[], analytics, timeline }
│   └── edit/:id
│       ├── Content: Campaign editor, update posting, media management
│       └── Data: { campaign, mediaFiles[], updates[], settings }
├── projects/
│   ├── list
│   │   ├── Content: Project portfolio, status tracking, resource allocation
│   │   └── Data: { projects[], statusFilters, resources[], timelines[] }
│   ├── create
│   │   ├── Content: Project planning form, resource requirements, timeline
│   │   └── Data: { projectInfo, resources[], timeline, budget }
│   ├── view/:id
│   │   ├── Content: Project details, progress tracking, team members, updates
│   │   └── Data: { project, progress, team[], updates[], milestones[] }
│   └── edit/:id
│       ├── Content: Project editor, milestone management, team assignment
│       └── Data: { project, milestones[], team[], budget }
├── volunteers/
│   ├── list
│   │   ├── Content: Volunteer directory, role assignment, activity tracking
│   │   └── Data: { volunteers[], roles[], activities[], schedules[] }
│   ├── recruitment
│   │   ├── Content: Volunteer opportunity posting, skill requirements
│   │   └── Data: { opportunities[], skillRequirements[], schedules[] }
│   └── manage/:id
│       ├── Content: Volunteer profile, activity history, role management
│       └── Data: { volunteer, activityHistory[], roles[], performance }
├── donations/
│   ├── overview
│   │   ├── Content: Donation analytics, top donors, recurring donations
│   │   └── Data: { donationStats, topDonors[], recurringDonations[], trends }
│   ├── campaigns
│   │   ├── Content: Campaign-wise donation breakdown, performance metrics
│   │   └── Data: { campaignDonations[], metrics[], comparisons[] }
│   └── reports
│       ├── Content: Financial reports, donor reports, tax documents
│       └── Data: { financialReports[], donorReports[], taxDocuments[] }
├── partnerships/
│   ├── list
│   │   ├── Content: Partner organizations, collaboration types, active partnerships
│   │   └── Data: { partners[], collaborationTypes[], activePartnerships[] }
│   ├── requests
│   │   ├── Content: Partnership requests, proposal management, negotiation
│   │   └── Data: { requests[], proposals[], negotiations[] }
│   └── manage/:id
│       ├── Content: Partnership details, shared projects, communication
│       └── Data: { partnership, sharedProjects[], communications[] }
├── events/
│   ├── list
│   │   ├── Content: Event calendar, upcoming events, past events
│   │   └── Data: { events[], calendar, categories[], attendance[] }
│   ├── create
│   │   ├── Content: Event creation form, venue booking, participant management
│   │   └── Data: { eventInfo, venue, participants[], budget }
│   ├── view/:id
│   │   ├── Content: Event details, participant list, photos, feedback
│   │   └── Data: { event, participants[], media[], feedback[] }
│   └── manage/:id
│       ├── Content: Event management, check-in system, live updates
│       └── Data: { event, checkIns[], liveUpdates[], logistics }
├── resources/
│   ├── inventory
│   │   ├── Content: Resource inventory, allocation tracking, requests
│   │   └── Data: { inventory[], allocations[], requests[], categories[] }
│   ├── donors
│   │   ├── Content: Resource donor directory, donation history, wish lists
│   │   └── Data: { resourceDonors[], donationHistory[], wishLists[] }
│   └── distribution
│       ├── Content: Distribution planning, beneficiary lists, tracking
│       └── Data: { distributionPlans[], beneficiaries[], trackingInfo[] }
├── communications/
│   ├── announcements
│   │   ├── Content: Public announcements, newsletter management, social media
│   │   └── Data: { announcements[], newsletters[], socialPosts[] }
│   ├── donor-relations
│   │   ├── Content: Donor communication, thank you messages, updates
│   │   └── Data: { donorCommunications[], thankYouTemplates[], updates[] }
│   └── media-kit
│       ├── Content: Press releases, media assets, brand guidelines
│       └── Data: { pressReleases[], mediaAssets[], brandGuidelines }
└── profile
    ├── Content: Organization info, verification documents, affiliation status
    └── Data: { organizationInfo, verificationDocs[], affiliationStatus, settings }
```

### **🩸 Blood Donation Module (Based on BloodDonation & BloodRequisition Entities)**
```
blood-donation/
├── campaigns/
│   ├── list
│   │   ├── Content: Blood donation campaigns, location filter, urgency
│   │   └── Data: { campaigns[], locations, urgency, bloodGroups[] }
│   ├── view/:id
│   │   ├── Content: Campaign details, donor list, progress, updates
│   │   └── Data: { campaign, donors[], progress, updates[] }
│   └── create
│       ├── Content: Campaign creation form, beneficiary details, goal
│       └── Data: { campaignInfo, beneficiaryDetails, goal, timeline }
├── requests/
│   ├── list
│   │   ├── Content: Blood requisition requests, urgency filter, location
│   │   └── Data: { requests[], urgency, locations, bloodGroups[] }
│   ├── view/:id
│   │   ├── Content: Request details, donor matches, status updates
│   │   └── Data: { request, donorMatches[], status, updates[] }
│   └── create
│       ├── Content: Blood request form, urgency, contact details
│       └── Data: { bloodGroup, urgency, contactDetails, hospital }
├── donors/
│   ├── list
│   │   ├── Content: Available donors, blood group filter, location
│   │   └── Data: { donors[], bloodGroups, locations, availability }
│   ├── view/:id
│   │   ├── Content: Donor profile, donation history, contact info
│   │   └── Data: { donor, donationHistory[], contactInfo, availability }
│   └── register
│       ├── Content: Donor registration form, health screening, consent
│       └── Data: { donorInfo, healthScreening, consent, availability }
├── donations/
│   ├── history
│   │   ├── Content: Donation history, badges, contribution points
│   │   └── Data: { donations[], badges[], contributionPoints, stats }
│   ├── schedule
│   │   ├── Content: Upcoming donations, eligibility check, reminders
│   │   └── Data: { upcomingDonations[], eligibility, reminders[] }
│   └── find-requests
│       ├── Content: Blood requisition requests, location filter, urgency
│       └── Data: { requests[], location, urgency, bloodGroup }
└── analytics
    ├── dashboard
    │   ├── Content: Blood donation statistics, trends, impact metrics
    │   └── Data: { statistics, trends, impactMetrics, bloodGroupStats }
    └── reports
        ├── Content: Donation reports, donor analytics, export options
        └── Data: { donationReports[], donorAnalytics[], exportOptions }
```

### **💰 Fundraising Module (Based on Advertisement Entity)**
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

### **🩺 Medical Services (Based on MedicalTest, Medicine, Ambulance Entities)**
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
├── pharmacy/
│   ├── search
│   │   ├── Content: Medicine search, availability, price comparison
│   │   └── Data: { medicines[], availability[], prices[] }
│   └── order
│       ├── Content: Cart, prescription upload, delivery address
│       └── Data: { cartItems[], prescription, deliveryAddress }
└── home-care/
    ├── list
    │   ├── Content: Home care services, provider selection, pricing
    │   └── Data: { services[], providers[], pricing[], availability }
    └── book
        ├── Content: Service booking form, schedule, requirements
        └── Data: { service, schedule, requirements, address }
```

### **📢 Advertisement Management (Based on Advertisement Entity)**
```
advertisements/
├── dashboard
│   ├── Content: Ad performance, views, clicks, revenue
│   └── Data: { performance, views, clicks, revenue, trends }
├── campaigns/
│   ├── list
│   │   ├── Content: Ad campaigns, status filter, performance metrics
│   │   └── Data: { campaigns[], status, metrics[], performance }
│   ├── create
│   │   ├── Content: Campaign creation form, targeting, budget
│   │   └── Data: { campaignInfo, targeting, budget, timeline }
│   ├── view/:id
│   │   ├── Content: Campaign details, performance analytics, targeting
│   │   └── Data: { campaign, analytics, targeting, performance }
│   └── edit/:id
│       ├── Content: Campaign editor, targeting updates, budget management
│       └── Data: { campaign, targeting, budget, settings }
├── ads/
│   ├── list
│   │   ├── Content: Individual ads, performance, status
│   │   └── Data: { ads[], performance, status, metrics[] }
│   ├── create
│   │   ├── Content: Ad creation form, creative assets, targeting
│   │   └── Data: { adInfo, creativeAssets, targeting, settings }
│   └── edit/:id
│       ├── Content: Ad editor, creative updates, performance
│       └── Data: { ad, creative, performance, settings }
├── targeting/
│   ├── demographics
│   │   ├── Content: Age group, gender, location targeting
│   │   └── Data: { ageGroups, genders, locations, targeting }
│   ├── interests
│   │   ├── Content: Medical interests, behavior targeting
│   │   └── Data: { interests[], behaviors[], targeting }
│   └── custom
│       ├── Content: Custom audience creation, lookalike audiences
│       └── Data: { customAudiences[], lookalikeAudiences[] }
└── analytics
    ├── performance
    │   ├── Content: Ad performance metrics, ROI, conversion tracking
    │   └── Data: { metrics[], roi, conversions[], tracking }
    └── reports
        ├── Content: Performance reports, export options, insights
        └── Data: { reports[], exportOptions, insights[] }
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

### **⚙️ Admin Panel (Based on UserType Enum)**
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
│   ├── institutions
│   │   ├── Content: Institution management, verification
│   │   └── Data: { institutions[], verificationStatus[] }
│   ├── social-organizations
│   │   ├── Content: Organization verification, affiliation management
│   │   └── Data: { organizations[], verificationStatus[], affiliations[] }
│   ├── associations
│   │   ├── Content: Association management, verification
│   │   └── Data: { associations[], verificationStatus[] }
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

## 🎯 **Priority Implementation Order (Aligned with Backend)**

### **Phase 1: Core Foundation (Weeks 1-8)**
1. **auth/** - Complete authentication flow with Keycloak integration
2. **public/** - Landing pages and basic info
3. **directory/doctors/** - Doctor listing and profiles (based on Doctor entity)
4. **directory/hospitals/** - Hospital directory (based on Hospital entity)
5. **patient/dashboard** - Basic patient dashboard (based on Profile entity)
6. **doctor/dashboard** - Basic doctor dashboard (based on Doctor entity)

### **Phase 2: Essential Features (Weeks 9-16)**
7. **patient/appointments/** - Appointment booking system
8. **doctor/appointments/** - Doctor appointment management
9. **patient/health-records/** - Document management
10. **doctor/prescriptions/** - Prescription system *(temporary standalone service exists; will be built natively in main backend/frontend)*
11. **directory/search** - Advanced search functionality
12. **hospital-admin/dashboard** - Hospital admin basics (based on Hospital entity)

### **Phase 3: Advanced Features (Weeks 17-24)**
13. **blood-donation/** - Complete blood donation module (based on BloodDonation & BloodRequisition entities)
14. **services/tests/** - Medical test booking (based on MedicalTest entity)
15. **communication/chat/** - Basic messaging
16. **doctor/blog/** - Blogging system
17. **analytics/dashboard** - Basic analytics
18. **admin/users/** - User management (based on UserType enum)

### **Phase 4: Enhancement (Weeks 25-32)**
19. **communication/video-call/** - Video consultation
20. **services/ambulance/** - Emergency services (based on Ambulance entity)
21. **services/pharmacy/** - Medicine ordering (based on Medicine entity)
22. **analytics/reports/** - Advanced reporting
23. **admin/system/** - System administration
24. **Mobile optimization** - All pages mobile-ready

---

## 🚨 **Critical Missing Elements (Based on Backend Analysis)**

### **Security & Compliance**
- **Keycloak integration** - JWT token management, role-based access control
- **Audit logs** - Track all medical data access (based on Auditable entity)
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
- **License verification** - Doctor/hospital license display (BMDC verification)
- **Insurance integration** - Health insurance information

### **Accessibility**
- **Accessibility settings** - Font size, contrast, voice commands
- **Multi-language support** - Bengali/English toggle (based on bnName fields)
- **Voice navigation** - For visually impaired users
- **Screen reader optimization** - Proper ARIA labels

### **Integration Points**
- **API documentation** - For third-party integrations
- **Webhook management** - Real-time data sync
- **Export/Import** - Data portability
- **Backup/Restore** - Data recovery options
- **Third-party SSO** - Google, Facebook login

---

## 📱 **Mobile-Specific Pages** *(Future — mobile app not yet started)*

> The mobile application is planned for Phase 3. The pages below are design intent for when
> mobile development begins. Do not build these as part of current web frontend work.

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

## 🔄 **Data Flow Connections (Based on Backend Entities)**

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

### **Entity Relationships (Based on Backend)**
```
Profile (User) → Doctor (if userType = DOCTOR)
Profile (User) → Hospital (if userType = HOSPITAL_ADMIN)
Profile (User) → Institution (if userType = INSTITUTION_ADMIN)
Profile (User) → SocialOrganization (if userType = SOCIAL_ORGANIZATION_ADMIN)
Profile (User) → Association (if userType = ASSOCIATION_ADMIN)

Doctor → Hospital (via DoctorWorkplace)
Doctor → Association (via DoctorAssociation)
Doctor → MedicalSpeciality (via specializations)
Doctor → Degree (via DoctorDegree)

Hospital → HospitalAmenity
Hospital → HospitalMedicalTest
Hospital → Ambulance

BloodDonation → Profile (donor)
BloodDonation → Hospital
BloodRequisition → Profile (requester)
```

### **Data Synchronization**
- **Real-time updates** - Appointment status, chat messages
- **Batch updates** - Analytics, reports
- **Conflict resolution** - Multiple edit scenarios
- **Cache invalidation** - Ensure data freshness

---

## 🔑 **Keycloak Integration Points**

### **User Management**
- **User registration** - Profile creation with Keycloak user
- **Role assignment** - UserType-based role mapping
- **Permission management** - Fine-grained access control
- **Session management** - JWT token lifecycle

### **Authentication Flows**
- **Login** - Keycloak authentication
- **Password reset** - Keycloak password management
- **Multi-factor** - Keycloak MFA integration
- **Social login** - OAuth2 providers

### **Authorization**
- **Role-based access** - UserType permissions
- **Resource protection** - API endpoint security
- **Dynamic permissions** - Context-aware access control

This revised structure provides a clear page hierarchy that aligns with your actual Open Care Backend entities, making it much easier for developers to understand the scope and implementation requirements while maintaining consistency with your existing data model.
