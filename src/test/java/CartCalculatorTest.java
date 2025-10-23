import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CartCalculatorTest {

    private final CartCalculator calculator = new CartCalculator();

    @Test
    void testCalculateItemTotal() {
        assertEquals(50, calculator.calculateItemTotal(10, 5));
        assertEquals(0, calculator.calculateItemTotal(0, 100));
        assertEquals(0, calculator.calculateItemTotal(100, 0));
    }

    @Test
    void testCalculateCartTotal() {
        int[] items = {100, 200, 300};
        assertEquals(600, calculator.calculateCartTotal(items));

        int[] single = {250};
        assertEquals(250, calculator.calculateCartTotal(single));

        int[] empty = {};
        assertEquals(0, calculator.calculateCartTotal(empty));
    }

    @Test
    void testCombinedLogic() {
        int totalItem1 = calculator.calculateItemTotal(10, 2); // 20
        int totalItem2 = calculator.calculateItemTotal(5, 4);  // 20
        int[] totals = {totalItem1, totalItem2};
        assertEquals(40, calculator.calculateCartTotal(totals));
    }
}
