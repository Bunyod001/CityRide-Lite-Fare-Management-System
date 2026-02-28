import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * ViewDailySummary prints totals for the day.
 * If there are no journeys, it prints zeros.
 */
public class ViewDailySummary {

    public static void run(CityRideState state) {
        System.out.println();
        System.out.println("————— Daily summary —————");

        if (state.journeys.isEmpty()) {
            System.out.println("Total journeys: 0");
            System.out.println("Total cost charged: GBP 0.00");
            System.out.println("Average cost per journey: GBP 0.00");
            System.out.println("Most expensive journey: None");
            return;
        }

        int count = state.journeys.size();
        BigDecimal totalCharged = BigDecimal.ZERO;

        Journey mostExpensive = state.journeys.get(0);
        for (Journey j : state.journeys) {
            totalCharged = totalCharged.add(j.getChargedFare());
            if (j.getChargedFare().compareTo(mostExpensive.getChargedFare()) > 0) {
                mostExpensive = j;
            }
        }

        totalCharged = Money.toMoney(totalCharged);
        BigDecimal avg = totalCharged.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);

        System.out.println("Total journeys: " + count);
        System.out.println("Total cost charged: GBP " + Money.format(totalCharged));
        System.out.println("Average cost per journey: GBP " + Money.format(avg));
        System.out.println("Most expensive journey: ID " + mostExpensive.getId()
                + " (GBP " + Money.format(mostExpensive.getChargedFare()) + ")");
    }
}