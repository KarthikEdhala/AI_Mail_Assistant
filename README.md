# AI Mail Assistant 🤖✉️

AI Mail Assistant is a Gmail Chrome extension powered by OpenAI that generates contextually appropriate email responses to help you communicate faster and more professionally.

[Project Link](https://github.com/KarthikEdhala/ai-mail-assistant)

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
    - [Clone the repository](#clone-the-repository)
    - [Backend Setup](#backend-setup)
    - [Chrome Extension Setup](#chrome-extension-setup)
    - [Load Extension in Chrome](#load-extension-in-chrome)
- [Usage](#usage)
- [Configuration](#configuration)
  - [Backend Configuration (`application.properties`)](#backend-configuration-applicationproperties)
  - [Extension Configuration](#extension-configuration)
- [API Endpoints](#api-endpoints)
  - [Generate Email Response](#generate-email-response)
- [Testing](#testing)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)
- [Contact](#contact)

## Overview
AI Mail Assistant analyzes the content and tone of incoming emails and generates context-aware draft responses. It integrates directly with Gmail through a Chrome extension and uses a Java Spring Boot backend to call the OpenAI API.

## Features
- Context-aware response generation
- Tone matching (formal, casual, friendly)
- One-click integration in Gmail
- Customizable and editable generated responses
- Time-saving AI-assisted composition

## Tech Stack
- Frontend: Chrome Extension API, JavaScript, HTML, CSS
- Backend: Java, Spring Boot, RESTful API
- AI: OpenAI API
- Build tools: Maven (backend), npm (extension)

## Architecture
```
┌─────────────────┐
│  Gmail UI       │
│  (Chrome Ext)   │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Java Backend   │
│  (Spring Boot)  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  OpenAI API     │
└─────────────────┘
```

## Getting Started

### Prerequisites
- Java 11 or higher
- Node.js and npm
- Chrome Browser
- OpenAI API Key

### Installation

#### Clone the repository
```bash
git clone https://github.com/KarthikEdhala/ai-mail-assistant.git
cd ai-mail-assistant
```

#### Backend Setup
1. Configure `application.properties` with your OpenAI API key:
```bash
cd backend
# add your OpenAI key to src/main/resources/application.properties
# Example:
# openai.api.key=YOUR_API_KEY
```

2. Build and run:
```bash
./mvnw clean install
./mvnw spring-boot:run
```
By default, the backend runs on port 8080. Update this in `application.properties` if needed.

#### Chrome Extension Setup
```bash
cd extension
npm install
npm run build
```
This creates the extension bundle under `extension/dist`.

#### Load Extension in Chrome
1. Open Chrome and go to `chrome://extensions/`
2. Enable "Developer mode"
3. Click "Load unpacked"
4. Select the `extension/dist` folder

## Usage
1. Open Gmail in Chrome.
2. Open any email thread.
3. Click the AI Mail Assistant icon (or the UI injected into the Gmail UI).
4. The extension will analyze the email and generate a draft response.
5. Review, edit if necessary, and send.

## Configuration

### Backend Configuration (`application.properties`)
```properties
server.port=8080
openai.api.key=your_openai_api_key
openai.model=gpt-4
openai.max.tokens=500
```

### Extension Configuration
In your extension source (e.g., `config.js`), point to the backend:
```javascript
const API_ENDPOINT = "http://localhost:8080/api/generate-response";
```
For production, use HTTPS and a secure endpoint.

## API Endpoints

### Generate Email Response
POST /api/generate-response

Request body:
```json
{
  "emailContent": "Original email text",
  "context": "Additional context (optional)",
  "tone": "professional" // e.g., professional, casual, friendly
}
```

Response:
```json
{
  "generatedResponse": "AI-generated email response",
  "confidence": 0.95
}
```

Notes:
- The backend should validate input size and sanitize content before sending to OpenAI.
- Consider rate-limiting and monitoring usage/cost.

## Testing
Backend:
```bash
cd backend
./mvnw test
```

Extension:
```bash
cd extension
npm test
```

## Security
- Store API keys securely (do not commit them to source control).
- Use HTTPS for all backend <-> extension communication in production.
- Do not persist or log sensitive email content unless explicitly required and consented to.
- Ensure compliance with Gmail API and Google policies if you later use an official Gmail API integration.

## Contributing
Contributions are welcome:

1. Fork the repository
2. Create a feature branch:
```bash
git checkout -b feature/AmazingFeature
```
3. Commit your changes:
```bash
git commit -m "Add some AmazingFeature"
```
4. Push to the branch:
```bash
git push origin feature/AmazingFeature
```
5. Open a Pull Request

Please follow the repository's code style and include tests for new features.

## License
This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

## Acknowledgments
- OpenAI for the GPT API
- Chrome Extensions documentation
- Spring Boot community

## Contact
Karthik Edhala — karthikedhala03@gmail.com

Project: https://github.com/KarthikEdhala/ai-mail-assistant

If you'd like, I can:
- commit this README.md directly to a branch and open a PR,
- or update any sections (examples, CI badges, screenshots) you want added.
