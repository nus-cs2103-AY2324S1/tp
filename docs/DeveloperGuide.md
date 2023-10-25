# CheckMate User Guide

## Table of Contents
1. Introduction
    - About CheckMate
    - Target Users
    - Value Proposition
2. Getting Started
    - Installation
    - Command Format
    - Acceptable Parameter Values
3. Features
    1. **Booking Management**
        - Add Bookings
        - Cancel a Booking
    2. **Room Data Visualization**
        - View Room Availability
        - View All Bookings
    3. **Search Functionality**
        - Search for a Booking
4. User Guide for Features
    - Detailed instructions for each feature
5. Non-Functional Requirements
    - System Requirements
    - Performance Expectations
    - User Typing Speed
6. Glossary
    - Key Terminology

---

## 1. Introduction

### About CheckMate
CheckMate is a powerful room booking and management system designed for hotel employees, especially those in administrative and management positions. It streamlines the process of room bookings, offering real-time room data visualization, search functionality, and efficient booking management.

### Target Users
Hotel employees, especially those in administrative and management roles.

### Value Proposition
CheckMate empowers hotel employees to efficiently manage room bookings, optimize room-booking matching, and enhance guest experiences. It offers real-time room availability, service scheduling, and amenity management. It is optimized for administrative roles, ensuring seamless room allocation and guest service.

## 2. Getting Started

### Installation
CheckMate runs on any mainstream operating system with Java 11 or above installed.

### Command Format
Commands in CheckMate follow a specific format. Parameters are enclosed in square brackets, and acceptable values are specified for each parameter.

### Acceptable Parameter Values
- ROOM_NUMBER: Any positive integer from 0 to 500 (Maximum).
- DATE: YYYY-MM-DD.
- START_TIME-END_TIME: HH:MM-HH:MM.

## 3. Features

### 1. Booking Management

#### Add Bookings
Allows hotel receptionists to add new room bookings upon confirmation of a room reservation.

**Command Format:** `add booking [r/ROOM_NUMBER] [d/DATE] [t/START_TIME-END_TIME]`

**Example Command:** `add booking r/101 d/2023-09-16 t/09:00-12:00`

**Expected Outputs (Success):** "Booking added successfully for Room ROOM_NUMBER on DATE from START_TIME to END_TIME."

**Expected Outputs (Failure):**
- Invalid ROOM_NUMBER: "Error: Invalid room number."
- Room already booked: "Error: Room ROOM_NUMBER is already booked during this time slot."
- Missing parameter: "Error: Missing parameter PARAMETER_NAME."

#### Cancel a Booking
Provides hotel receptionists with the capability to cancel an existing booking efficiently.

**Command Format:** `cancelBooking [booking_id]`

**Example Command:** `cancelBooking 12345`

**Expected Outputs (Success):** "Booking 12345 has been cancelled."

**Expected Outputs (Failure):**
- Invalid booking_id: "Error: Invalid booking ID."
- Booking not found: "Error: No booking found with the given ID."

### 2. Room Data Visualization

#### View Room Availability
Allows hotel receptionists to view rooms that are available on a specified date.

**Command Format:** `view availability d/DATE`

**Example Command:** `view availability d/2023-09-16`

**Expected Outputs (Success):**
```
Available rooms:
#01-001
#02-002
#03-003
```


**Expected Outputs (Failure):**
- Invalid date: "Error: Invalid date format."
- All rooms booked: "All rooms are booked on [date]."

#### View All Bookings
Displays a comprehensive list of all bookings made to help hotel receptionists prepare for guest arrivals.

**Command Format:** `view all bookings`

**Expected Outputs (Success):**
```
Booking ID: 12345
Room Number: 101
Date: 2023-09-16
Time Slot: 09:00 - 12:00
```
```
Booking ID: 12346
Room Number: 102
Date: 2023-09-18
Time Slot: 14:00 - 16:00
```

**Expected Outputs (Failure):** "No bookings available to display."

### 3. Search Functionality

#### Search for a Booking
Allows hotel receptionists to efficiently search and locate specific bookings based on various criteria.

**Command Format:** `find [KEYWORD]`

**Example Commands:**
- `find 102`
- `find jihoon`
- `find 102 jihoon`

**Expected Outputs (Success):** A list of bookings that match the search criteria.

**Expected Outputs (Failure):**
- Invalid parameters: "Invalid command format! "
- No matching bookings: "0 bookings listed!"

## 4. User Guide for Features
Detailed instructions for using each feature can be found in the respective sections above.

## 5. Non-Functional Requirements

### System Requirements
- CheckMate works on any mainstream OS with Java 11 or above.

### Performance Expectations
- It can hold up to 1000 bookings without noticeable sluggishness in performance for typical usage.

### User Typing Speed
- A user with above-average typing speed for regular English text should be able to accomplish tasks faster using commands than using the mouse.

## 6. Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X.
- **Private contact detail**: A contact detail not meant to be shared with others.

---

This user guide provides an overview of CheckMate and detailed instructions for its features. It empowers hotel employees to efficiently manage room bookings and enhance guest experiences.
