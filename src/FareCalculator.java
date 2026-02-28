import java.math.BigDecimal;

/*
 * FareCalculator applies the pricing rules.
 * The key point is that the daily cap depends on the running total,
 * so chargedFare may be smaller than discountedFare.
 */
public class FareCalculator {

    public static int calcZonesCrossed(int fromZone, int toZone) {
        return Math.abs(toZone - fromZone) + 1;
    }

    public static FareResult calculate(int fromZone,
                                       int toZone,
                                       CityRideDataset.TimeBand band,
                                       CityRideDataset.PassengerType type,
                                       BigDecimal currentRunningTotal) {

        BigDecimal baseFare = CityRideDataset.getBaseFare(fromZone, toZone, band);
        BigDecimal discountRate = CityRideDataset.DISCOUNT_RATE.get(type);

        BigDecimal discountedFare = baseFare.multiply(BigDecimal.ONE.subtract(discountRate));
        discountedFare = Money.toMoney(discountedFare);

        BigDecimal cap = CityRideDataset.DAILY_CAP.get(type);
        BigDecimal chargedFare = applyDailyCap(currentRunningTotal, discountedFare, cap);

        return new FareResult(baseFare, discountRate, discountedFare, chargedFare);
    }

    public static BigDecimal applyDailyCap(BigDecimal runningTotal, BigDecimal discountedFare, BigDecimal cap) {
        if (runningTotal.compareTo(cap) >= 0) {
            return Money.toMoney(BigDecimal.ZERO);
        }

        BigDecimal potentialTotal = runningTotal.add(discountedFare);
        if (potentialTotal.compareTo(cap) > 0) {
            return Money.toMoney(cap.subtract(runningTotal));
        }

        return Money.toMoney(discountedFare);
    }

    public static class FareResult {
        private final BigDecimal baseFare;
        private final BigDecimal discountRate;
        private final BigDecimal discountedFare;
        private final BigDecimal chargedFare;

        public FareResult(BigDecimal baseFare, BigDecimal discountRate, BigDecimal discountedFare, BigDecimal chargedFare) {
            this.baseFare = Money.toMoney(baseFare);
            this.discountRate = discountRate;
            this.discountedFare = Money.toMoney(discountedFare);
            this.chargedFare = Money.toMoney(chargedFare);
        }

        public BigDecimal getBaseFare() { return baseFare; }
        public BigDecimal getDiscountRate() { return discountRate; }
        public BigDecimal getDiscountedFare() { return discountedFare; }
        public BigDecimal getChargedFare() { return chargedFare; }
    }
}