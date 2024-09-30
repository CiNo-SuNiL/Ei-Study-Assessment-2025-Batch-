# Smart Office Booking System

A terminal-based Java application for managing and booking conference rooms in a smart office. This system features room booking, occupant management, room capacity configuration, and user authentication to secure access to sensitive features.

## Features
- **Room Configuration**: Set up the number of rooms and their maximum capacities.
- **Room Booking**: Block a room for a specific time slot and duration.
- **Occupant Management**: Add or remove occupants from rooms.
- **User Authentication**: Users must log in to perform booking or cancel actions.
- **Booking Ownership**: Only the user who booked a room can cancel the booking.
- **Room Status Check**: Monitor whether rooms are occupied, and automatically mark them as unoccupied if left unused for more than 5 minutes.


## Usage
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/Smart-Office-Booking-System.git
   cd Smart-Office-Booking-System
   ```

2. **Run the Application**:
   Compile and run the Java files using:
   ```bash
   javac *.java
   java SmartOfficeApp
   ```

3. **Commands**:
   - `Config room count x`: Set up x number of rooms.
   - `Config room max capacity x y`: Set room x capacity to y.
   - `Add occupant x y`: Add y occupants to room x.
   - `Block room x HH:MM z`: Block room x from time HH:MM for z minutes.
   - `Cancel room x`: Cancel the booking for room x.
   - `Show booking x`: Display all bookings for room x.
   - `Room status x`: Display the status of room x.
   - `Exit`: Exit the application.

## Purpose of the Application
This application is designed for smart office management, allowing employees to easily book and manage conference rooms. It ensures smooth room utilization and prevents overbooking or conflicts. Only authorized users can access booking features, adding an extra layer of security.


## How to Contribute
- Fork the repository
- Create a new branch (`git checkout -b feature-branch`)
- Commit your changes (`git commit -m 'Add new feature'`)
- Push to the branch (`git push origin feature-branch`)
- Open a pull request
