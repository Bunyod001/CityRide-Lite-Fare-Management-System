import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * Money helps the program work with prices correctly.
 * It rounds all values to two decimal places and formats them for display.
 */
public class Money {

    public static BigDecimal toMoney(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public static String format(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}