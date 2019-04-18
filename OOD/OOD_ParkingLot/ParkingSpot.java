package OOD_ParkingLot;

public class ParkingSpot {
    //records size of this spot and if there's car parking currently
    private final VehicleSize size;
    private Vehicle currentVehicle;

    ParkingSpot(VehicleSize size) {
        this.size = size;
    }
    //spot must be empty and size >= vehicle size s.t. v can be fitted to the spot
    boolean fit(Vehicle v) {
        return currentVehicle == null && size.getSize() >= v.getSize().getSize();
    }
    //record a vehicle is parked in by updating currentVehicle field
    void park(Vehicle v) {
        currentVehicle = v;
    }
    void leave(Vehicle v) {
        currentVehicle = null;
    }
    Vehicle getVehicle(){
        return currentVehicle;
    }
}
