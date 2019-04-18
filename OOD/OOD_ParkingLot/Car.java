package OOD_ParkingLot;

public class Car extends Vehicle {
    @Override
    public VehicleSize getSize(){
        return VehicleSize.Compact;
    }
}
