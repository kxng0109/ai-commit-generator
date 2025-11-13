# AI Commit Generator (Backend) - Deprecated

A Spring Boot backend service that was originally designed to generate Conventional Commit messages from git diff using Spring AI and OpenAI.

## Project Status

**This project has been deprecated and is no longer maintained.**

## Overview

This repository contains a Spring Boot backend service that was intended to work as a server-side component for a CLI tool. The service would receive git diff data and use AI to generate conventional commit messages.

## Issues That Led to Deprecation

During development, several critical design flaws were identified:

1. **API Key Security**: The backend service required hosting with a centralized API key, creating a security risk and single point of failure.

2. **Limited AI Provider Support**: The implementation was tightly coupled to OpenAI, preventing users from choosing their preferred AI provider.

3. **Lack of Flexibility**: Users could not configure the service to work with:
    - Ollama (local models)
    - Anthropic Claude
    - Azure OpenAI
    - Google Gemini
    - Other AI providers

4. **Unnecessary Architecture Complexity**: A client-server architecture introduced unnecessary network latency and deployment overhead for what could be accomplished locally.

## Migration

This project has been superseded by a new standalone CLI application that addresses all the above issues:

**[ai-commit-cli](https://github.com/kxng0109/ai-commit-cli)**

The new implementation:
- Runs entirely locally, eliminating the need for a backend service
- Allows users to configure their own API keys securely
- Supports multiple AI providers (OpenAI, Ollama, Anthropic, Azure, Gemini)
- Uses GraalVM for native compilation, providing fast startup and low memory footprint
- Includes automated CI/CD pipelines for cross-platform releases

## License

MIT License - See [LICENSE](LICENSE) file for details.