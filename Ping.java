import java.util.List;

/**
 * A Ping represents a vehicle's position at a given timestamp
 */
final class Ping {
    private Position position;
    private long timestamp;

    Ping(double x, double y, long timestamp) {
        this.position = new Position(x, y);
        // Timestamp of the ping, in seconds since the epoch.
        this.timestamp = timestamp;
    }

    Position getPosition() {
        return this.position;
    }

    long getTimestamp() {
        // timestamp in seconds since a fixed (but arbitrary) epoch.
        return this.timestamp;
    }

    public String toString() {
        return position + " @ " + timestamp;
    }

    /**
     * The difference between the timestamps of the pings, in seconds. The
     result is positive if ping1 is earlier than ping2.
     */
    static long secondsBetween(Ping ping1, Ping ping2) {
        return ping2.getTimestamp() - ping1.getTimestamp();
    }
}
