import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

// Imports

// Homework 1: Sales Register Program
// Course: CIS357
// Due date: 07/05/2022
// Name: Justin Kruskie
// GitHub: https://github.com/jkruskie/CIS357-HW1-Kruskie
// Instructor: Il-Hyung Cho
// Program description: Emulate cash register at a Grocery Store

public class Item {
    public int[] codes;
    public String[] names;
    public double[] prices;
    private String fileName = "catalog.txt";

    // Constructor to import data from text file

    /**
     * Constructor
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Item() {
        // Import data from text file
        try {

            Scanner input = new Scanner(new File(fileName));

            // Get number of lines from input and reinitialize the arrays with the number of lines.
            // Then read in the data from the text file and insert it into the arrays
            long numLines = Files.lines(Paths.get(fileName)).count();
//            System.out.println("LINES: " + numLines);

            codes = new int[(int) numLines];
            names = new String[(int) numLines];
            prices = new double[(int) numLines];

            int i = 0;
            while (input.hasNextLine()) {
                String[] line = input.nextLine().split(",");

                // Print the line for debugging purposes
//                System.out.println("Code: " + line[0] + " Name: " + line[1] + " Price: " + line[2]);
                codes[i] = Integer.parseInt(line[0]);
                names[i] = line[1];
                prices[i] = Double.parseDouble(line[2]);
                i++;
            }

            // Print all the data from the arrays for debugging purposes
//            for (int j = 0; j < codes.length; j++) {
//                System.out.println("Code: " + codes[j] + " Name: " + names[j] + " Price: " + prices[j]);
//            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters for the data lists
    /**
     * Get the code of the item
     * @param index the index of the item in the list
     * @return the code of the item
     */
    public int getCode(int index) {
        return codes[index];
    }

    /**
     * Get the name of the item at the given index
     * @param index the index of the item
     * @return String the name of the item
     */
    public String getName(int index) {
        return names[index];
    }

    /**
     * Get the price of the item based on the code
     * @param index the index of the item in the array
     * @return price
     */
    public double getPrice(int index) {
        return prices[index];
    }

    /**
     * Get the total number of items in the catalog
     * @return int total number of items
     */
    public int getCodeTotal() {
        return codes.length;
    }
}
