import controller.TrafficController;
import model.*;
import service.TrafficService;
import service.TrafficStatistics;
import simulation.TrafficSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // ==================== COLORS ====================

    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";


    // ==================== OBJECTS ====================

    static Scanner scanner = new Scanner(System.in);

    static TrafficService trafficService =
            new TrafficService();

    static TrafficController controller =
            new TrafficController();

    static TrafficStatistics statistics =
            new TrafficStatistics();


    // ==================== JUNCTIONS ====================

    static Junction central =
            new Junction("J1", "Central Junction");

    static Junction airport =
            new Junction("J2", "Airport Junction");


    // ==================== VEHICLE IDs ====================

    static int carId = 103;
    static int bikeId = 102;
    static int busId = 102;
    static int emergencyId = 102;


    // ==================== MAIN ====================

    public static void main(String[] args) {

        setupSystem();

        while (true) {

            showMainMenu();

            String choice =
                    scanner.nextLine();

            switch (choice.toUpperCase()) {

                case "1":
                    controlRoom();
                    break;

                case "2":
                    vehicleManagement();
                    break;

                case "3":
                    roadTrafficStatus();
                    break;

                case "4":
                    incidentAlerts();
                    break;

                case "5":
                    analyticsReports();
                    break;

                case "6":
                    startSimulation();
                    break;

                case "X":
                    System.out.println();
                    System.out.println(
                            GREEN +
                                    "Thank you for using Smart Traffic Control System!"
                                    + RESET);
                    return;

                default:
                    System.out.println(
                            RED + "\nInvalid choice."
                                    + RESET);
            }
        }
    }


    // ==================== MAIN MENU ====================

    static void showMainMenu() {

        System.out.println();

        System.out.println(
                CYAN +
                        "╔════════════════════════════════════════════╗"
                        + RESET);

        System.out.println(
                CYAN +
                        "║        🚦 SMART TRAFFIC SYSTEM             ║"
                        + RESET);

        System.out.println(
                CYAN +
                        "╠════════════════════════════════════════════╣"
                        + RESET);

        System.out.println(
                "║                                            ║");

        System.out.println(
                "║   1. 🖥️  Control Room                     ║");

        System.out.println(
                "║   2. 🚗  Vehicle Management                ║");

        System.out.println(
                "║   3. 🛣️  Road & Traffic Status             ║");

        System.out.println(
                "║   4. 🚨  Incident & Alerts                 ║");

        System.out.println(
                "║   5. 📊  Analytics & Reports               ║");

        System.out.println(
                "║   6. ▶️  Live Simulation                   ║");

        System.out.println(
                "║                                            ║");

        System.out.println(
                "║   X. ❌  Exit                              ║");

        System.out.println(
                "║                                            ║");

        System.out.println(
                CYAN +
                        "╚════════════════════════════════════════════╝"
                        + RESET);

        System.out.print(
                "\nEnter choice: ");
    }


    // ==================== SETUP ====================

    static void setupSystem() {

        Road mainRoad =
                new Road(
                        "R1",
                        "Main Road",
                        10);

        Road marketRoad =
                new Road(
                        "R2",
                        "Market Road",
                        8);

        central.addRoad(mainRoad);
        central.addRoad(marketRoad);


        Road airportRoad =
                new Road(
                        "R3",
                        "Airport Road",
                        12);

        Road highwayRoad =
                new Road(
                        "R4",
                        "Highway Road",
                        15);

        airport.addRoad(airportRoad);
        airport.addRoad(highwayRoad);


        Car car101 =
                new Car("CAR101", 60);

        EmergencyVehicle emergency101 =
                new EmergencyVehicle(
                        "EMG101",
                        80,
                        "Ambulance");

        Bike bike101 =
                new Bike("BIKE101", 70);

        Bus bus101 =
                new Bus("BUS101", 40);

        Car car102 =
                new Car("CAR102", 65);


        try {

            trafficService.addVehicleToRoad(
                    mainRoad,
                    car101);

            trafficService.addVehicleToRoad(
                    mainRoad,
                    emergency101);

            trafficService.addVehicleToRoad(
                    marketRoad,
                    bike101);

            trafficService.addVehicleToRoad(
                    airportRoad,
                    bus101);

            trafficService.addVehicleToRoad(
                    airportRoad,
                    car102);

        } catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }


    // ==================== CONTROL ROOM ====================

    static void controlRoom() {

        while (true) {

            System.out.println();

            System.out.println(
                    CYAN +
                            "╔════════════════════════════════════════════╗"
                            + RESET);

            System.out.println(
                    CYAN +
                            "║          🚦 TRAFFIC CONTROL ROOM          ║"
                            + RESET);

            System.out.println(
                    CYAN +
                            "╠════════════════════════════════════════════╣"
                            + RESET);

            System.out.println(
                    "║                                            ║");

            controller.calculateSignalTime(central);
            controller.calculateSignalTime(airport);

            displayControlRoomJunction(central);

            displayControlRoomJunction(airport);

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    CYAN +
                            "╚════════════════════════════════════════════╝"
                            + RESET);

            System.out.println();

            System.out.println(
                    "              X. ↩ Back");

            System.out.print(
                    "\nEnter choice: ");

            String choice =
                    scanner.nextLine();

            if (choice.equalsIgnoreCase("X")) {
                return;
            }

            System.out.println(
                    RED + "Invalid choice."
                            + RESET);
        }
    }


    // ==================== CONTROL ROOM DISPLAY ====================

    static void displayControlRoomJunction(
            Junction junction) {

        int totalVehicles =
                junction.getTotalVehicles();

        boolean emergency =
                controller.hasEmergencyVehicle(
                        junction);

        String signal =
                junction.getSignal()
                        .getCurrentSignal();

        String signalCircle;

        if (signal.equals("GREEN")) {

            signalCircle =
                    GREEN + "🟢" + RESET;

        } else if (signal.equals("YELLOW")) {

            signalCircle =
                    YELLOW + "🟡" + RESET;

        } else {

            signalCircle =
                    RED + "🔴" + RESET;
        }


        String trafficStatus;

        if (totalVehicles >= 8) {

            trafficStatus = "HIGH";

        } else if (totalVehicles >= 4) {

            trafficStatus = "MODERATE";

        } else {

            trafficStatus = "LOW";
        }


        System.out.println();

        System.out.println(
                "║  📍 " + junction.getName());

        System.out.println(
                "║  ────────────────────────────────────────");

        System.out.println(
                "║  Signal Status      : " + signalCircle);

        System.out.println(
                "║  Green Time         : "
                        + junction.getSignal()
                        .getGreenTime()
                        + " seconds");

        System.out.println(
                "║  Total Vehicles     : "
                        + totalVehicles);

        System.out.println(
                "║  Emergency Vehicle  : "
                        + (emergency
                        ? GREEN + "🚨 YES" + RESET
                        : "NO"));

        System.out.println(
                "║  Overall Traffic    : "
                        + trafficStatus);

        System.out.println();

        System.out.println(
                "║  🛣️ Road-wise Status:");

        for (Road road :
                junction.getRoads()) {

            String circle;

            double usage =
                    road.getOccupancyPercentage();

            if (!road.isOpen()) {

                circle =
                        RED + "🔴" + RESET;

            } else if (usage >= 80) {

                circle =
                        RED + "🔴" + RESET;

            } else if (usage >= 50) {

                circle =
                        YELLOW + "🟡" + RESET;

            } else {

                circle =
                        GREEN + "🟢" + RESET;
            }

            System.out.println(
                    "║     "
                            + road.getName()
                            + " : "
                            + road.getVehicleCount()
                            + "/"
                            + road.getCapacity()
                            + "  "
                            + circle
                            + " "
                            + getTrafficText(road));
        }
    }


    static String getTrafficText(
            Road road) {

        if (!road.isOpen()) {

            return "CLOSED";

        }

        double usage =
                road.getOccupancyPercentage();

        if (usage >= 80) {

            return "HIGH";

        } else if (usage >= 50) {

            return "MODERATE";

        } else {

            return "LOW";
        }
    }


    // ==================== VEHICLE MANAGEMENT ====================

    static void vehicleManagement() {

        while (true) {

            System.out.println();

            System.out.println(
                    CYAN +
                            "╔════════════════════════════════════════════╗"
                            + RESET);

            System.out.println(
                    CYAN +
                            "║          🚗 VEHICLE MANAGEMENT             ║"
                            + RESET);

            System.out.println(
                    CYAN +
                            "╠════════════════════════════════════════════╣"
                            + RESET);

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   1. ➕ Add Vehicle                        ║");

            System.out.println(
                    "║   2. 👀 View Vehicles                      ║");

            System.out.println(
                    "║   3. 🚗 Vehicle Exit                       ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   X. ↩ Back                                ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    CYAN +
                            "╚════════════════════════════════════════════╝"
                            + RESET);

            System.out.print(
                    "\nEnter choice: ");

            String choice =
                    scanner.nextLine();

            switch (choice.toUpperCase()) {

                case "1":
                    addVehicle();
                    break;

                case "2":
                    viewVehicles();
                    break;

                case "3":
                    vehicleExit();
                    break;

                case "X":
                    return;

                default:
                    System.out.println(
                            RED + "Invalid choice."
                                    + RESET);
            }
        }
    }


    // ==================== ADD VEHICLE ====================

    static void addVehicle() {

        System.out.println();

        System.out.println(
                "╔════════════════════════════════════════════╗");

        System.out.println(
                "║              ➕ ADD VEHICLE                ║");

        System.out.println(
                "╠════════════════════════════════════════════╣");

        System.out.println(
                "║   1. 🚗 Car                                ║");

        System.out.println(
                "║   2. 🏍️ Bike                               ║");

        System.out.println(
                "║   3. 🚌 Bus                                ║");

        System.out.println(
                "║   4. 🚑 Emergency Vehicle                  ║");

        System.out.println(
                "╚════════════════════════════════════════════╝");

        System.out.print(
                "\nEnter choice: ");

        String type =
                scanner.nextLine();


        System.out.println();

        System.out.println(
                "Select Junction:");

        System.out.println(
                "1. 📍 Central Junction");

        System.out.println(
                "2. 📍 Airport Junction");

        System.out.print(
                "Enter choice: ");

        String junctionChoice =
                scanner.nextLine();

        Junction junction;

        if (junctionChoice.equals("1")) {

            junction = central;

        } else if (junctionChoice.equals("2")) {

            junction = airport;

        } else {

            System.out.println(
                    RED + "Invalid junction."
                            + RESET);

            return;
        }


        System.out.println();

        System.out.println(
                "Select Road:");

        for (int i = 0;
             i < junction.getRoads().size();
             i++) {

            Road road =
                    junction.getRoads().get(i);

            System.out.println(
                    (i + 1)
                            + ". 🛣️ "
                            + road.getName());
        }

        System.out.print(
                "Enter choice: ");

        int roadChoice;

        try {

            roadChoice =
                    Integer.parseInt(
                            scanner.nextLine());

        } catch (Exception e) {

            System.out.println(
                    RED + "Invalid choice."
                            + RESET);

            return;
        }


        if (roadChoice < 1 ||
                roadChoice >
                        junction.getRoads().size()) {

            System.out.println(
                    RED + "Invalid road."
                            + RESET);

            return;
        }


        Road road =
                junction.getRoads()
                        .get(roadChoice - 1);

        Vehicle vehicle = null;


        try {

            if (type.equals("1")) {

                vehicle =
                        new Car(
                                "CAR" + carId,
                                60);

                carId++;

            } else if (type.equals("2")) {

                vehicle =
                        new Bike(
                                "BIKE" + bikeId,
                                70);

                bikeId++;

            } else if (type.equals("3")) {

                vehicle =
                        new Bus(
                                "BUS" + busId,
                                40);

                busId++;

            } else if (type.equals("4")) {

                vehicle =
                        new EmergencyVehicle(
                                "EMG" + emergencyId,
                                80,
                                "Ambulance");

                emergencyId++;

            } else {

                System.out.println(
                        RED + "Invalid vehicle type."
                                + RESET);

                return;
            }


            trafficService.addVehicleToRoad(
                    road,
                    vehicle);


            if (vehicle.isEmergency()) {

                controller.checkEmergencyVehicle(
                        junction);
            }

        } catch (Exception e) {

            System.out.println(
                    RED + e.getMessage()
                            + RESET);
        }
    }


    // ==================== VIEW VEHICLES ====================

    static void viewVehicles() {

        System.out.println();

        System.out.println(
                CYAN +
                        "╔════════════════════════════════════════════╗"
                        + RESET);

        System.out.println(
                CYAN +
                        "║             👀 ALL VEHICLES                ║"
                        + RESET);

        System.out.println(
                CYAN +
                        "╚════════════════════════════════════════════╝"
                        + RESET);

        displayVehicles(central);
        displayVehicles(airport);

        System.out.println();

        System.out.println(
                "                  X. ↩ Back");

        while (true) {

            System.out.print(
                    "Enter choice: ");

            String choice =
                    scanner.nextLine();

            if (choice.equalsIgnoreCase("X")) {
                return;
            }

            System.out.println(
                    RED + "Please enter X."
                            + RESET);
        }
    }


    static void displayVehicles(
            Junction junction) {

        System.out.println();
        System.out.println(
                "📍 Junction: "
                        + junction.getName());

        for (Road road :
                junction.getRoads()) {

            System.out.println(
                    "\n🛣️ Road: "
                            + road.getName());

            if (road.getVehicles().isEmpty()) {

                System.out.println(
                        "   No vehicles.");

                continue;
            }

            for (Vehicle vehicle :
                    road.getVehicles()) {

                System.out.println(
                        "   • "
                                + vehicle.getVehicleId()
                                + " | "
                                + vehicle.getType()
                                + " | Speed: "
                                + vehicle.getSpeed());
            }
        }
    }


    // ==================== VEHICLE EXIT ====================

    static void vehicleExit() {

        System.out.print(
                "\nEnter Vehicle ID: ");

        String id =
                scanner.nextLine();

        List<Junction> junctions =
                List.of(central, airport);

        for (Junction junction :
                junctions) {

            for (Road road :
                    junction.getRoads()) {

                for (Vehicle vehicle :
                        new ArrayList<>(
                                road.getVehicles())) {

                    if (vehicle.getVehicleId()
                            .equalsIgnoreCase(id)) {

                        trafficService
                                .removeVehicleFromRoad(
                                        road,
                                        vehicle);

                        System.out.println(
                                GREEN
                                        + "✓ Vehicle exited successfully."
                                        + RESET);

                        return;
                    }
                }
            }
        }

        System.out.println(
                RED + "Vehicle not found."
                        + RESET);
    }


    // ==================== ROAD & TRAFFIC ====================

    static void roadTrafficStatus() {

        while (true) {

            System.out.println();

            System.out.println(
                    CYAN +
                            "╔════════════════════════════════════════════╗"
                            + RESET);

            System.out.println(
                    CYAN +
                            "║          🛣️ ROAD & TRAFFIC STATUS         ║"
                            + RESET);

            System.out.println(
                    CYAN +
                            "╠════════════════════════════════════════════╣"
                            + RESET);

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   1. 📍 Central Junction                  ║");

            System.out.println(
                    "║   2. 📍 Airport Junction                  ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   X. ↩ Back                                ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    CYAN +
                            "╚════════════════════════════════════════════╝"
                            + RESET);

            System.out.print(
                    "\nEnter choice: ");

            String choice =
                    scanner.nextLine();

            Junction junction;

            if (choice.equals("1")) {

                junction = central;

            } else if (choice.equals("2")) {

                junction = airport;

            } else if (choice.equalsIgnoreCase("X")) {

                return;

            } else {

                System.out.println(
                        RED + "Invalid choice."
                                + RESET);

                continue;
            }

            displayRoadStatus(junction);
        }
    }


    static void displayRoadStatus(
            Junction junction) {

        while (true) {

            System.out.println();

            System.out.println(
                    "╔════════════════════════════════════════════╗");

            System.out.println(
                    "║       🛣️ " + junction.getName());

            System.out.println(
                    "╠════════════════════════════════════════════╣");

            System.out.println(
                    "║                                            ║");

            for (Road road :
                    junction.getRoads()) {

                System.out.println(
                        "║  🛣️ " + road.getName());

                System.out.println(
                        "║     Status   : "
                                + (road.isOpen()
                                ? GREEN + "🟢 OPEN" + RESET
                                : RED + "🔴 CLOSED" + RESET));

                System.out.println(
                        "║     Vehicles : "
                                + road.getVehicleCount()
                                + "/"
                                + road.getCapacity());

                System.out.println(
                        "║     Traffic  : "
                                + getTrafficLevel(road));

                System.out.println(
                        "║                                            ║");
            }

            System.out.println(
                    "║   1. 🚧 Block Road                        ║");

            System.out.println(
                    "║   2. 🟢 Reopen Road                        ║");

            System.out.println(
                    "║   3. 🗺️ Alternative Route                  ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   X. ↩ Back                                ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "╚════════════════════════════════════════════╝");

            System.out.print(
                    "\nEnter choice: ");

            String choice =
                    scanner.nextLine();

            if (choice.equals("1")) {

                changeRoadStatus(
                        junction,
                        false);

            } else if (choice.equals("2")) {

                changeRoadStatus(
                        junction,
                        true);

            } else if (choice.equals("3")) {

                alternativeRoute(junction);

            } else if (choice.equalsIgnoreCase("X")) {

                return;

            } else {

                System.out.println(
                        RED + "Invalid choice."
                                + RESET);
            }
        }
    }


    static String getTrafficLevel(
            Road road) {

        if (!road.isOpen()) {

            return RED + "🔴 CLOSED" + RESET;
        }

        double usage =
                road.getOccupancyPercentage();

        if (usage >= 80) {

            return RED + "🔴 HIGH" + RESET;

        } else if (usage >= 50) {

            return YELLOW + "🟡 MODERATE" + RESET;

        } else {

            return GREEN + "🟢 LOW" + RESET;
        }
    }


    // ==================== BLOCK / REOPEN ====================

    static void changeRoadStatus(
            Junction junction,
            boolean reopen) {

        System.out.println();

        for (int i = 0;
             i < junction.getRoads().size();
             i++) {

            System.out.println(
                    (i + 1)
                            + ". 🛣️ "
                            + junction.getRoads()
                            .get(i)
                            .getName());
        }

        System.out.print(
                "Select road: ");

        try {

            int choice =
                    Integer.parseInt(
                            scanner.nextLine());

            if (choice < 1 ||
                    choice >
                            junction.getRoads().size()) {

                System.out.println(
                        RED + "Invalid road."
                                + RESET);

                return;
            }

            Road road =
                    junction.getRoads()
                            .get(choice - 1);

            if (reopen) {

                road.reopenRoad();

                System.out.println(
                        GREEN
                                + "🟢 Road reopened."
                                + RESET);

            } else {

                road.blockRoad(
                        "Road blockage");

                System.out.println(
                        RED
                                + "🔴 Road blocked."
                                + RESET);
            }

        } catch (Exception e) {

            System.out.println(
                    RED + "Invalid input."
                            + RESET);
        }
    }


    // ==================== ALTERNATIVE ROUTE ====================

    static void alternativeRoute(
            Junction junction) {

        System.out.println();

        for (int i = 0;
             i < junction.getRoads().size();
             i++) {

            System.out.println(
                    (i + 1)
                            + ". 🛣️ "
                            + junction.getRoads()
                            .get(i)
                            .getName());
        }

        System.out.print(
                "Select current road: ");

        try {

            int choice =
                    Integer.parseInt(
                            scanner.nextLine());

            if (choice < 1 ||
                    choice >
                            junction.getRoads().size()) {

                System.out.println(
                        RED + "Invalid road."
                                + RESET);

                return;
            }

            Road road =
                    junction.getRoads()
                            .get(choice - 1);

            controller.suggestAlternativeRoute(
                    junction,
                    road);

        } catch (Exception e) {

            System.out.println(
                    RED + "Invalid input."
                            + RESET);
        }
    }


    // ==================== INCIDENT & ALERTS ====================

    static void incidentAlerts() {

        while (true) {

            System.out.println();

            System.out.println(
                    CYAN +
                            "╔════════════════════════════════════════════╗"
                            + RESET);

            System.out.println(
                    CYAN +
                            "║             🚨 INCIDENT & ALERTS          ║"
                            + RESET);

            System.out.println(
                    CYAN +
                            "╠════════════════════════════════════════════╣"
                            + RESET);

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   1. 🚨 Check Central Traffic             ║");

            System.out.println(
                    "║   2. 🚨 Check Airport Traffic             ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   X. ↩ Back                                ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    CYAN +
                            "╚════════════════════════════════════════════╝"
                            + RESET);

            System.out.print(
                    "\nEnter choice: ");

            String choice =
                    scanner.nextLine();

            if (choice.equals("1")) {

                controller.checkEmergencyVehicle(
                        central);

                controller.detectCongestion(
                        central);

            } else if (choice.equals("2")) {

                controller.checkEmergencyVehicle(
                        airport);

                controller.detectCongestion(
                        airport);

            } else if (choice.equalsIgnoreCase("X")) {

                return;

            } else {

                System.out.println(
                        RED + "Invalid choice."
                                + RESET);
            }
        }
    }


    // ==================== ANALYTICS ====================

    static void analyticsReports() {

        while (true) {

            System.out.println();

            System.out.println(
                    CYAN +
                            "╔════════════════════════════════════════════╗"
                            + RESET);

            System.out.println(
                    CYAN +
                            "║           📊 ANALYTICS & REPORTS          ║"
                            + RESET);

            System.out.println(
                    CYAN +
                            "╠════════════════════════════════════════════╣"
                            + RESET);

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   1. 📍 Central Junction Report           ║");

            System.out.println(
                    "║   2. 📍 Airport Junction Report           ║");

            System.out.println(
                    "║   3. 📊 Complete Traffic Report           ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   X. ↩ Back                                ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    CYAN +
                            "╚════════════════════════════════════════════╝"
                            + RESET);

            System.out.print(
                    "\nEnter choice: ");

            String choice =
                    scanner.nextLine();

            if (choice.equals("1")) {

                statistics.generateReport(
                        central);

            } else if (choice.equals("2")) {

                statistics.generateReport(
                        airport);

            } else if (choice.equals("3")) {

                statistics.generateReport(
                        central);

                statistics.generateReport(
                        airport);

            } else if (choice.equalsIgnoreCase("X")) {

                return;

            } else {

                System.out.println(
                        RED + "Invalid choice."
                                + RESET);
            }
        }
    }


    // ==================== LIVE SIMULATION ====================

    static void startSimulation() {

        while (true) {

            System.out.println();

            System.out.println(
                    CYAN +
                            "╔════════════════════════════════════════════╗"
                            + RESET);

            System.out.println(
                    CYAN +
                            "║             ▶️ LIVE SIMULATION             ║"
                            + RESET);

            System.out.println(
                    CYAN +
                            "╠════════════════════════════════════════════╣"
                            + RESET);

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   1. ▶️ Start Simulation                   ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    "║   X. ↩ Back                                ║");

            System.out.println(
                    "║                                            ║");

            System.out.println(
                    CYAN +
                            "╚════════════════════════════════════════════╝"
                            + RESET);

            System.out.print(
                    "\nEnter choice: ");

            String choice =
                    scanner.nextLine();

            if (choice.equals("1")) {

                System.out.println();

                System.out.println(
                        "▶️ Starting simulation for both junctions...");

                Thread centralThread =
                        new Thread(
                                new TrafficSimulation(
                                        central),
                                "Central-Junction");

                Thread airportThread =
                        new Thread(
                                new TrafficSimulation(
                                        airport),
                                "Airport-Junction");

                centralThread.start();
                airportThread.start();

                try {

                    centralThread.join();
                    airportThread.join();

                } catch (InterruptedException e) {

                    Thread.currentThread()
                            .interrupt();

                    System.out.println(
                            "Simulation interrupted.");
                }

                System.out.println();

                System.out.println(
                        GREEN
                                + "✓ Both junction simulations completed."
                                + RESET);

            } else if (choice.equalsIgnoreCase("X")) {

                return;

            } else {

                System.out.println(
                        RED + "Invalid choice."
                                + RESET);
            }
        }
    }
}