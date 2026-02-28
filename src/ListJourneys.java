public class ListJourneys {

    /*
     * ListJourneys prints all recorded journeys in a clear, readable format.
     * It first checks for an empty list to avoid confusing output and to guide the user.
     */
    public static void run(CityRideState state) {
        System.out.println();
        System.out.println("----- List journeys -----");

        if (state.journeys.isEmpty()) {
            System.out.println("No journeys recorded.");
            return;
        }

        for (Journey j : state.journeys) {
            System.out.println(j.toDisplayString());
        }
    }
}