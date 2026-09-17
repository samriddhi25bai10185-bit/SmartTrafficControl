package model;

import java.util.ArrayList;
import java.util.List;

public class Road {

    private String roadId;
    private String name;
    private int capacity;
    private List<Vehicle> vehicles;
    private boolean open;
    private String blockageReason;

    public Road(
            String roadId,
            String name,
            int capacity) {

        this.roadId = roadId;
        this.name = name;
        this.capacity = capacity;

        this.vehicles =
                new ArrayList<>();

        this.open = true;
        this.blockageReason = "";
    }

    public String getRoadId() {
        return roadId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {

        this.open = open;

        if (open) {
            blockageReason = "";
        }
    }

    public String getBlockageReason() {
        return blockageReason;
    }

    public void blockRoad(String reason) {

        open = false;
        blockageReason = reason;
    }

    public void reopenRoad() {

        open = true;
        blockageReason = "";
    }

    public void addVehicle(
            Vehicle vehicle) {

        vehicles.add(vehicle);
    }

    public void removeVehicle(
            Vehicle vehicle) {

        vehicles.remove(vehicle);
    }

    public int getVehicleCount() {

        return vehicles.size();
    }

    public int getAvailableCapacity() {

        return capacity -
                vehicles.size();
    }

    public double getOccupancyPercentage() {

        return ((double) vehicles.size()
                / capacity) * 100;
    }

    public boolean isFull() {

        return vehicles.size()
                >= capacity;
    }
}