package OOD_ParkingLot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//constructor, methods: hasSpot(), park(), leave()
class Level {
    private final List<ParkingSpot> spots;

    //new number of spots arraylist first, then put it into unmodifiablelist s.t the size can't be changed
    Level(int numOfSpots){
        List<ParkingSpot> list = new ArrayList<ParkingSpot>(numOfSpots);
        //assume half of spots are large and half are compact
        int i = 0;
        for (; i < numOfSpots / 2; i++) {
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for (; i < numOfSpots; i++) {
            list.add(new ParkingSpot(VehicleSize.Large));
        }
        //wrap list with unmodifableList so that others can't change the number of spots of the level
        spots = Collections.unmodifiableList(list);
    }

    //given a coming vehicle, return if there's a spot allowing parking
    boolean hasSpot(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) return true;
        }
        return false;
    }

    //assign a spot to given v, return true if successful
    boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }

    //T:let it leave the spot if it parked on this level; F: v didnt park this level
    boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            //check if v parked at spot s
            if (s.getVehicle() == v){
                s.leave(v);
                return true;
            }
        }
        return false;
    }

}
