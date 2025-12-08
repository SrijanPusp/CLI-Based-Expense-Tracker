package Tracker;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class ExpenseStorage {
    private static String FILENAME = "expense.txt";

    public static void saveExpense(ArrayList<Expense> expenses){
        try {
            File file = new File(FILENAME);
            // FIX: Create the directory if it's missing
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) file.createNewFile();

            PrintWriter writer = new PrintWriter(new FileWriter(file));
            for (Expense e : expenses) {
                writer.println(e.getDescription() + " , " + e.getDate() + " , " + e.getAmount() + " , " + e.getID());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    public static ArrayList<Expense> loadExpense(){
        ArrayList<Expense> expenses = new ArrayList<>();
        File file = new File(FILENAME);

        // FIX: Create the directory if it's missing
//        if (file.getParentFile() != null) {
//            file.getParentFile().mkdirs();
//        }
//
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                // Print the actual error so you can see it in the future
//                System.out.println("Error creating file: " + e.getMessage());
//                throw new RuntimeException(e);
//            }
//        }

        try(BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length >= 3) { // Basic check to avoid crashing on empty lines
                    expenses.add(new Expense(new Date(Date.parse(parts[1].trim())), parts[0].trim(), Double.parseDouble(parts[2].trim())));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
        return expenses;
    }
}