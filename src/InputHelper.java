import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

/*
 * InputHelper keeps common validation in one place.
 * This avoids repeating the same try/catch and checks inside each feature.
 */
public class InputHelper {

    private static final DateTimeFormatter INPUT_DATE_FMT = DateTimeFormatter
            .ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("Input cannot be blank. Try again.");
                continue;
            }

            try {
                int val = Integer.parseInt(line);
                if (val < min || val > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static boolean readYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().toUpperCase();
            if (line.equals("Y") || line.equals("YES")) return true;
            if (line.equals("N") || line.equals("NO")) return false;
            System.out.println("Please enter Y or N.");
        }
    }

    public static LocalDate readDateDDMMYYYY(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, INPUT_DATE_FMT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use DD-MM-YYYY (for example 05-02-2026).");
            }
        }
    }

    public static CityRideDataset.TimeBand readTimeBand(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim().toUpperCase();

            if (s.equals("P") || s.equals("PEAK")) return CityRideDataset.TimeBand.PEAK;
            if (s.equals("O") || s.equals("OFF-PEAK") || s.equals("OFF_PEAK") || s.equals("OFFPEAK")) {
                return CityRideDataset.TimeBand.OFF_PEAK;
            }

            System.out.println("Invalid choice. Enter PEAK or OFF-PEAK (or P/O).");
        }
    }

    public static CityRideDataset.PassengerType readPassengerType(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim().toUpperCase();

            if (s.equals("ADULT")) return CityRideDataset.PassengerType.ADULT;
            if (s.equals("STUDENT")) return CityRideDataset.PassengerType.STUDENT;
            if (s.equals("CHILD")) return CityRideDataset.PassengerType.CHILD;
            if (s.equals("SENIOR") || s.equals("SENIOR CITIZEN") || s.equals("SENIOR_CITIZEN") || s.equals("SENIORCITIZEN")) {
                return CityRideDataset.PassengerType.SENIOR_CITIZEN;
            }

            System.out.println("Invalid passenger type. Allowed: Adult, Student, Child, Senior Citizen.");
        }
    }
}