import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

/*
 * Class responsible for collecting and validating user input when adding a journey
 * It uses loops to re-prompt the user instead of stopping the program when input is wrong.
 * Dates are parsed with a strict formatter so invalid dates (e.g., 31-02-2026) are rejected.
 */
public class AddJourney {

    public static void run(Scanner scanner, CityRideState state) {
        System.out.println();
        System.out.println("————— Add journey —————");

        // Single-day tracking
        LocalDate date;
        while (true) {
            date = InputHelper.readDateDDMMYYYY(scanner, "Enter date (DD-MM-YYYY): ");

            if (state.recordingDate == null) {
                state.recordingDate = date;
                break;
            }

            if (date.equals(state.recordingDate)) {
                break;
            }

            System.out.println(
                    "This program records one day only. Please enter: " +
                            state.recordingDate.format(
                                    java.time.format.DateTimeFormatter.ofPattern("dd-MM-uuuu")
                            )
            );
        }

        CityRideDataset.TimeBand band = InputHelper.readTimeBand(scanner, "Enter time band (Peak/Off-peak or P/O): ");

        int fromZone = InputHelper.readIntInRange(scanner,
                "Enter starting zone (" + CityRideDataset.MIN_ZONE + "-" + CityRideDataset.MAX_ZONE + "): ",
                CityRideDataset.MIN_ZONE, CityRideDataset.MAX_ZONE);

        int toZone = InputHelper.readIntInRange(scanner,
                "Enter destination zone (" + CityRideDataset.MIN_ZONE + "-" + CityRideDataset.MAX_ZONE + "): ",
                CityRideDataset.MIN_ZONE, CityRideDataset.MAX_ZONE);

        CityRideDataset.PassengerType type = InputHelper.readPassengerType(scanner,
                "Enter passenger type (Adult/Student/Child/Senior Citizen): ");

        int zonesCrossed = FareCalculator.calcZonesCrossed(fromZone, toZone);

        BigDecimal currentTotal = state.runningTotals.get(type);
        FareCalculator.FareResult fares = FareCalculator.calculate(fromZone, toZone, band, type, currentTotal);

        state.runningTotals.put(type, Money.toMoney(currentTotal.add(fares.getChargedFare())));

        Journey j = new Journey(
                state.nextJourneyId,
                date,
                band,
                fromZone,
                toZone,
                type,
                zonesCrossed,
                fares.getBaseFare(),
                fares.getDiscountRate(),
                fares.getDiscountedFare(),
                fares.getChargedFare()
        );

        state.journeys.add(j);
        state.nextJourneyId++;

        System.out.println("Journey added successfully.");
        System.out.println("ID: " + j.getId() + " | Charged: GBP " + Money.format(j.getChargedFare()));
    }
}