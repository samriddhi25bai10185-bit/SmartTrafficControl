package model;

public class EmergencyVehicle extends Vehicle {

    private String emergencyType;

    public EmergencyVehicle(String vehicleId, int speed, String emergencyType) {
        super(vehicleId, "Emergency Vehicle", speed, true);
        this.emergencyType = emergencyType;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    @Override
    public void move() {
        System.out.println(
                emergencyType + " " + getVehicleId() +
                        " is moving with emergency priority!"
        );
    }
}