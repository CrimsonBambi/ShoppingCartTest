import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter language");
        String language = sc.nextLine();
        System.out.println("Enter country");
        String country = sc.nextLine();
        System.out.println("Selected language: " + language + " " + country + "\n");

        Locale locale = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", locale);

        String message = rb.getString("productAmount");
        String message2 = rb.getString("price");
        String message3 = rb.getString("quantity");
        String message4 = rb.getString("total");

        System.out.println(message);
        int amount = sc.nextInt();

        CartCalculator calculator = new CartCalculator();
        int[] itemTotals = new int[amount];

        for (int i = 0; i < amount; i++) {
            System.out.println(message2 + " " + (i + 1));
            int price = sc.nextInt();

            System.out.println(message3 + " " + (i + 1));
            int quantity = sc.nextInt();

            itemTotals[i] = calculator.calculateItemTotal(price, quantity);
        }

        int total = calculator.calculateCartTotal(itemTotals);
        System.out.println(message4 + " " + total);
    }
}

