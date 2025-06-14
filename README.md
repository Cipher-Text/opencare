# Open-Care

<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Open-Care</h3>

  <p align="center">
    Empowering communities with free access to medical information for healthier lives!
    <br />
    <a href="#getting-started"><strong>Get Started »</strong></a>
    <br />
    <br />
    <a href="http://46.102.157.211:5175/">View Live Demo</a>
    ·
    <a href="http://46.102.157.211:6700/swagger-ui/index.html">API Documentation</a>
    ·
    <a href="https://github.com/Cipher-Text/opencare-issues/issues">Report Bug</a>
    ·
    <a href="https://github.com/Cipher-Text/opencare-issues/issues">Request Feature</a>
  </p>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]
</div>

## 📖 Table of Contents

<details>
  <summary>Click to expand</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#key-features">Key Features</a></li>
    <li><a href="#repositories">Repositories</a></li>
    <li><a href="#live-demo--api">Live Demo & API</a></li>
    <li><a href="#tech-stack">Tech Stack</a></li>
    <li><a href="#architecture">Architecture</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#api-reference">API Reference</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

## 🩺 About The Project

[![Product Screenshot][product-screenshot]](http://46.102.157.211:5175/)

Open-Care is a comprehensive medical information platform designed to democratize access to healthcare resources. In a world where medical knowledge should be accessible to all, our platform serves as a bridge between healthcare professionals and the communities they serve.

The healthcare landscape is rapidly evolving with new research, treatments, and medical guidelines emerging constantly. Open-Care addresses the critical need for a centralized, reliable source of medical information that empowers both healthcare providers and patients with accurate, evidence-based resources.

### 🎯 Mission
To create an inclusive healthcare ecosystem where accurate medical information, qualified healthcare providers, and trusted medical facilities are accessible to everyone, regardless of their geographical or economic constraints.

## ✨ Key Features

### 👨‍⚕️ **Doctor Directory**
- **Comprehensive Database**: Access detailed profiles of healthcare professionals
- **Advanced Search**: Find doctors by specialty, location, experience, and ratings
- **Verified Credentials**: All medical professionals are verified for authenticity
- **Contact Integration**: Direct contact information and appointment booking capabilities
- **Patient Reviews**: Community-driven feedback system for quality assurance

### 🏥 **Hospital Database**
- **Facility Information**: Detailed hospital profiles with services, facilities, and specializations
- **Location-Based Search**: Find nearby hospitals using geolocation
- **Service Mapping**: Search hospitals by specific medical services and treatments
- **Quality Ratings**: Community and professional ratings for informed decisions
- **Real-time Updates**: Current availability, emergency services, and contact information

### 🔍 **Advanced Information Search**
- **Medical Knowledge Base**: Curated collection of medical articles, research papers, and guidelines
- **Smart Search Engine**: AI-powered search with medical terminology recognition
- **Evidence-Based Content**: All information sourced from peer-reviewed medical literature
- **Regular Updates**: Content continuously updated with latest medical research
- **Multi-language Support**: Accessible content in multiple languages (planned)

### 📱 **User Experience**
- **Responsive Design**: Seamless experience across desktop, tablet, and mobile devices
- **Intuitive Interface**: User-friendly design suitable for both medical professionals and patients
- **Accessibility Features**: Designed with accessibility standards for inclusive usage
- **Offline Support**: Critical information available offline for emergency situations

## 📁 Repositories

This project is organized into separate repositories for better maintainability and deployment:

| Repository | Description | Technologies | Status |
|------------|-------------|--------------|--------|
| **[Frontend Repository](https://github.com/Cipher-Text/opencare-frontend)** | React-based web application with modern UI/UX | React, Next.js, TailwindCSS, TypeScript | ✅ Active |
| **[Backend Repository](https://github.com/Cipher-Text/opencare-backend)** | RESTful API server with comprehensive medical data management | Java, Spring Boot, PostgreSQL, Swagger | ✅ Active |

### Repository Structure
```
Open-Care Project/
├── opencare-frontend/          # Client-side application
│   ├── src/
│   ├── public/
│   └── package.json
├── opencare-backend/           # Server-side application
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── pom.xml
└── README.md                   # This file
```

## 🌐 Live Demo & API

| Service | URL | Description |
|---------|-----|-------------|
| **Live Application** | [http://46.102.157.211:5175/](http://46.102.157.211:5175/) | Full-featured web application |
| **API Documentation** | [http://46.102.157.211:6700/swagger-ui/index.html](http://46.102.157.211:6700/swagger-ui/index.html) | Interactive API documentation |
| **API Base URL** | `http://46.102.157.211:6700/api/v1` | Base endpoint for API calls |

> **Note**: Demo servers are for testing purposes and may have limited uptime. For production use, please deploy your own instance.

## 🛠️ Tech Stack

### Frontend
- **Framework**: [Next.js](https://nextjs.org/) - React framework for production
- **Styling**: [TailwindCSS](https://tailwindcss.com/) - Utility-first CSS framework
- **UI Components**: Custom components with accessibility focus
- **State Management**: React Context API / Redux Toolkit
- **HTTP Client**: Axios for API communication
- **Type Safety**: TypeScript for enhanced development experience

### Backend
- **Runtime**: [Java 17+](https://www.java.com/) - Enterprise-grade runtime environment
- **Framework**: [Spring Boot](https://spring.io/projects/spring-boot) - Production-ready application framework
- **Database**: [PostgreSQL](https://www.postgresql.org/) - Advanced open-source relational database
- **API Documentation**: [Swagger/OpenAPI](https://swagger.io/) - Interactive API documentation
- **Security**: Spring Security for authentication and authorization
- **Testing**: JUnit 5, Mockito for comprehensive testing

### Infrastructure
- **Deployment**: Docker containers for consistent deployment
- **Database Migration**: Flyway for version-controlled database changes
- **Monitoring**: Application health checks and logging
- **CI/CD**: GitHub Actions for automated testing and deployment

## 🏗️ Architecture

### Database Schema
[![Database Diagram][db-screenshot]](https://dbdiagram.io/d/6488b6bf722eb77494e72192)

Our database is designed with scalability and data integrity in mind:

- **Users & Authentication**: Secure user management with role-based access
- **Medical Professionals**: Comprehensive doctor profiles with specializations
- **Healthcare Facilities**: Detailed hospital and clinic information
- **Medical Content**: Curated medical information with proper categorization
- **Reviews & Ratings**: Community feedback system with moderation
- **Search Indexing**: Optimized indexes for fast information retrieval

### System Architecture
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend API   │    │   Database      │
│   (Next.js)     │◄──►│   (Spring Boot) │◄──►│   (PostgreSQL)  │
│   Port: 3000    │    │   Port: 6700    │    │   Port: 5432    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed on your system:

- **Node.js** (v18.0.0 or higher)
- **npm** or **yarn** package manager
- **Java Development Kit** (JDK 17 or higher)
- **PostgreSQL** (v14.0 or higher)
- **Git** for version control

```bash
# Check Node.js version
node --version

# Check Java version
java --version

# Check PostgreSQL
psql --version
```

### Installation

#### 1. Clone the Repositories

```bash
# Clone frontend repository
git clone https://github.com/Cipher-Text/opencare-frontend.git
cd opencare-frontend

# Clone backend repository
git clone https://github.com/Cipher-Text/opencare-backend.git
cd opencare-backend
```

#### 2. Backend Setup

```bash
cd opencare-backend

# Create PostgreSQL database
createdb opencare_db

# Configure application properties
cp src/main/resources/application.properties.example src/main/resources/application.properties

# Edit database configuration
# Update the following in application.properties:
# spring.datasource.url=jdbc:postgresql://localhost:5432/opencare_db
# spring.datasource.username=your_username
# spring.datasource.password=your_password

# Build and run the application
./mvnw clean install
./mvnw spring-boot:run
```

The backend API will be available at `http://localhost:6700`

#### 3. Frontend Setup

```bash
cd opencare-frontend

# Install dependencies
npm install

# Configure environment variables
cp .env.example .env.local

# Update API endpoint in .env.local:
# NEXT_PUBLIC_API_URL=http://localhost:6700/api/v1

# Start development server
npm run dev
```

The frontend application will be available at `http://localhost:3000`

### Environment Variables

#### Backend (.env or application.properties)
```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/opencare_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

# JWT Configuration
app.jwt.secret=your_jwt_secret_key
app.jwt.expiration=86400000

# Email Configuration (optional)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
```

#### Frontend (.env.local)
```bash
# API Configuration
NEXT_PUBLIC_API_URL=http://localhost:6700/api/v1
NEXT_PUBLIC_APP_URL=http://localhost:3000

# Optional: Analytics and monitoring
NEXT_PUBLIC_GOOGLE_ANALYTICS_ID=your_ga_id
```

## 💻 Usage

### For Healthcare Professionals

1. **Registration**: Create a professional account with credential verification
2. **Profile Management**: Maintain updated professional information and specializations
3. **Content Contribution**: Contribute to the medical knowledge base
4. **Patient Interaction**: Respond to queries and provide professional guidance

### For Patients and General Users

1. **Doctor Search**: Find qualified healthcare professionals in your area
2. **Hospital Lookup**: Locate nearby medical facilities and services
3. **Medical Information**: Access reliable, evidence-based medical content
4. **Health Records**: Maintain personal health information (future feature)

### For Developers

```bash
# API Usage Example
curl -X GET "http://localhost:6700/api/v1/doctors?specialty=cardiology&location=dhaka" \
  -H "Accept: application/json"

# Response
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "Dr. John Doe",
      "specialty": "Cardiology",
      "location": "Dhaka",
      "rating": 4.8,
      "experience": 15
    }
  ]
}
```

## 📚 API Reference

### Base URL
```
http://localhost:6700/api/v1
```

### Authentication
The API uses JWT tokens for authentication. Include the token in the Authorization header:

```bash
Authorization: Bearer <your_jwt_token>
```

### Key Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/doctors` | Get list of doctors | No |
| `GET` | `/doctors/{id}` | Get doctor details | No |
| `POST` | `/doctors` | Create doctor profile | Yes |
| `GET` | `/hospitals` | Get list of hospitals | No |
| `GET` | `/hospitals/{id}` | Get hospital details | No |
| `GET` | `/search` | Search medical information | No |
| `POST` | `/auth/login` | User authentication | No |
| `POST` | `/auth/register` | User registration | No |

For complete API documentation, visit: [API Documentation](http://46.102.157.211:6700/swagger-ui/index.html)

## 🗺️ Roadmap

### Phase 1: Core Features ✅
- [x] Doctor directory with search functionality
- [x] Hospital database with location-based search
- [x] Basic medical information repository
- [x] User authentication system
- [x] Responsive web interface

### Phase 2: Enhanced Features 🚧
- [ ] Mobile application (React Native)
- [ ] Advanced search with AI-powered recommendations
- [ ] Telemedicine integration
- [ ] Appointment booking system
- [ ] Patient health records management

### Phase 3: Advanced Features 📋
- [ ] Multi-language support (Bengali, Hindi, Spanish)
- [ ] Real-time chat with healthcare professionals
- [ ] AI-powered symptom checker
- [ ] Integration with wearable health devices
- [ ] Emergency services locator

### Phase 4: Expansion 🌍
- [ ] International healthcare provider network
- [ ] Medical insurance integration
- [ ] Pharmaceutical information database
- [ ] Medical research collaboration platform
- [ ] Community health analytics

## 🤝 Contributing

We welcome contributions from the community! Whether you're a developer, healthcare professional, or simply passionate about improving healthcare accessibility, there are many ways to contribute.

### How to Contribute

1. **Fork the Repository**
   ```bash
   git fork https://github.com/Cipher-Text/opencare-frontend
   # or
   git fork https://github.com/Cipher-Text/opencare-backend
   ```

2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Make Your Changes**
    - Follow our coding standards and conventions
    - Add tests for new functionality
    - Update documentation as needed

4. **Commit Your Changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```

5. **Push to Your Branch**
   ```bash
   git push origin feature/amazing-feature
   ```

6. **Open a Pull Request**
    - Provide a clear description of your changes
    - Reference any related issues
    - Include screenshots for UI changes

### Contribution Guidelines

- **Code Style**: Follow established coding conventions for each technology
- **Testing**: Ensure all tests pass and add new tests for new features
- **Documentation**: Update relevant documentation for any changes
- **Commit Messages**: Use clear, descriptive commit messages
- **Pull Requests**: Keep PRs focused and include detailed descriptions

### Areas for Contribution

- **Frontend Development**: React/Next.js components and features
- **Backend Development**: Spring Boot APIs and services
- **Database**: Schema improvements and optimization
- **Documentation**: Technical and user documentation
- **Testing**: Unit tests, integration tests, and end-to-end tests
- **UI/UX**: Design improvements and accessibility features
- **Medical Content**: Curated medical information and resources

## 📝 License

Distributed under the MIT License. See [`LICENSE`](LICENSE) for more information.

## 📞 Contact

**Sadman Sobhan**
- LinkedIn: [@sadmansobhan](https://www.linkedin.com/in/sadmansobhan/)
- Email: imran110219@gmail.com
- GitHub: [@Cipher-Text](https://github.com/Cipher-Text)

**Project Links:**
- Frontend Repository: [https://github.com/Cipher-Text/opencare-frontend](https://github.com/Cipher-Text/opencare-frontend)
- Backend Repository: [https://github.com/Cipher-Text/opencare-backend](https://github.com/Cipher-Text/opencare-backend)
- Live Demo: [http://46.102.157.211:5175/](http://46.102.157.211:5175/)

## 🙏 Acknowledgments

We extend our gratitude to the following resources and communities that made this project possible:

### Medical Resources
- [World Health Organization (WHO)](https://www.who.int/) - Global health guidelines and standards
- [PubMed](https://pubmed.ncbi.nlm.nih.gov/) - Medical literature database
- [Mayo Clinic](https://www.mayoclinic.org/) - Trusted medical information
- [WebMD](https://www.webmd.com/) - Medical information platform

### Technical Resources
- [Spring Boot Documentation](https://spring.io/projects/spring-boot) - Comprehensive framework guide
- [React Documentation](https://reactjs.org/) - Frontend library documentation
- [PostgreSQL Documentation](https://www.postgresql.org/docs/) - Database documentation
- [TailwindCSS](https://tailwindcss.com/) - Utility-first CSS framework

### Development Tools
- [GitHub](https://github.com) - Version control and collaboration
- [Docker](https://www.docker.com/) - Containerization platform
- [Swagger](https://swagger.io/) - API documentation tools
- [Postman](https://www.postman.com/) - API development and testing

### Community
- Open-source contributors and maintainers
- Healthcare professionals providing domain expertise
- Beta testers and early adopters
- The global developer community

---

<div align="center">
  <p>Made with ❤️ for better healthcare accessibility</p>
  <p>
    <a href="#readme-top">Back to Top</a> •
    <a href="http://46.102.157.211:5175/">Live Demo</a> •
    <a href="http://46.102.157.211:6700/swagger-ui/index.html">API Docs</a>
  </p>
</div>

<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/Cipher-Text/opencare?style=for-the-badge
[contributors-url]: https://github.com/Cipher-Text/opencare/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Cipher-Text/opencare?style=for-the-badge
[forks-url]: https://github.com/Cipher-Text/opencare/network/members
[stars-shield]: https://img.shields.io/github/stars/Cipher-Text/opencare?style=for-the-badge
[stars-url]: https://github.com/Cipher-Text/opencare/stargazers
[issues-shield]: https://img.shields.io/github/issues/Cipher-Text/opencare?style=for-the-badge
[issues-url]: https://github.com/Cipher-Text/opencare/issues
[license-shield]: https://img.shields.io/github/license/Cipher-Text/opencare?style=for-the-badge
[license-url]: https://github.com/Cipher-Text/opencare/blob/master/LICENSE
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/sadmansobhan
[product-screenshot]: images/screenshot.png
[db-screenshot]: images/db-diagram.png