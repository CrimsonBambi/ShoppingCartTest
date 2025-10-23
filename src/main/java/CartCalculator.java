public class CartCalculator {

    public int calculateItemTotal(int price, int quantity) {
        return price * quantity;
    }

    public int calculateCartTotal(int[] itemTotals) {
        int total = 0;
        for (int t : itemTotals) {
            total += t;
        }
        return total;
    }
}

