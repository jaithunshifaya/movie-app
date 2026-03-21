# 🎬 CineVerse - Full Stack Movie Application

CineVerse is a full-stack movie application that allows users to search movies, view details, manage favorites, and track search history using the OMDb API.

---

## 🚀 Tech Stack

### 🔹 Frontend
- React JS
- Axios
- CSS

### 🔹 Backend
- Spring Boot
- Spring Security
- JWT Authentication
- REST APIs

### 🔹 Database
- MySQL

### 🔹 External API
- OMDb API

---

## 📂 Project Structure
movie-app/
├── Backend/
│ ├── src/
│ ├── pom.xml
│ └── application.properties
│
└── Frontend/
├── src/
├── package.json
└── public/


---

## 🔐 Features

- ✅ User Registration & Login (JWT Auth)
- 🔍 Movie Search (OMDb API)
- 🎬 Movie Details
- ❤️ Add / Remove Favorites
- 🕘 Search History Tracking
- 🔒 Secure API with Token-based Authentication

---

## 🔑 OMDb API Setup

1. Go to: https://www.omdbapi.com/apikey.aspx
2. Get your API key: omdb.api.key=YOUR_API_KEY
3. Add in `application.properties`:

---

## ⚙️ Backend Setup (Spring Boot)

### 🔹 Step 1: Configure Database

spring.datasource.url=jdbc:mysql://localhost:3306/movie_app
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

---

### 🔹 Step 2: Run Backend

cd Backend
mvn spring-boot:run 

Server runs on:

http://localhost:8080

## 💻 Frontend Setup (React)
cd Frontend
npm install
npm start

App runs on:

http://localhost:3000

---

## 🔐 JWT Authentication Flow
- User logs in
- Backend returns JWT token
- Token stored in localStorage
- Every API request includes:
- Authorization: Bearer <token>


---

## 📬 API Endpoints (Postman)

### 🔹 Auth APIs

| Method | Endpoint         | Description        |
|--------|----------------|------------------|
| POST   | /auth/register | Register user     |
| POST   | /auth/login    | Login & get token|

---

### 🔹 Movie APIs

| Method | Endpoint                         | Description        |
|--------|----------------------------------|--------------------|
| GET    | /movies?name=batman              | Search movies      |
| GET    | /movies/details?id=tt1234567     | Movie details      |

---

### 🔹 Favorites APIs

| Method | Endpoint            | Description         |
|--------|--------------------|---------------------|
| POST   | /favorites         | Add favorite        |
| GET    | /favorites         | Get all favorites   |
| DELETE | /favorites/{id}    | Delete favorite     |
| PUT    | /favorites/{id}    | Update favorite     |

---

### 🔹 History APIs

| Method | Endpoint                       | Description              |
|--------|--------------------------------|--------------------------|
| GET    | /movies/history               | Get search history       |
| DELETE | /movies/history/{id}          | Delete one history       |
| DELETE | /movies/history/clear         | Clear all history        |

---

## 🧪 Swagger Setup (Optional)

Add this dependency in `pom.xml`:

<dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId> <version>2.5.0</version> </dependency> ```

Access Swagger UI:

http://localhost:8080/swagger-ui.html

---

## 🌍 Deployment Guide
### 🔹 Backend Deployment (Render)
1. Push Backend to GitHub
2. Go to https://render.com
3. Create a Web Service
4. Connect GitHub repo

### 🔹 Frontend (Vercel)
1. Go to https://vercel.com
2. Import GitHub repo
3. Select Frontend folder

### ⚠️ Important Notes
- Always send JWT token in headers
- Do NOT commit API keys publicly
- Use .env for frontend configs

---

## 👨‍💻 Author

Jaithun Shifaya

---

## ⭐ Acknowledgment
- OMDb API
- Spring Boot Docs
- React Docs

---

## 🚀 Future Improvements
- 🎥 Watchlist Feature
- 📊 Movie Recommendations (AI)
- 🌐 Multi-language support

---

