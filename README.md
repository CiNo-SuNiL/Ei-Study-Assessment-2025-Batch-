# Coding Exercises and Mini-Project Repository

This repository contains solutions for two coding exercises and a mini-project. The first exercise demonstrates six use cases using behavioral, creational, and structural design patterns. The second exercise is a console-based "Smart Office Facility" application implementing Singleton, Factory, and Observer patterns.

## 📁 Folder 1: Design Pattern Exercises

### Description:
This folder contains six use cases that apply various design patterns, categorized as:

- **Creational Patterns**: Used to manage object creation mechanisms.
- **Structural Patterns**: Help organize classes and objects to form larger structures.
- **Behavioral Patterns**: Handle object communication and responsibility delegation.

### Features:
- **Use Case 1**: Implemented using the **Singleton** pattern.
- **Use Case 2**: Implemented using the **Factory** pattern.
- **Use Case 3**: Implemented using the **Observer** pattern.
- **Use Case 4**: Implemented using the **Decorator** pattern.
- **Use Case 5**: Implemented using the **Strategy** pattern.
- **Use Case 6**: Implemented using the **Command** pattern.

## 📁 Folder 2: Smart Office Facility Project

### Description:
This is a console-based application that simulates a smart office management system. It uses **Singleton**, **Factory**, and **Observer** design patterns to manage office resources like conference room booking, occupant management, and automatic control of room facilities.

### Features:
- **Room Configuration**: Configure the number of rooms and set maximum room capacity.
- **Occupant Management**: Manage occupants in each room, enabling control over room facilities (AC, lights).
- **Booking System**: Allows users to book and cancel rooms, ensuring only authorized users can cancel their bookings.
- **Real-time Monitoring**: Automatically checks room status for inactivity (e.g., if a room is unoccupied for more than 5 minutes).
- **User Authentication**: Restricts access to booking and room configuration features to authenticated users only.

### Patterns Used:
- **Singleton**: For managing the single instance of office configuration.
- **Factory**: For creating room objects dynamically.
- **Observer**: For notifying room occupancy changes.
