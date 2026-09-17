package service;

import model.Junction;
import model.Road;
import model.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TrafficStatistics {

    public void generateReport(Junction junction) {

        StringBuilder report = new StringBuilder();

        report.append("\n");
        report.append("====================================\n");
        report.append("       TRAFFIC STATISTICS REPORT\n");
        report.append("====================================\n");

        report.append("Junction: ")
                .append(junction.getName())
                .append("\n");

        int totalVehicles = 0;
        int emergencyVehicles = 0;

        Map<String, Integer> vehicleCounts =
                new HashMap<>();

        for (Road road : junction.getRoads()) {

            int count = road.getVehicleCount();

            totalVehicles += count;

            report.append("\n");
            report.append("Road: ")
                    .append(road.getName())
                    .append("\n");

            report.append("Vehicles: ")
                    .append(count)
                    .append("/")
                    .append(road.getCapacity())
                    .append("\n");

            for (Vehicle vehicle :
                    road.getVehicles()) {

                String type = vehicle.getType();

                vehicleCounts.put(
                        type,
                        vehicleCounts.getOrDefault(
                                type, 0) + 1
                );

                if (vehicle.isEmergency()) {
                    emergencyVehicles++;
                }
            }
        }

        report.append("\n");
        report.append("------------------------------------\n");
        report.append("Vehicle Type Statistics\n");
        report.append("------------------------------------\n");

        for (Map.Entry<String, Integer> entry :
                vehicleCounts.entrySet()) {

            report.append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue())
                    .append("\n");
        }

        report.append("\n");
        report.append("Total Vehicles     : ")
                .append(totalVehicles)
                .append("\n");

        report.append("Emergency Vehicles : ")
                .append(emergencyVehicles)
                .append("\n");

        report.append("====================================\n");

        System.out.println(report);

        saveReport(report.toString());
    }

    private void saveReport(String report) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "traffic_report.txt",
                            true);

            writer.write(report);
            writer.close();

            System.out.println(
                    "✓ Report saved to traffic_report.txt");

        } catch (IOException e) {

            System.out.println(
                    "✗ Error saving report.");
        }
    }
}