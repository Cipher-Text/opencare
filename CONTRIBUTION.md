# Contributing to Open-Care

Thank you for your interest in contributing to Open-Care! We welcome contributions from developers, healthcare professionals, designers, and anyone passionate about improving healthcare accessibility.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [Contribution Guidelines](#contribution-guidelines)
- [Frontend Contributions](#frontend-contributions)
- [Backend Contributions](#backend-contributions)
- [Mobile Contributions](#mobile-contributions)
- [Documentation Contributions](#documentation-contributions)
- [Testing Guidelines](#testing-guidelines)
- [Pull Request Process](#pull-request-process)
- [Issue Reporting](#issue-reporting)
- [Community Guidelines](#community-guidelines)

## 🤝 Code of Conduct

By participating in this project, you agree to abide by our Code of Conduct:

### Our Pledge
We pledge to make participation in our project a harassment-free experience for everyone, regardless of age, body size, disability, ethnicity, gender identity and expression, level of experience, nationality, personal appearance, race, religion, or sexual identity and orientation.

### Our Standards
- Be respectful and inclusive
- Welcome newcomers and help them get started
- Focus on constructive feedback
- Respect differing viewpoints and experiences
- Show empathy towards other community members

### Unacceptable Behavior
- Harassment, trolling, or discriminatory comments
- Publishing others' private information
- Spam or excessive self-promotion
- Any conduct that could be considered inappropriate in a professional setting

## 🚀 Getting Started

### Prerequisites

Before contributing, ensure you have:

- **Git** installed and configured
- **Node.js** (v18.0.0 or higher)
- **Java Development Kit** (JDK 17 or higher) - for backend contributions
- **Docker** and **Docker Compose** - for full stack development
- **A GitHub account** for submitting contributions

### First Steps

1. **Fork the Repository**: Fork the specific repository you want to contribute to
2. **Clone Your Fork**: Clone your forked repository to your local machine
3. **Set Up Development Environment**: Follow the setup instructions in the respective repository
4. **Find an Issue**: Look for issues labeled "good first issue" or "help wanted"
5. **Join the Community**: Join our discussions and introduce yourself

## 🛠️ Development Setup

### Environment Setup

```bash
# Clone your fork
git clone https://github.com/YOUR_USERNAME/REPOSITORY_NAME.git
cd REPOSITORY_NAME

# Add upstream remote
git remote add upstream https://github.com/Cipher-Text/REPOSITORY_NAME.git

# Create a new branch
git checkout -b feature/your-feature-name
```

### Docker Development Environment

For a complete development environment:

```bash
# Clone the main repository
git clone https://github.com/Cipher-Text/open-care.git
cd open-care

# Start all services
docker-compose up -d

# Services will be available at:
# - Frontend: http://localhost:3000
# - Backend: http://localhost:6700
# - Keycloak: http://localhost:8080
# - MinIO Console: http://localhost:9001
```

## 📝 Contribution Guidelines

### General Guidelines

1. **Keep Changes Focused**: One feature or fix per pull request
2. **Follow Existing Patterns**: Maintain consistency with existing code style
3. **Write Tests**: Include tests for new features and bug fixes
4. **Update Documentation**: Update relevant documentation for your changes
5. **Test Thoroughly**: Ensure all tests pass before submitting

### Commit Message Guidelines

Follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

**Examples:**
```
feat(frontend): add doctor search functionality
fix(backend): resolve authentication token expiration
docs(readme): update installation instructions
test(mobile): add unit tests for profile component
```

## 🎨 Frontend Contributions

### Technology Stack
- **Framework**: Next.js 13+ with App Router
- **Language**: TypeScript
- **Styling**: TailwindCSS
- **State Management**: React Context API / Redux Toolkit
- **Testing**: Jest, React Testing Library
- **Linting**: ESLint, Prettier

### Setup Instructions

```bash
# Navigate to frontend repository
cd open-care-frontend

# Install dependencies
npm install

# Set up environment variables
cp .env.example .env.local

# Start development server
npm run dev
```

### Code Style Guidelines

#### Component Structure
```typescript
// components/DoctorCard.tsx
import React from 'react';
import { Doctor } from '@/types/doctor';

interface DoctorCardProps {
  doctor: Doctor;
  onSelect?: (doctor: Doctor) => void;
}

export const DoctorCard: React.FC<DoctorCardProps> = ({ 
  doctor, 
  onSelect 
}) => {
  return (
    <div className="bg-white rounded-lg shadow-md p-6">
      {/* Component content */}
    </div>
  );
};
```

#### Page Structure
```typescript
// app/doctors/page.tsx
import { Metadata } from 'next';
import { DoctorsList } from '@/components/doctors/DoctorsList';

export const metadata: Metadata = {
  title: 'Find Doctors - Open-Care',
  description: 'Find qualified healthcare professionals in your area',
};

export default function DoctorsPage() {
  return (
    <main className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-8">Find Doctors</h1>
      <DoctorsList />
    </main>
  );
}
```

#### Styling Guidelines
- Use TailwindCSS utility classes
- Create custom components for complex styling
- Follow mobile-first responsive design
- Ensure accessibility compliance

### Testing
```bash
# Run tests
npm run test

# Run tests with coverage
npm run test:coverage

# Run E2E tests
npm run test:e2e
```

## ⚙️ Backend Contributions

### Technology Stack
- **Framework**: Spring Boot 3.x
- **Language**: Java 17+
- **Database**: PostgreSQL
- **Authentication**: Keycloak integration
- **File Storage**: MinIO
- **Testing**: JUnit 5, Mockito, TestContainers
- **Documentation**: Swagger/OpenAPI

### Setup Instructions

```bash
# Navigate to backend repository
cd open-care-backend

# Set up environment variables
cp src/main/resources/application.properties.example src/main/resources/application.properties

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

### Code Style Guidelines

#### Controller Structure
```java
@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
@Tag(name = "Doctors", description = "Doctor management endpoints")
public class DoctorController {
    
    private final DoctorService doctorService;
    
    @GetMapping
    @Operation(summary = "Get all doctors", description = "Retrieve a list of all doctors")
    public ResponseEntity<ApiResponse<List<DoctorDto>>> getAllDoctors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String specialty
    ) {
        // Implementation
    }
}
```

#### Service Structure
```java
@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {
    
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    
    public List<DoctorDto> getAllDoctors(Pageable pageable, String specialty) {
        // Implementation
    }
}
```

#### Repository Structure
```java
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    @Query("SELECT d FROM Doctor d WHERE d.specialty = :specialty")
    List<Doctor> findBySpecialty(@Param("specialty") String specialty);
    
    @Query("SELECT d FROM Doctor d WHERE d.location = :location")
    List<Doctor> findByLocation(@Param("location") String location);
}
```

### Testing Guidelines
```java
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class DoctorServiceTest {
    
    @Autowired
    private DoctorService doctorService;
    
    @MockBean
    private DoctorRepository doctorRepository;
    
    @Test
    void shouldReturnAllDoctors() {
        // Test implementation
    }
}
```

## 📱 Mobile Contributions

### Technology Stack
- **Framework**: React Native with Expo
- **Language**: TypeScript
- **Navigation**: React Navigation 6
- **State Management**: Redux Toolkit
- **UI Components**: Native Base / React Native Elements
- **Testing**: Jest, React Native Testing Library

### Setup Instructions

```bash
# Navigate to mobile repository
cd open-care-mobile

# Install dependencies
npm install

# Install Expo CLI globally
npm install -g @expo/cli

# Start development server
expo start
```

### Code Style Guidelines

#### Screen Structure
```typescript
// src/screens/DoctorListScreen.tsx
import React from 'react';
import { View, FlatList } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { DoctorCard } from '@/components/DoctorCard';
import { useDoctors } from '@/hooks/useDoctors';

export const Doctor