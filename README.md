# ChatApp

A console-based Java application built for the IIE POE assignment. ChatApp allows users to register an account, log in securely, and send, store, or disregard messages.

## Features

- **Registration & Login**: Username, password, and cell phone number validation with custom rules (e.g. username must contain an underscore and be 5 characters or fewer, password must include a capital letter, digit, and special character).
- **Messaging**: Send up to a chosen number of messages per session, with recipient and length validation. Each message generates a unique ID and hash.
- **Message Management**: Send, store, or disregard messages. Stored messages are saved to a JSON file (`chat.json`).
- **Reporting**: View sender/recipient pairs, find the longest message, search by Message ID or recipient, delete messages by hash, and view a full message report.
- **Unit Testing**: JUnit tests cover validation logic, message ID/hash generation, and login functionality.

## Project Structure

- `ChatApp.java` – main program entry point and menu logic
- `LoginFeature.java` – handles registration, login, and validation
- `Message.java` – handles message creation, ID/hash generation, and storage

## Author

modibakamogelo95
