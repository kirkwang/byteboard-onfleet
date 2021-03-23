import java.util.List;
import java.util.ArrayList;

/**
 * A named vehicle with a sequence of pings.
 */
final class Vehicle {
    private final String name;
    private final List<Ping> pings;

    Vehicle(String name) {
        this.name = name;
        this.pings = new ArrayList<Ping>();
    }

    /**
     * The name of the vehicle.
     */
    String getName() {
        return this.name;
    }

    /**
     * The pings for the vehicle, in chronological order (earliest first).
     */
    List<Ping> getPings() {
        return this.pings;
    }

    public String toString() {
        return this.name + ": " + this.pings;
    }

    /**
     * Determines the total distance covered by the pings.
     */
    static double getTotalDistance(List<Ping> pings) {

        //distance = sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))
        double distance = 0.0;
        double total_distance = 0;
        for (int i =1 ; i< pings.size(); i ++){
            Ping pre = pings.get(i-1);
            Ping cur = pings.get(i);
            double yTemp = cur.getPosition().getY() - pre.getPosition().getY();
                    yTemp *= yTemp;


            double xTemp = cur.getPosition().getX() - pre.getPosition().getX();
                    xTemp *= xTemp;
             distance = Math.sqrt(yTemp + xTemp);
            total_distance +=distance;
        }

        return total_distance;
    }

    /**
     * Determines the total distance traveled by the vehicle.
     */
    double getTotalDistance() {

        return getTotalDistance(this.pings);
    }

    /**
     * Determines the average speed of the vehicle.
     */
    double getAverageSpeed() {
        //TODO: Implement
        double result = 0.0;
        double totalTime = 0.0;
        for (int i =1 ; i< pings.size(); i ++){
            long pre = pings.get(i-1).getTimestamp();
            long cur = pings.get(i).getTimestamp();

            totalTime = cur - pre + totalTime;
        }
        result = getTotalDistance()/totalTime;
        return result;
    }
}
