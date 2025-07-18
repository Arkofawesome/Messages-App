# Multi-Client Direct Messaging App (Java)

A simple, multi-threaded socket-based application that enables real-time direct messaging between multiple clients. Built in Java using basic networking and threading concepts.

---

## Features

- 📡 Server accepts multiple clients via sockets
- 💬 Real-time direct messaging between clients
- 🔒 Thread-safe communication handling
- 🧵 Each client runs in a separate thread
- 🛠️ Simple and lightweight Java implementation

---

## Tech Stack

- Java (Core)
- Java Sockets (`java.net`)
- Multi-threading (`java.lang.Thread`)

---

## Project Structure

```bash
.
├── src/
│   ├── Server.java        # Starts the server and listens for client connections
│   ├── ClientHandler.java # Handles individual client communication (threaded)
│   └── Client.java        # Client-side application for sending/receiving messages
└── README.md
