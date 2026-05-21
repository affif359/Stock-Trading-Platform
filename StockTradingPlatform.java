import java.util.HashMap;
import java.util.Scanner;

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double balance = 10000;

        HashMap<String, Integer> portfolio = new HashMap<>();

        String[] stockNames = {"Apple", "Tesla", "Google"};
        double[] stockPrices = {200, 250, 180};

        while (true) {

            System.out.println("\n===== Stock Trading Platform =====");

            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.println("\nAvailable Stocks:");

                for (int i = 0; i < stockNames.length; i++) {

                    System.out.println(
                        (i + 1) + ". " +
                        stockNames[i] +
                        " $ " +
                        stockPrices[i]
                    );
                }
            }

            else if (choice == 2) {

                System.out.println("\nChoose Stock:");

                for (int i = 0; i < stockNames.length; i++) {

                    System.out.println(
                        (i + 1) + ". " + stockNames[i]
                    );
                }

                int stockChoice = sc.nextInt() - 1;

                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();

                double totalCost =
                    stockPrices[stockChoice] * quantity;

                if (totalCost > balance) {

                    System.out.println("Insufficient balance!");
                }

                else {

                    balance -= totalCost;

                    portfolio.put(
                        stockNames[stockChoice],

                        portfolio.getOrDefault(
                            stockNames[stockChoice],
                            0
                        ) + quantity
                    );

                    System.out.println(
                        "Stock purchased successfully!"
                    );
                }
            }

            else if (choice == 3) {

                System.out.println("\nChoose Stock:");

                for (int i = 0; i < stockNames.length; i++) {

                    System.out.println(
                        (i + 1) + ". " + stockNames[i]
                    );
                }

                int stockChoice = sc.nextInt() - 1;

                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();

                int owned = portfolio.getOrDefault(
                    stockNames[stockChoice],
                    0
                );

                if (quantity > owned) {

                    System.out.println(
                        "Not enough shares to sell!"
                    );
                }

                else {

                    balance +=
                        stockPrices[stockChoice] * quantity;

                    portfolio.put(
                        stockNames[stockChoice],
                        owned - quantity
                    );

                    System.out.println(
                        "Stock sold successfully!"
                    );
                }
            }

            else if (choice == 4) {

                System.out.println("\n----- Portfolio -----");

                for (String stock : portfolio.keySet()) {

                    int quantity = portfolio.get(stock);

                    if (quantity > 0) {

                        System.out.println(
                            stock +
                            " : " +
                            quantity +
                            " shares"
                        );
                    }
                }

                System.out.println(
                    "Balance: $" + balance
                );
            }

            else if (choice == 5) {

                System.out.println("Exiting platform...");
                break;
            }

            else {

                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}