import java.math.BigDecimal;

/*
 * ViewTotalsByPassengerType prints totals for passenger types that has min of 1 journey.
 * It also shows whether the daily cap has been reached.
 */
public class ViewTotalsByPassengerType {

    public static void run(CityRideState state) {
        System.out.println();
        System.out.println("————— Totals by passenger type —————");

        if (state.journeys.isEmpty()) {
            System.out.println("No journeys recorded to summarise.");
            return;
        }

        for (CityRideDataset.PassengerType type : CityRideDataset.PassengerType.values()) {
            int count = 0;
            BigDecimal sumBase = BigDecimal.ZERO;
            BigDecimal sumDiscounted = BigDecimal.ZERO;
            BigDecimal sumCharged = BigDecimal.ZERO;

            for (Journey j : state.journeys) {
                if (j.getPassengerType() == type) {
                    count++;
                    sumBase = sumBase.add(j.getBaseFare());
                    sumDiscounted = sumDiscounted.add(j.getDiscountedFare());
                    sumCharged = sumCharged.add(j.getChargedFare());
                }
            }

            if (count == 0) continue;

            sumBase = Money.toMoney(sumBase);
            sumDiscounted = Money.toMoney(sumDiscounted);
            sumCharged = Money.toMoney(sumCharged);

            BigDecimal cap = CityRideDataset.DAILY_CAP.get(type);
            boolean capReached = sumCharged.compareTo(cap) >= 0;

            System.out.println();
            System.out.println("Passenger type: " + type);
            System.out.println("Journeys: " + count);
            System.out.println("Sum base fare: GBP " + Money.format(sumBase));
            System.out.println("Sum discounted fare: GBP " + Money.format(sumDiscounted));
            System.out.println("Sum charged fare (after cap): GBP " + Money.format(sumCharged));
            System.out.println("Cap reached?: " + (capReached ? "Yes" : "No"));
        }
    }
}