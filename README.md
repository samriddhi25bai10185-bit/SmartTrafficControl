# 🚦 Smart Traffic Control System

A Java-based Command Line Interface (CLI) application that simulates a smart traffic management system for multiple junctions. The system monitors traffic density, manages different types of vehicles, detects congestion, provides emergency vehicle priority, suggests alternative routes, and generates traffic reports.

---

## 📌 Project Overview

The **Smart Traffic Control System** is designed to simulate the basic working of a centralized traffic control system.

The application manages:

- Multiple traffic junctions
- Multiple roads
- Different types of vehicles
- Traffic signal control
- Emergency vehicle priority
- Traffic congestion
- Road blockages
- Alternative route suggestions
- Traffic statistics
- Multithreaded traffic simulation

The project is completely **CLI-based** and can be executed from the command line.

---

## ✨ Key Features

### 🖥️ 1. Control Room

Provides a centralized monitoring dashboard for all junctions.

It displays:

- Junction status
- Traffic signal status
- Green signal timing
- Total vehicles
- Emergency vehicle presence
- Overall traffic condition
- Road-wise traffic status

---

### 🚗 2. Vehicle Management

The system supports different vehicle types:

- Car
- Bike
- Bus
- Emergency Vehicle

Users can:

- Add vehicles
- View vehicles
- Remove vehicles when they exit the road

Each vehicle has its own ID, type, speed, and emergency status.

---

### 🛣️ 3. Road & Traffic Management

Each road has:

- Road ID
- Road name
- Maximum capacity
- Current vehicle count
- Open/closed status

The system calculates road occupancy and classifies traffic as:

- LOW TRAFFIC
- MODERATE TRAFFIC
- HIGH TRAFFIC
- ROAD CLOSED

---

### 🚨 4. Emergency Vehicle Priority

Emergency vehicles receive the highest priority.

When an emergency vehicle is detected:

1. The system identifies the emergency vehicle.
2. Its current road is displayed.
3. The traffic signal is changed to GREEN.
4. Emergency priority is granted.

This simulates priority movement for ambulances and other emergency services.

---

### 🚦 5. Smart Signal Timing

Signal timing is calculated according to the current traffic density.

| Traffic Condition | Green Time |
|-------------------|------------|
| Low Traffic | 20 seconds |
| Moderate Traffic | 40 seconds |
| High Traffic | 60 seconds |
| Emergency Vehicle | 60 seconds + Priority |

The system also changes the signal state according to traffic conditions.

---

### 💡 6. Alternative Route Suggestion

When a road is highly congested or closed, the system searches for another available road.

It considers:

- Road availability
- Available capacity
- Current traffic occupancy

The road with lower traffic and available capacity is suggested as an alternative route.

---

### ⚠️ 7. Incident & Alerts

The system can detect traffic-related conditions such as:

- Emergency vehicles
- High traffic
- Road blockage
- Closed roads
- Lack of alternative routes

---

### 📊 8. Analytics & Reports

The system generates traffic statistics including:

- Road-wise vehicle count
- Total vehicles
- Vehicle type statistics
- Emergency vehicle count
- Junction information

Reports are automatically stored in:

```text
traffic_report.txt
