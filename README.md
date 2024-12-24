# CityThrillsMorocco 🌟

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18.x-blue.svg)](https://reactjs.org)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Responsive](https://img.shields.io/badge/Responsive-Yes-orange.svg)]()

<div align="center">
  <video width="800" height="400" controls>
    <source src="demo/citythrills-demo.mp4" type="video/mp4">
    Your browser does not support the video tag.
  </video>
  <p>Experience CityThrillsMorocco in Action - Your Gateway to Authentic Moroccan Experiences</p>
</div>

## 🎥 Demo Highlights

Watch our platform in action:
- User journey from search to booking
- Service provider dashboard overview
- Real-time booking management
- Interactive map features
- Social features and community engagement

[View Full Demo Video](demo/citythrills-demo.mp4)

## 🌐 Overview

CityThrillsMorocco is a state-of-the-art tourism platform connecting adventurous travelers with premium local experiences across Morocco. Our responsive web application ensures a seamless experience across all devices, from desktop to mobile.

## ✨ Key Features

### 📱 Responsive Design
- Fluid layouts that adapt to any screen size
- Touch-friendly interface for mobile users
- Optimized images and assets for fast loading
- Progressive Web App (PWA) capabilities

### 🎯 For Tourists
- **Smart Search System**
  - Location-based activity discovery
  - Advanced filtering by category
  - Real-time availability updates
  
- **User Experience**
  - Intuitive booking interface
  - Secure payment gateway
  - Interactive activity maps
  - Personalized favorites list
  
- **Social Features**
  - Activity reviews and ratings
  - Photo sharing capabilities
  - Community engagement

### 💼 For Service Providers
- **Business Dashboard**
  - Real-time analytics
  - Booking management
  - Revenue tracking
  - Customer insights

- **Content Management**
  - Easy activity publication
  - Media gallery management
  - Dynamic pricing tools
  - Availability calendar

## 🛠️ Technical Stack

### Frontend
- React.js with TypeScript
- Tailwind CSS for responsive styling
- Redux for state management
- React Router for navigation
- Axios for API requests

### Backend
- Spring Boot 3.x
- Spring Security for authentication
- Spring Data JPA
- RESTful API architecture

### Database
- MySQL/PostgreSQL
- Redis for caching

### Cloud Infrastructure
- AWS/Azure for hosting
- CloudFront for CDN
- S3 for media storage

## 🚀 Getting Started

### Prerequisites
```bash
node >= 14.x
npm >= 6.x
java >= 17
maven >= 3.x
```

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/citythrillsmorocco.git
cd citythrillsmorocco
```

2. **Backend Setup**
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

3. **Frontend Setup**
```bash
cd frontend
npm install
npm start
```

4. **Environment Configuration**
Create `.env` files in both frontend and backend directories:
```env
# Frontend
REACT_APP_API_URL=http://localhost:8080
REACT_APP_GOOGLE_MAPS_KEY=your_key_here

# Backend
SPRING_PROFILES_ACTIVE=dev
DATABASE_URL=your_db_url
```

## 📱 Responsive Design Features

Our platform implements responsive design through:

- **Fluid Grid System**
  - Dynamic columns and rows
  - Flexible images and media
  - Breakpoint-specific layouts

- **Mobile-First Approach**
  - Touch-optimized interfaces
  - Swipe gestures support
  - Optimized navigation patterns

- **Performance Optimization**
  - Lazy loading of images
  - Minified assets
  - Efficient caching strategies

## 🔒 Security Features

- JWT authentication
- OAuth2 integration
- HTTPS encryption
- XSS protection
- CSRF protection
- Input validation
- Rate limiting

## 📖 API Documentation

API documentation is available at:
```
http://localhost:8080/swagger-ui.html
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## 📞 Contact

- Website: [citythrillsmorocco.com](https://citythrillsmorocco.com)
- Email: contact@citythrillsmorocco.com
- Issue Tracker: [GitHub Issues](https://github.com/yourusername/citythrillsmorocco/issues)

---

<div align="center">
  Made with ❤️ in Morocco
</div>
