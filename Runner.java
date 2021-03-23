class Runner {

    public static void main(String[] args) throws Exception {
        // Initialize WarehouseServer instance.
        WarehouseServer warehouseServer = new WarehouseServer();
        warehouseServer.initializeServer("warehouse_pings.csv");
        System.out.println("~~~WarehouseServer is initialized.");
        System.out.println();

        System.out.println("Average Speeds: " + warehouseServer.getAverageSpeeds());
        System.out.println();

        printArrayContents(
            "The 3 most traveled vehicles since 1553273158 are: ",
            warehouseServer.getMostTraveledSince(3, 1553273158));

        printArrayContents(
            "Vehicles possibly damaged: ",
            warehouseServer.checkForDamage());

        // Feel free to put any println statements below for testing and debugging
    }

    private static void printArrayContents(String description, String[] array) {
        System.out.println(description);
        for(String element : array) {
            System.out.println("\t" + element);
        }
        System.out.println();
    }
}
