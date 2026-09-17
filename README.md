# Smart Traffic Control System

A Java-based Command Line Interface (CLI) application that simulates a smart traffic management system for multiple junctions. It monitors traffic density, manages vehicles, handles emergency priority, detects congestion, suggests alternative routes, and generates traffic reports.

## Features

- Control Room dashboard for monitoring junctions
- Multiple junctions and roads
- Car, Bike, Bus and Emergency Vehicle management
- Smart signal timing based on traffic density
- Emergency vehicle priority
- Traffic congestion detection
- Road blockage and reopening
- Alternative route suggestion
- Traffic statistics and report generation
- Multithreaded traffic simulation
- Synchronization and custom exception handling
- File I/O for traffic reports

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Collections Framework
- Multithreading
- Synchronization
- Exception Handling
- File I/O
- CLI

## Project Structure

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
└── traffic_report.txt
```

## Traffic Signal Logic

Signal timing is calculated according to traffic density:

| Traffic Level | Green Time |
|---|---:|
| Low | 20 seconds |
| Moderate | 40 seconds |
| High | 60 seconds |
| Emergency Vehicle | 60 seconds + Priority |

Road traffic is classified using occupancy:

- Below 50% → Low Traffic
- 50%–79% → Moderate Traffic
- 80% or above → High Traffic
- Closed Road → Road Closed

## Emergency Vehicle Priority

When an emergency vehicle is detected, the system:

1. Identifies the emergency vehicle.
2. Detects its road.
3. Changes the signal to GREEN.
4. Grants emergency priority.

## Alternative Route

If a road is highly congested or closed, the system checks other open roads and suggests an available road with lower traffic and sufficient capacity.

## Multithreading

The Live Simulation module runs multiple junction simulations concurrently using Java threads and the `Runnable` interface.

## Reports

Traffic statistics can be generated and saved to:

```text
traffic_report.txt
```

The report contains junction information, road-wise vehicle count, vehicle type statistics, total vehicles and emergency vehicle count.

## Requirements

- Java JDK 26 or compatible version
- Git
- Command Prompt / PowerShell / Terminal
- IntelliJ IDEA (optional)

Check Java installation:

```bash
java -version
javac -version
```

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/samriddhi25bai10185-bit/SmartTrafficControl.git
```

### 2. Open the Project

```bash
cd SmartTrafficControl
```

### 3. Compile

For Windows:

```bash
javac -d out src\model\*.java src\controller\*.java src\service\*.java src\simulation\*.java src\exception\*.java src\Main.java
```

### 4. Run

```bash
java -cp out Main
```

## Main Menu

```text
1. Control Room
2. Vehicle Management
3. Road & Traffic Status
4. Incident & Alerts
5. Analytics & Reports
6. Live Simulation
X. Exit
```

## Java Concepts Demonstrated

- Classes and Objects
- Encapsulation
- Abstraction
- Inheritance
- Polymorphism
- Method Overriding
- ArrayList and HashMap
- Exception Handling
- File Handling
- Multithreading
- Synchronization

## Future Enhancements

- GUI-based interface
- Database integration
- Real-time traffic sensors
- GPS-based route optimization
- AI-based traffic prediction
- Real-time map visualization

## Author

**Samriddhi Kesarwani**

GitHub: https://github.com/samriddhi25bai10185-bit

## Project Type

Academic Java Project  
**Application:** Smart Traffic Management  
**Interface:** Command Line Interface (CLI)
