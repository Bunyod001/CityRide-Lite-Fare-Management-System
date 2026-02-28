import java.util.Scanner;

/*
 * RemoveJourney deletes a journey by ID.
 * The program asks for confirmation, then recalculates totals because
 * cap logic depends on the order of journeys during the day.
 */
public class RemoveJourney {

    public static void run(Scanner scanner, CityRideState state) {
        System.out.println();
        System.out.println("————— Remove journey —————");

        if (state.journeys.isEmpty()) {
            System.out.println("No journeys recorded.");
            return;
        }

        int id = InputHelper.readIntInRange(scanner, "Enter journey ID to remove: ", 1, Integer.MAX_VALUE);

        Journey target = null;
        for (Journey j : state.journeys) {
            if (j.getId() == id) {
                target = j;
                break;
            }
        }

        if (target == null) {
            System.out.println("Invalid ID. No journey found with ID " + id + ".");
            return;
        }

        System.out.println("Journey found:");
        System.out.println(target.toDisplayString());

        boolean confirm = InputHelper.readYesNo(scanner, "Confirm remove? (Y/N): ");
        if (!confirm) {
            System.out.println("Removal cancelled.");
            return;
        }

        state.journeys.remove(target);
        state.renumberJourneyIds();
        System.out.println("Journey removed.");

        state.recalculateAllCharges();
        System.out.println("Totals recalculated.");
    }
}