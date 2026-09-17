# 🚦 Smart Traffic Control System

A Java-based Command Line Interface (CLI) application that simulates a smart traffic management system for multiple junctions. The system monitors traffic density, manages vehicles, controls traffic signals, provides emergency vehicle priority, detects congestion, suggests alternative routes, and generates traffic reports.

## 📌 Project Overview

The **Smart Traffic Control System** simulates the basic working of a centralized traffic management system.

The application manages:

- Multiple traffic junctions and roads
- Different types of vehicles
- Traffic signal control
- Emergency vehicle priority
- Traffic congestion
- Road blockages
- Alternative route suggestions
- Traffic statistics and reports
- Multithreaded traffic simulation

## ✨ Features

### 🖥️ Control Room

A centralized dashboard to monitor:

- Junction status
- Signal status
- Green signal timing
- Total vehicles
- Emergency vehicle presence
- Overall traffic condition
- Road-wise traffic status

### 🚗 Vehicle Management

The system supports:

- Car
- Bike
- Bus
- Emergency Vehicle

Users can add, view, and remove vehicles.

### 🛣️ Road & Traffic Monitoring

Each road has a defined capacity and current vehicle count.

Traffic is classified as:

- LOW TRAFFIC
- MODERATE TRAFFIC
- HIGH TRAFFIC
- ROAD CLOSED

### 🚨 Emergency Vehicle Priority

When an emergency vehicle is detected:

1. The vehicle is identified.
2. Its road is detected.
3. The signal changes to GREEN.
4. Emergency priority is granted.

### 🚦 Smart Signal Timing

Signal timing is calculated according to traffic density.

| Traffic Level | Green Time |
|---|---:|
| Low | 20 seconds |
| Moderate | 40 seconds |
| High | 60 seconds |
| Emergency Vehicle | 60 seconds + Priority |

### 💡 Alternative Route Suggestion

If a road is highly congested or closed, the system checks other available roads and suggests a suitable alternative route based on traffic and available capacity.

### 📊 Analytics & Reports

The system generates traffic statistics including:

- Road-wise vehicle count
- Total vehicles
- Vehicle type statistics
- Emergency vehicle count
- Junction information

Reports are saved in:

```text
traffic_report.txt
```

### 🧵 Multithreaded Simulation

The Live Simulation module runs multiple junction simulations concurrently using Java threads and the `Runnable` interface.

### 🔒 Synchronization & Exception Handling

The project uses synchronized methods for vehicle operations and custom exceptions for invalid traffic operations such as adding a vehicle to a closed or full road.

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Collections Framework
- Multithreading
- Synchronization
- Exception Handling
- File I/O
- Command Line Interface (CLI)

## 🧠 Java Concepts Demonstrated

- Classes and Objects
- Encapsulation
- Abstraction
- Inheritance
- Polymorphism
- Method Overriding
- Constructors
- ArrayList
- HashMap
- Exception Handling
- File Handling
- Multithreading
- Synchronization

## 📂 Project Structure

```text
SmartTrafficControl/
│
├── src/
│   ├── controller/
│   ├── exception/
│   ├── model/
│   ├── service/
│   ├── simulation/
│   └── Main.java
│
├── README.md
├── statement.md
└── traffic_report.txt
```

## 💻 Requirements

- Java JDK 26 or compatible version
- Git
- Command Prompt / PowerShell / Terminal
- IntelliJ IDEA (optional)

Check Java installation:

```bash
java -version
javac -version
```

## ▶️ How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/samriddhi25bai10185-bit/SmartTrafficControl.git
```

### 2. Open the Project

```bash
cd SmartTrafficControl
```

### 3. Compile the Project

For Windows:

```bash
javac -d out src\model\*.java src\controller\*.java src\service\*.java src\simulation\*.java src\exception\*.java src\Main.java
```

### 4. Run the Application

```bash
java -cp out Main
```

## 🎮 Main Menu

```text
1. Control Room
2. Vehicle Management
3. Road & Traffic Status
4. Incident & Alerts
5. Analytics & Reports
6. Live Simulation
X. Exit
```

## 🧪 Testing

The application can be tested through the CLI by performing the following checks:

1. Add different vehicle types and verify they appear in the vehicle list.
2. Try adding a vehicle to a closed or full road and verify the error message.
3. Add an emergency vehicle and verify that emergency priority is granted.
4. Check roads with different traffic densities and verify traffic status and signal timing.
5. Close a road and verify that an alternative route is suggested.
6. Run Live Simulation and verify that multiple junctions are processed using separate threads.
7. Generate a traffic report and verify that `traffic_report.txt` is updated.

## 🔄 Working Flow

```text
Start
  ↓
Initialize Junctions & Roads
  ↓
Manage Vehicles
  ↓
Monitor Traffic Density
  ↓
Calculate Signal Timing
  ↓
Check Emergency Vehicle
  ↓
Detect Congestion
  ↓
Suggest Alternative Route
  ↓
Generate Traffic Report
  ↓
End
```

## 🎯 Project Objectives

- Monitor traffic across multiple junctions.
- Manage different types of vehicles and roads.
- Calculate signal timing based on traffic density.
- Provide priority to emergency vehicles.
- Detect traffic congestion.
- Suggest alternative routes.
- Demonstrate Java OOP concepts.
- Implement multithreading and synchronization.
- Use exception handling and file I/O.
- Generate traffic statistics.

## 🔮 Future Enhancements

- GUI-based interface
- Database integration
- Real-time traffic sensors
- GPS-based route optimization
- AI-based traffic prediction
- Real-time map visualization

## 👩‍💻 Author

**Samriddhi Kesarwani**

GitHub:  
https://github.com/samriddhi25bai10185-bit

## 📌 Project Type

**Academic Java Project**

**Application:** Smart Traffic Management  
**Interface:** Command Line Interface (CLI)
