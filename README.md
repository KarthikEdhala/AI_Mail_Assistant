# AI Mail Assistant

AI Mail Assistant is a lightweight Gmail Chrome extension that uses OpenAI to generate context-aware, tone-matched email drafts. It helps users reply faster while keeping replies professional and on-tone.

## What it does
- Analyzes incoming email content and thread context
- Generates editable reply drafts (formal, casual, or friendly)
- Inserts suggestions into Gmail for quick review and send

## Key use cases
- Quick replies to routine emails (scheduling, confirmations)
- Customer support templates and consistency
- Personalized sales follow-ups and outreach
- Recruiting messages (interviews, rejections, onboarding)
- Summarizing long threads and proposing concise actions

## Architecture (high level)
- Chrome Extension: injects UI, extracts email content, sends requests to backend
- Backend (Java + Spring Boot): builds prompts, calls OpenAI securely, returns drafts
- OpenAI: generates the reply text
- Optional: Gmail API for deep integration, caching/rate-limits for cost control

## Contact
Karthik Edhala — karthikedhala03@gmail.com
