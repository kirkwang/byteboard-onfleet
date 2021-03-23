import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

final class WarehouseServer {

    // vehicles is a list of vehicle instances
    private List<Vehicle> vehicles;

    WarehouseServer() {
        vehicles = new ArrayList<Vehicle>();
    }

    /**
     * Returns a Map from vehicle name to that vehicle's average speed for all
     * vehicles.
     */
    Map<String, Double> getAverageSpeeds() {
        Map<String, Double> avgSpeeds = new HashMap<>();
        for (Vehicle vehicle : this.vehicles) {
            avgSpeeds.put(vehicle.getName(), vehicle.getAverageSpeed());
        }
        return avgSpeeds;
    }

    /**
     * Returns a sorted array of size max_results of vehicle names corresponding
     * to the vehicles that have traveled the most distance since the given
     * timestamp.
     */
    String[] getMostTraveledSince(int maxResult, long timestamp) {
        // List<Vehicle> vehicles;
        // for each vehic find the

        Map< Double, String> unsortMap = new HashMap<>();
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            List<Ping> pings = v.getPings();
            for (int j = 0; j < pings.size(); j++) {
                long vTime = pings.get(j).getTimestamp();
                if( vTime < timestamp){ // The result stores a list of vehicles before the timestamp

                    unsortMap.put(v.getTotalDistance(),v.getName());
                }
            }
        }
        // Find the most traveled.
        Map< Double, String> result = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        String[] answer = new String[maxResult];

        int index = maxResult-1;
        for(Map.Entry<Double,String> me: result.entrySet()){
            if(index >= 0 ){
                answer[index--] = me.getValue();
            }else {
                break;
            }
        }

        return answer;
    }

    /**
     * Returns an array of names identifying vehicles that might have been
     * damaged through any number of risky behaviors, including collision with
     * another vehicle and excessive acceleration.
     */
    String[] checkForDamage() {
        //TODO: Implement

        return new String[0];
    }

    void initializeServer(String fileName) {
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] parsedLine = line.split(",");
                processPing(
                    parsedLine[0],
                    Double.parseDouble(parsedLine[1]),
                    Double.parseDouble(parsedLine[2]),
                    Integer.parseInt(parsedLine[3]));
            }

            bufferedReader.close();
        } catch (IOException ioException) {
            System.out.println("Exception thrown populating data: " + ioException);
        }
    }

    private void processPing(String vehicleName, double x, double y, long timestamp) {
        Ping ping = new Ping(x, y, timestamp);
        if (vehicles.isEmpty() || !vehicles.get(vehicles.size() - 1).getName().equals(vehicleName)) {
            vehicles.add(new Vehicle(vehicleName));
        }
        vehicles.get(vehicles.size() - 1).getPings().add(ping);
    }
}
