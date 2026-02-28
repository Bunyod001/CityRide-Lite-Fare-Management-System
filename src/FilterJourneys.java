import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * FilterJourneys lets the user view a subset of journeys.
 * It first checks if the list is empty to avoid confusion.
 */
public class FilterJourneys {

    public static void run(Scanner scanner, CityRideState state) {
        System.out.println();
        System.out.println("————— Filter journeys —————");

        if (state.journeys.isEmpty()) {
            System.out.println("No journeys to filter.");
            return;
        }

        System.out.println("1. By passenger type");
        System.out.println("2. By time band");
        System.out.println("3. By zone (from or to)");
        System.out.println("4. By date");

        int option = InputHelper.readIntInRange(scanner, "Choose filter option (1-4): ", 1, 4);

        List<Journey> matches = new ArrayList<>();

        if (option == 1) {
            CityRideDataset.PassengerType type = InputHelper.readPassengerType(scanner,
                    "Enter passenger type (Adult/Student/Child/Senior Citizen): ");

            for (Journey j : state.journeys) {
                if (j.getPassengerType() == type) matches.add(j);
            }

        } else if (option == 2) {
            CityRideDataset.TimeBand band = InputHelper.readTimeBand(scanner,
                    "Enter time band (Peak/Off-peak or P/O): ");

            for (Journey j : state.journeys) {
                if (j.getTimeBand() == band) matches.add(j);
            }

        } else if (option == 3) {
            int zone = InputHelper.readIntInRange(scanner,
                    "Enter zone (" + CityRideDataset.MIN_ZONE + "-" + CityRideDataset.MAX_ZONE + "): ",
                    CityRideDataset.MIN_ZONE, CityRideDataset.MAX_ZONE);

            for (Journey j : state.journeys) {
                if (j.getFromZone() == zone || j.getToZone() == zone) matches.add(j);
            }

        } else if (option == 4) {
            LocalDate date = InputHelper.readDateDDMMYYYY(scanner, "Enter date to match (DD-MM-YYYY): ");

            for (Journey j : state.journeys) {
                if (j.getDate().equals(date)) matches.add(j);
            }
        }

        if (matches.isEmpty()) {
            System.out.println("No matching journeys found.");
            return;
        }

        System.out.println();
        System.out.println("Matching journeys:");
        for (Journey j : matches) {
            System.out.println(j.toDisplayString());
        }
    }
}