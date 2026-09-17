package simulation;

import controller.TrafficController;
import model.Junction;

public class TrafficSimulation implements Runnable {

    private Junction junction;
    private TrafficController controller;

    public TrafficSimulation(Junction junction) {
        this.junction = junction;
        this.controller = new TrafficController();
    }

    @Override
    public void run() {

        String threadName =
                Thread.currentThread().getName();

        System.out.println();
        System.out.println(
                "========== TRAFFIC SIMULATION ==========");

        System.out.println(
                "Thread   : " + threadName);

        System.out.println(
                "Junction : " + junction.getName());

        System.out.println();

        // Check emergency vehicle
        controller.checkEmergencyVehicle(junction);

        // Calculate signal timing
        controller.calculateSignalTime(junction);

        // Detect traffic congestion
        controller.detectCongestion(junction);

        // Run signal cycle
        controller.runSignalCycle(junction);

        System.out.println();

        System.out.println(
                "Simulation completed for "
                        + junction.getName());

        System.out.println(
                "========================================");
    }
}