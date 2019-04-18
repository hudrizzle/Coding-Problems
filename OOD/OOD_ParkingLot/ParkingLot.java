package OOD_ParkingLot;

//first design classes and their relations, then public apis
public class ParkingLot {
    //the parking lot has many levels
    private final Level[] levels;

    public ParkingLot(int numLevels, int spotsPerLevel){
        this.levels = new Level[numLevels];
        for (int i = 0; i < numLevels; i++) {
            levels[i] = new Level(spotsPerLevel);
        }
    }

    //given vehicle information, check if there's suitable spot for it to park
    public boolean hasSpot(Vehicle v){
        for (Level level : levels) {
            if (level.hasSpot(v)) return true;
        }
        return false;
    }

    //given a vehicle v, return if it can be succesfully parked
    public boolean park(Vehicle v){
        for (Level level : levels) {
            if (level.park(v)) {
                return true;
            }
        }
        return false;
    }

    //given a Vehicle v, return if it can be found and leave
    public boolean leave(Vehicle v){
        for (Level level : levels) {
            if (level.leave(v)) return true;
        }
        return false;
    }
}
