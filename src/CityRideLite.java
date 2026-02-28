import java.util.Scanner;

public class CityRideLite {

    public static void main(String[] args) {
        CityRideState state = new CityRideState();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            displayMainMenu();
            choice = InputHelper.readIntInRange(scanner, "Enter your choice (1-8): ", 1, 8);

            switch (choice) {
                case 1:
                    AddJourney.run(scanner, state);
                    break;
                case 2:
                    ListJourneys.run(state);
                    break;
                case 3:
                    FilterJourneys.run(scanner, state);
                    break;
                case 4:
                    ViewDailySummary.run(state);
                    break;
                case 5:
                    ViewTotalsByPassengerType.run(state);
                    break;
                case 6:
                    RemoveJourney.run(scanner, state);
                    break;
                case 7:
                    ResetDay.run(scanner, state);
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1-8");
            }

        } while (choice != 8);

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println();
        System.out.println("\n———————— Welcome to the City Ride Lite ————————");
        System.out.println("1. Add journey");
        System.out.println("2. List journeys");
        System.out.println("3. Filter journeys");
        System.out.println("4. View daily summary");
        System.out.println("5. View totals by passenger type");
        System.out.println("6. Undo/Remove journey");
        System.out.println("7. Reset day");
        System.out.println("8. Exit");
        System.out.println("———————————————————————————————————————————————");
    }
}