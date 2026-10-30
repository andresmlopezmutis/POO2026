package model;

public class StandardFloorSurchargePolicy implements FloorSurchargePolicy {

    @Override
    public double getSurchargePercent(int floor) {
        if (floor < 1) {
            throw new IllegalArgumentException("Floor must be greater than zero.");
        }
        if (floor <= 3) {
            return 0;
        }
        if (floor <= 6) {
            return 10;
        }
        return 20;
    }
}
