# realtime-chat-application
Developed a real-time chat app using Java, Swing, and socket programming with a multithreaded client-server architecture. Designed intuitive GUIs with timestamped messages and tested deployment locally, separating UI and networking logic for flexibility and scalability.

# Realtime Chat Application

A GUI-based real-time chat application built using **Java**, **Swing**, and **Socket Programming**. This application allows two users (client and server) to communicate through a local network using a custom-designed interface.

## Tech Stack

- **Programming Language:** Java  
- **GUI Framework:** Java Swing  
- **Networking:** Socket, ServerSocket, DataInputStream, DataOutputStream  
- **Concurrency:** Java Multithreading

## Features

- Real-time bi-directional chat between server and client  
- Custom-designed chat interface using Swing  
- Timestamped messages with dynamic chat display  
- Modular separation of GUI and network logic  
- Profile pictures and headers for a user-friendly experience

## Project Structure

Server.java     // Server-side logic with GUI  
Client.java     // Client-side logic with GUI  
resources/      // Profile images used in the UI

## Getting Started

Prerequisites
Java JDK (8 or above)
Java-compatible IDE (e.g., IntelliJ IDEA or VS Code)

Compile the files:

javac Server.java  
javac Client.java

Run the Server
Run the Client (in a separate terminal or machine)

Make sure both client and server run on the same network or update IP addresses in the code for remote use.
