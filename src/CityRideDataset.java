import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public final class CityRideDataset {

    private CityRideDataset() {}

    public static final int MIN_ZONE = 1;
    public static final int MAX_ZONE = 5;

    public enum TimeBand { PEAK, OFF_PEAK }

    public enum PassengerType { ADULT, STUDENT, CHILD, SENIOR_CITIZEN }

    public static final Map<PassengerType, BigDecimal> DISCOUNT_RATE = Map.of(
            PassengerType.ADULT, new BigDecimal("0.00"),
            PassengerType.STUDENT, new BigDecimal("0.25"),
            PassengerType.CHILD, new BigDecimal("0.50"),
            PassengerType.SENIOR_CITIZEN, new BigDecimal("0.30")
    );

    public static final Map<PassengerType, BigDecimal> DAILY_CAP = Map.of(
            PassengerType.ADULT, new BigDecimal("8.00"),
            PassengerType.STUDENT, new BigDecimal("6.00"),
            PassengerType.CHILD, new BigDecimal("4.00"),
            PassengerType.SENIOR_CITIZEN, new BigDecimal("7.00")
    );

    public static final Map<String, BigDecimal> BASE_FARE = buildBaseFare();

    public static BigDecimal getBaseFare(int fromZone, int toZone, TimeBand timeBand) {
        return BASE_FARE.get(key(fromZone, toZone, timeBand));
    }

    public static String key(int fromZone, int toZone, TimeBand timeBand) {
        return fromZone + "-" + toZone + "-" + timeBand.name();
    }

    private static BigDecimal money(String amount) {
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
    }

    private static Map<String, BigDecimal> buildBaseFare() {
        Map<String, BigDecimal> m = new HashMap<>();

        // Peak fares
        put(m,1,1, TimeBand.PEAK,"2.50"); put(m,1,2, TimeBand.PEAK,"3.20");
        put(m,1,3, TimeBand.PEAK,"3.80"); put(m,1,4, TimeBand.PEAK,"4.40");
        put(m,1,5, TimeBand.PEAK,"5.00");

        put(m,2,1, TimeBand.PEAK,"3.20"); put(m,2,2, TimeBand.PEAK,"2.30");
        put(m,2,3, TimeBand.PEAK,"3.10"); put(m,2,4, TimeBand.PEAK,"3.80");
        put(m,2,5, TimeBand.PEAK,"4.50");

        put(m,3,1, TimeBand.PEAK,"3.80"); put(m,3,2, TimeBand.PEAK,"3.10");
        put(m,3,3, TimeBand.PEAK,"2.10"); put(m,3,4, TimeBand.PEAK,"3.00");
        put(m,3,5, TimeBand.PEAK,"3.70");

        put(m,4,1, TimeBand.PEAK,"4.40"); put(m,4,2, TimeBand.PEAK,"3.80");
        put(m,4,3, TimeBand.PEAK,"3.00"); put(m,4,4, TimeBand.PEAK,"2.00");
        put(m,4,5, TimeBand.PEAK,"2.90");

        put(m,5,1, TimeBand.PEAK,"5.00"); put(m,5,2, TimeBand.PEAK,"4.50");
        put(m,5,3, TimeBand.PEAK,"3.70"); put(m,5,4, TimeBand.PEAK,"2.90");
        put(m,5,5, TimeBand.PEAK,"1.90");

        // Off-peak fares
        put(m,1,1, TimeBand.OFF_PEAK,"2.00"); put(m,1,2, TimeBand.OFF_PEAK,"2.70");
        put(m,1,3, TimeBand.OFF_PEAK,"3.20"); put(m,1,4, TimeBand.OFF_PEAK,"3.70");
        put(m,1,5, TimeBand.OFF_PEAK,"4.20");

        put(m,2,1, TimeBand.OFF_PEAK,"2.70"); put(m,2,2, TimeBand.OFF_PEAK,"1.90");
        put(m,2,3, TimeBand.OFF_PEAK,"2.60"); put(m,2,4, TimeBand.OFF_PEAK,"3.20");
        put(m,2,5, TimeBand.OFF_PEAK,"3.80");

        put(m,3,1, TimeBand.OFF_PEAK,"3.20"); put(m,3,2, TimeBand.OFF_PEAK,"2.60");
        put(m,3,3, TimeBand.OFF_PEAK,"1.70"); put(m,3,4, TimeBand.OFF_PEAK,"2.50");
        put(m,3,5, TimeBand.OFF_PEAK,"3.10");

        put(m,4,1, TimeBand.OFF_PEAK,"3.70"); put(m,4,2, TimeBand.OFF_PEAK,"3.20");
        put(m,4,3, TimeBand.OFF_PEAK,"2.50"); put(m,4,4, TimeBand.OFF_PEAK,"1.60");
        put(m,4,5, TimeBand.OFF_PEAK,"2.40");

        put(m,5,1, TimeBand.OFF_PEAK,"4.20"); put(m,5,2, TimeBand.OFF_PEAK,"3.80");
        put(m,5,3, TimeBand.OFF_PEAK,"3.10"); put(m,5,4, TimeBand.OFF_PEAK,"2.40");
        put(m,5,5, TimeBand.OFF_PEAK,"1.50");

        return Map.copyOf(m);
    }

    private static void put(Map<String, BigDecimal> m, int from, int to, TimeBand band, String amount) {
        m.put(key(from, to, band), money(amount));
    }
}