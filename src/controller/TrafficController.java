package controller;

import model.Junction;
import model.Road;
import model.Vehicle;

public class TrafficController {

    // ==================== SIGNAL TIMING ====================

    public void calculateSignalTime(Junction junction) {

        int totalVehicles = junction.getTotalVehicles();

        int greenTime;

        // Emergency vehicle gets highest priority
        if (hasEmergencyVehicle(junction)) {

            greenTime = 60;

            junction.getSignal()
                    .changeSignal("GREEN");

            System.out.println(
                    "🚨 Emergency Priority: Signal GREEN");

        } else {

            // Traffic density based signal timing
            if (totalVehicles <= 3) {

                greenTime = 20;

            } else if (totalVehicles <= 7) {

                greenTime = 40;

            } else {

                greenTime = 60;
            }

            // Decide signal based on traffic level
            Road highestTrafficRoad =
                    getHighestTrafficRoad(junction);

            if (highestTrafficRoad != null) {

                double usage =
                        highestTrafficRoad
                                .getOccupancyPercentage();

                if (usage >= 80) {

                    junction.getSignal()
                            .changeSignal("GREEN");

                    System.out.println(
                            "🚦 High Traffic Detected: Signal GREEN");

                } else if (usage >= 50) {

                    junction.getSignal()
                            .changeSignal("GREEN");

                    System.out.println(
                            "🚦 Moderate Traffic: Signal GREEN");

                } else {

                    junction.getSignal()
                            .changeSignal("RED");

                    System.out.println(
                            "🔴 Low Traffic: Signal RED");
                }
            }
        }

        junction.getSignal()
                .setGreenTime(greenTime);

        System.out.println(
                "Smart Signal Time: "
                        + greenTime
                        + " seconds");
    }


    // ==================== EMERGENCY CHECK ====================

    public boolean hasEmergencyVehicle(
            Junction junction) {

        for (Road road :
                junction.getRoads()) {

            for (Vehicle vehicle :
                    road.getVehicles()) {

                if (vehicle.isEmergency()) {

                    return true;
                }
            }
        }

        return false;
    }


    public void checkEmergencyVehicle(
            Junction junction) {

        for (Road road :
                junction.getRoads()) {

            for (Vehicle vehicle :
                    road.getVehicles()) {

                if (vehicle.isEmergency()) {

                    System.out.println();
                    System.out.println(
                            "🚨 EMERGENCY VEHICLE DETECTED");

                    System.out.println(
                            "Vehicle : "
                                    + vehicle.getVehicleId());

                    System.out.println(
                            "Type    : "
                                    + vehicle.getType());

                    System.out.println(
                            "Road    : "
                                    + road.getName());

                    junction.getSignal()
                            .changeSignal("GREEN");

                    System.out.println(
                            "✓ Signal changed to GREEN");

                    System.out.println(
                            "✓ Emergency priority granted");

                    return;
                }
            }
        }

        System.out.println(
                "No emergency vehicle detected.");
    }


    // ==================== HIGHEST TRAFFIC ROAD ====================

    public Road getHighestTrafficRoad(
            Junction junction) {

        Road highestRoad = null;

        double highestUsage = -1;

        for (Road road :
                junction.getRoads()) {

            if (!road.isOpen()) {
                continue;
            }

            double usage =
                    road.getOccupancyPercentage();

            if (usage > highestUsage) {

                highestUsage = usage;
                highestRoad = road;
            }
        }

        return highestRoad;
    }


    // ==================== TRAFFIC DETECTION ====================

    public void detectCongestion(
            Junction junction) {

        System.out.println();
        System.out.println(
                "========== TRAFFIC STATUS ==========");

        for (Road road :
                junction.getRoads()) {

            int vehicles =
                    road.getVehicleCount();

            int capacity =
                    road.getCapacity();

            double percentage =
                    road.getOccupancyPercentage();

            System.out.println();
            System.out.println(
                    "Road: "
                            + road.getName());

            System.out.println(
                    "Vehicles: "
                            + vehicles
                            + "/"
                            + capacity);

            if (!road.isOpen()) {

                System.out.println(
                        "Status: ROAD CLOSED");

            } else if (percentage >= 80) {

                System.out.println(
                        "Status: HIGH TRAFFIC");

            } else if (percentage >= 50) {

                System.out.println(
                        "Status: MODERATE TRAFFIC");

            } else {

                System.out.println(
                        "Status: LOW TRAFFIC");
            }
        }

        System.out.println(
                "====================================");
    }


    // ==================== ALTERNATIVE ROUTE ====================

    public void suggestAlternativeRoute(
            Junction junction,
            Road currentRoad) {

        double currentUsage =
                currentRoad.getOccupancyPercentage();

        // No need for alternative route
        if (currentRoad.isOpen()
                && currentUsage < 80) {

            return;
        }

        Road alternativeRoad = null;

        double lowestUsage = 101;

        for (Road road :
                junction.getRoads()) {

            if (road == currentRoad) {
                continue;
            }

            if (!road.isOpen()) {
                continue;
            }

            if (road.isFull()) {
                continue;
            }

            double usage =
                    road.getOccupancyPercentage();

            if (usage < lowestUsage) {

                lowestUsage = usage;
                alternativeRoad = road;
            }
        }

        if (alternativeRoad == null) {

            System.out.println();
            System.out.println(
                    "⚠ No alternative route available.");

            return;
        }

        System.out.println();
        System.out.println(
                "💡 ALTERNATIVE ROUTE SUGGESTION");

        System.out.println(
                "Current Road : "
                        + currentRoad.getName());

        System.out.println(
                "Suggested Road : "
                        + alternativeRoad.getName());

        System.out.println(
                "Available Capacity : "
                        + alternativeRoad
                        .getAvailableCapacity()
                        + " vehicles");

        System.out.println(
                "Current Usage : "
                        + String.format(
                        "%.0f",
                        currentUsage)
                        + "%");

        System.out.println(
                "Suggested Road Usage : "
                        + String.format(
                        "%.0f",
                        lowestUsage)
                        + "%");

        System.out.println(
                "✓ Recommendation: Use the suggested road.");
    }


    // ==================== SIGNAL CYCLE ====================

    public void runSignalCycle(
            Junction junction) {

        System.out.println();
        System.out.println(
                "Starting signal cycle...");

        junction.getSignal()
                .changeSignal("GREEN");

        try {

            Thread.sleep(1000);

            junction.getSignal()
                    .changeSignal("YELLOW");

            Thread.sleep(1000);

            junction.getSignal()
                    .changeSignal("RED");

        } catch (InterruptedException e) {

            Thread.currentThread()
                    .interrupt();

            System.out.println(
                    "Signal cycle interrupted.");
        }

        System.out.println(
                "Signal cycle completed.");
    }
}