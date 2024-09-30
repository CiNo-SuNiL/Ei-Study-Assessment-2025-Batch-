# Design Patterns Implementation in Java

This repository demonstrates the implementation of six design patterns across **Behavioral**, **Creational**, and **Structural** categories. The project is written in Java and follows best practices such as exception handling, logging, input validation, and optimized code structure.

## Project Structure

The project contains six use cases, each representing a different design pattern. The implementation of each pattern can be found in its respective class files, organized to ensure clean code and easy understanding.

### Design Patterns Implemented:

### 1. Observer Pattern (Stock Price Notification System)

This pattern allows multiple observers to subscribe to updates from a subject. In this implementation, when the stock price changes, all subscribed observers (such as a mobile app or web app) are notified.

**Key Classes**:
- `StockMarket` (Subject)
- `MobileApp` and `WebApp` (Observers)

---

### 2. Command Pattern (Home Automation System)

The Command pattern encapsulates requests as objects, allowing for command execution, undo/redo functionality, and command queuing. This implementation controls devices like lights and fans in a home automation system.

**Key Classes**:
- `LightOnCommand` and `FanOnCommand` (Concrete Commands)
- `RemoteControl` (Invoker)
- `Light` and `Fan` (Receivers)

---

### 3. Singleton Pattern (Database Connection Manager)

The Singleton pattern ensures only one instance of a class is created and provides a global access point to that instance. Here, it is used to manage a single database connection instance.

**Key Class**:
- `DatabaseConnection` (Singleton)

---

### 4. Factory Pattern (Shape Creation Tool)

The Factory pattern provides an interface for creating objects without specifying the exact class of the object that will be created. In this example, different shape objects are created based on user input.

**Key Classes**:
- `Shape`, `Circle`, `Square` (Products)
- `ShapeFactory` (Factory)

---

### 5. Adapter Pattern (Payment Gateway Adapter)

The Adapter pattern allows incompatible interfaces to work together. Here, the `PayPal` class is adapted to the `PaymentGateway` interface to allow seamless payment processing.

**Key Classes**:
- `PaymentGateway` (Target Interface)
- `PayPal` (Adaptee)
- `PayPalAdapter` (Adapter)

---

### 6. Decorator Pattern (Coffee Shop Order System)

The Decorator pattern dynamically adds behavior to objects. In this example, condiments like milk and sugar are added to a base coffee order dynamically.

**Key Classes**:
- `Coffee`, `SimpleCoffee` (Component)
- `CoffeeDecorator`, `MilkDecorator`, `SugarDecorator` (Decorators)

---

## Code Organization

Each design pattern has its own directory containing:
- **Interfaces**: Defines the base contract for the pattern.
- **Concrete Classes**: Implements the interfaces and pattern-specific behavior.
- **Test Classes**: Contains `main` methods to test and run the design patterns individually.
  
---

## Requirements

- **Java 8 or higher** installed.
- **Git** for version control (optional but recommended).

---

