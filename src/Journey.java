import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/*
 * Journey stores both the user inputs and the calculated fare values.
 * This makes listing and summary features easier, because the program
 * does not need to recalculate fares every time it displays a journey.
 */
public class Journey {

    private int id;
    private final LocalDate date;
    private final CityRideDataset.TimeBand timeBand;
    private final int fromZone;
    private final int toZone;
    private final CityRideDataset.PassengerType passengerType;

    private final int zonesCrossed;
    private BigDecimal baseFare;
    private BigDecimal discountRate;
    private BigDecimal discountedFare;
    private BigDecimal chargedFare;

    private static final DateTimeFormatter OUTPUT_DATE_FMT = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    public Journey(int id,
                   LocalDate date,
                   CityRideDataset.TimeBand timeBand,
                   int fromZone,
                   int toZone,
                   CityRideDataset.PassengerType passengerType,
                   int zonesCrossed,
                   BigDecimal baseFare,
                   BigDecimal discountRate,
                   BigDecimal discountedFare,
                   BigDecimal chargedFare) {
        this.id = id;
        this.date = date;
        this.timeBand = timeBand;
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.passengerType = passengerType;
        this.zonesCrossed = zonesCrossed;
        this.baseFare = baseFare;
        this.discountRate = discountRate;
        this.discountedFare = discountedFare;
        this.chargedFare = chargedFare;
    }

    // Get methods
    public int getId() { return id; }
    public LocalDate getDate() { return date; }
    public CityRideDataset.TimeBand getTimeBand() { return timeBand; }
    public int getFromZone() { return fromZone; }
    public int getToZone() { return toZone; }
    public CityRideDataset.PassengerType getPassengerType() { return passengerType; }
    public int getZonesCrossed() { return zonesCrossed; }
    public BigDecimal getBaseFare() { return baseFare; }
    public BigDecimal getDiscountRate() { return discountRate; }
    public BigDecimal getDiscountedFare() { return discountedFare; }
    public BigDecimal getChargedFare() { return chargedFare; }

    // Set methods
    public void setId(int id) { this.id = id; }

    public void setCalculatedFares(BigDecimal baseFare,
                                   BigDecimal discountRate,
                                   BigDecimal discountedFare,
                                   BigDecimal chargedFare) {
        this.baseFare = baseFare;
        this.discountRate = discountRate;
        this.discountedFare = discountedFare;
        this.chargedFare = chargedFare;
    }

    public String toDisplayString() {
        String dateStr = date.format(OUTPUT_DATE_FMT);
        BigDecimal discountPercent = discountRate.multiply(new BigDecimal("100"));

        return "ID " + id +
                " | Date: " + dateStr +
                " | From: " + fromZone +
                " | To: " + toZone +
                " | TimeBand: " + timeBand +
                " | Passenger: " + passengerType +
                " | Zones crossed: " + zonesCrossed +
                " | Base fare: GBP " + Money.format(baseFare) +
                " | Discount: " + Money.format(discountPercent) + "%" +
                " | Charged: GBP " + Money.format(chargedFare);
    }
}