import javafx.geometry.Point2D;

/**
 *
 * Direction - Helper class to handle all player movement
 *
 * @author Emmanuel Edamwen
 * @version 1.0
 * @since 11/29/18
 *
 *
 */

public enum Direction{
    UP(new Point2D(0, -1)),
    DOWN(new Point2D(0, 1)),
    LEFT(new Point2D(-1, 0)),
    RIGHT(new Point2D(1, 0));

    final Point2D vector;

    public Direction next() {
        if(ordinal() ==3)
            return UP;

        return Direction.values()[ordinal() + 1];
    }

    Direction(Point2D vector)
    {
        this.vector = vector;
    }

}
