package utd.theia.enums;

/**
 * Created by Sagar on 11/4/2015.
 */
public enum Direction {
    NORTH("north"), EAST("east"), SOUTH("south"), WEST("west");

    private String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return direction;
    }
}
