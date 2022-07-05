import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

// Homework 1: Sales Register Program
// Course: CIS357
// Due date: 07/05/2022
// Name: Justin Kruskie
// GitHub: github.com/jkruskie
// Instructor: Il-Hyung Cho
// Program description: Emulate cash register at a Grocery Store

public class CashRegister {

    private String[] items;
    private static int selectedCode = 0;
    private static int quantity = 0;
    private static double total = 0;
    private static double totalTax = 0;
    private static double totalSales = 0;
    private static int totalTransactions = 0;
    private static double dailySales = 0;
    static Scanner input;
    private static ArrayList<String> transactionList = new ArrayList<String>();
    // You stated to use arrays for the item data. This isn't necessarily the "item data"
    // but just a list of strings containing the item list.
    // The actual item data in the Item class is arrays

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // Scanner for user input
        input = new Scanner(System.in);

        // Print beginning message
        System.out.print("Beginning a new sale (Y/N)? ");
        String newSale = input.next();

        while(newSale.toLowerCase().equals("y")) {
            if (newSale.toLowerCase().equals("y")) {
                do {
                    // Start transaction
                    // Create new item object
                    Item item = new Item();
                    // Reset variables for each transaction
                    resetVariables();

                    System.out.print("Enter product code: ");
                    // Get code from user
                    String code = input.next();

                    // Check that the code is between 0 and 9 in ascii
                    // If not, ask for another code
                    while(checkCode(code, item.getCodeTotal()) == false) {
                        // Get new code from user
                        code = input.next();
                    }

                    // Check if code is -1 to end transaction, else continue
                    selectedCode = Integer.parseInt(code);
                    if(selectedCode == -1) {
                        continue;
                    }

                    // Code is valid, get the name and price of the item
                    String name = item.getName(selectedCode);
                    double price = item.getPrice(selectedCode);

                    System.out.println("         Item name: " + name);

                    System.out.print("Enter quantity:     ");

                    // Get quantity from user
                    String amount = input.next();
                    while(checkQuantity(amount) == false) {
                        // Get new quantity from user
                        amount = input.next();
                    }

                    quantity = Integer.parseInt(amount);

                    // Quantity is valid, get the total price of the item and add it to the totalSales
                    total = quantity * price;
                    totalSales = totalSales + total;
                    System.out.println("       Total price: $   " + String.format("%.2f", total));

                    // Increment the total transactions and add the transaction to the transactionList for printing
                    totalTransactions++;
                    String transactionStr = String.format("%5d  %1s        $   %4.2f", quantity, name, total);
                    transactionList.add(transactionStr);

                } while (selectedCode != -1);

                // Selected code is -1, end transaction and print the required information
                completeSale();

                // Ask user if they want to continue a new sale
                System.out.println("Beginning a new sale (Y/N)? ");
                newSale = input.next();

            }
        } // User entered "N", end program
        closeProgram();
    }

    /**
     * Close the program
     * @return void
     */
    private static void closeProgram() {
        System.out.println("Total sales of the day is $ "+String.format("%.2f", dailySales));
        System.out.println("Thanks for using POST System. Goodbye.");
    }

    /**
     * Check if the given quantity is valid
     * @param amount String containing the quantity entered by the user
     * @return boolean true if the quantity is valid, false if not
     */
    private static boolean checkQuantity(String amount) {
        try
        {
            int num = Integer.parseInt(amount);
                return true;
        }
        catch(NumberFormatException e)
        {
            System.out.println("!!! Invalid quantity");
            System.out.print("Enter quantity:     ");
            return false;
        }
    }

    /**
     * Checks if the code is between 0 and 9 in ascii
     * @param code String to check
     * @param lengthOfCodes int length of the array of codes
     * @return boolean true if code integer and that it exists in array, false otherwise
     */
    private static boolean checkCode(String code, int lengthOfCodes) {
        try
        {
            int num = Integer.parseInt(code);
            // Check if num is within the length of the array
            if(num >= 0 && num <= lengthOfCodes) {
                return true;
            }
            else if(num == -1) {
                // Check for -1 to end transaction
                return true;
            }
            else {
                System.out.println("!!! Invalid code");
                System.out.print("Enter product code: ");
                return false;
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println("!!! Invalid product code");
            System.out.print("Enter product code: ");
            return false;
        }
    }

    /**
     * Print the end of the sale and print the transaction list
     * @return void
     */
    private static void completeSale() {
        // Print ending message
        System.out.println("----------------------------");
        System.out.println("Items list:");

        // Sort through the transactionList
        Collections.sort(transactionList);

        // Loop through transactionList and print each item
        for(int i = 0; i < transactionList.size(); i++) {
            System.out.println(transactionList.get(i));
        }

        // Get total with tax and save this to dailySales
        totalTax = totalSales + (totalSales * 0.06);
        dailySales = dailySales + totalTax;


        System.out.println("Subtotal            $   " + String.format("%.2f", totalSales));
        System.out.println("Total with Tax (6%) $   " + String.format("%.2f", totalTax));

        System.out.print("Tendered Amount     $   ");

        double tender = input.nextDouble();

        while(tender < totalTax) {
            System.out.println("!!! Invalid amount");
            System.out.print("Tendered Amount     $   ");
            tender = input.nextDouble();
        }
        double change = tender - totalTax;

        System.out.println("Change              $   " + String.format("%.2f", change));

        System.out.println("----------------------------");
    }

    /**
     * Reset all global variables per transaction
     * @return void
     */
    private static void resetVariables() {
        // Reset all the global variables
        selectedCode = 0;
        quantity = 0;
        total = 0;
        totalTax = 0;
        totalSales = 0;
        totalTransactions = 0;
        transactionList = new ArrayList<String>();
    }
}