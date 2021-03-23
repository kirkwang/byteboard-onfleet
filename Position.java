/**
 * A Position represents an x, y coordinate in a given warehouse. Position
 * can be used to determine how far apart or near together two vehicles are.
 */
final class Position {
    private double x, y;

    Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;

        Position pos = (Position) o;
        return (x == pos.getX() && y == pos.getY());
    }

    /**
     * Determines the distance between two Positions
     * Distance is calculated as the Euclidean distance in two dimensions
     * https://en.wikipedia.org/wiki/Euclidean_distance
     */
    static double getDistance(Position position1, Position position2) {
        double xDiff = Math.abs(position1.getX() - position2.getX());
        double yDiff = Math.abs(position1.getY() - position2.getY());
        return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
    }
}
