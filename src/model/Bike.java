package model;

public class Bike extends Vehicle {

    public Bike(String vehicleId, int speed) {
        super(vehicleId, "Bike", speed, false);
    }

    @Override
    public void move() {
        System.out.println("Bike " + getVehicleId() + " is moving.");
    }
}