package model;

public class TrafficSignal {

    private String signalId;
    private String currentSignal;
    private int greenTime;

    public TrafficSignal(String signalId) {
        this.signalId = signalId;
        this.currentSignal = "RED";
        this.greenTime = 30;
    }

    public String getSignalId() {
        return signalId;
    }

    public String getCurrentSignal() {
        return currentSignal;
    }

    public int getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }

    public void changeSignal(String signal) {

        if (signal.equals("RED") ||
                signal.equals("GREEN") ||
                signal.equals("YELLOW")) {

            currentSignal = signal;

            System.out.println(
                    "Signal " + signalId +
                            " changed to " + signal
            );
        }
    }

    public void displaySignal() {

        System.out.println(
                "Signal " + signalId +
                        " | Status: " + currentSignal +
                        " | Green Time: " +
                        greenTime + " seconds"
        );
    }
}