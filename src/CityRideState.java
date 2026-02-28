import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * CityRideState stores the main data of the program.
 * It keeps all journeys, running totals, and the current working date.
 * This class helps different features share and update the same information.
 */
public class CityRideState {

    public final List<Journey> journeys = new ArrayList<>();
    public int nextJourneyId = 1;

    // Single-day rule: once first journey is added, all journeys must use same date.
    public LocalDate recordingDate = null;

    public final Map<CityRideDataset.PassengerType, BigDecimal> runningTotals = new HashMap<>();

    public CityRideState() {
        initialiseRunningTotals();
    }

    public void initialiseRunningTotals() {
        runningTotals.put(CityRideDataset.PassengerType.ADULT, BigDecimal.ZERO);
        runningTotals.put(CityRideDataset.PassengerType.STUDENT, BigDecimal.ZERO);
        runningTotals.put(CityRideDataset.PassengerType.CHILD, BigDecimal.ZERO);
        runningTotals.put(CityRideDataset.PassengerType.SENIOR_CITIZEN, BigDecimal.ZERO);
    }

    // Renumber IDs 1..n after removals
    public void renumberJourneyIds() {
        int id = 1;
        for (Journey j : journeys) {
            j.setId(id++);
        }
        nextJourneyId = id;
    }

    // Needed after removal because daily cap depends on order
    public void recalculateAllCharges() {
        initialiseRunningTotals();

        for (Journey j : journeys) {
            BigDecimal currentTotal = runningTotals.get(j.getPassengerType());

            FareCalculator.FareResult fares = FareCalculator.calculate(
                    j.getFromZone(),
                    j.getToZone(),
                    j.getTimeBand(),
                    j.getPassengerType(),
                    currentTotal
            );

            runningTotals.put(j.getPassengerType(), Money.toMoney(currentTotal.add(fares.getChargedFare())));
            j.setCalculatedFares(fares.getBaseFare(), fares.getDiscountRate(), fares.getDiscountedFare(), fares.getChargedFare());
        }
    }
}