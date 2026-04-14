# 🚀 AI Resume Analyzer - Backend

## 📌 Overview
This is the backend service of the AI Resume Analyzer project.
It analyzes resumes and matches them with job descriptions using skill extraction and AI-based suggestions.

## 🛠 Tech Stack
* Java 17
* Spring Boot
* REST API
* PDFBox (for resume parsing)
* OpenAI API (for suggestions)

## ⚙️ Features
* 📄 Upload Resume (PDF)
* 🧠 Extract Skills from Resume
* 🔍 Match Skills with Job Description
* 📊 Generate Match Score
* 🎯 Detect Job Role
* 💡 AI-based Suggestions

## 🔗 API Endpoints

### 1. Upload Resume
POST `/upload`
* Upload a PDF resume file
  
### 2. Match Skills
POST `/match`
* Body: Plain text job description

## 🌐 Live Backend URL

https://resume-analyzer-backend-2-xc71.onrender.com

## ▶️ Run Locally

```bash
mvn spring-boot:run
```

## ⚠️ Environment Variables

```
OPENAI_API_KEY=your_api_key_here
```

## 📌 Notes

* Upload resume before calling `/match`
* Ensure API key is set for AI suggestions

---
