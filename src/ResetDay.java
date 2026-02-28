import java.util.Scanner;

/*
 * ResetDay clears the list of journeys.
 * A confirmation check is used to prevent accidental reset.
 */
public class ResetDay {

    public static void run(Scanner scanner, CityRideState state) {
        System.out.println();
        System.out.println("————— Reset day —————");

        if (state.journeys.isEmpty()) {
            System.out.println("No journeys to reset.");
            return;
        }

        boolean confirm = InputHelper.readYesNo(scanner, "Are you sure you want to reset the day? (Y/N): ");
        if (!confirm) {
            System.out.println("Reset cancelled.");
            return;
        }

        state.journeys.clear();
        state.nextJourneyId = 1;
        state.recordingDate = null; // allow a new day after reset
        state.initialiseRunningTotals();

        System.out.println("Day reset successfully.");
    }
}