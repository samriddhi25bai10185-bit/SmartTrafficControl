package model;

public class Bus extends Vehicle {

    public Bus(String vehicleId, int speed) {
        super(vehicleId, "Bus", speed, false);
    }

    @Override
    public void move() {
        System.out.println("Bus " + getVehicleId() + " is moving.");
    }
}