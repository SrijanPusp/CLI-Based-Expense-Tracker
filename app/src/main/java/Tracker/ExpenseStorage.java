package Tracker;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class ExpenseStorage {
    private static String FILENAME = "expemse.txt";

    public static void saveExpense(ArrayList<Expense> expenses){
        try(PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))){
            for (Expense e : expenses ){
                writer.println(e.getDescription() + " , " + e.getDate() + " , " + e.getAmount());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Expense> loadExpense(){
        ArrayList<Expense> expenses = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILENAME))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                expenses.add(new Expense(new Date(Date.parse(parts[1])), parts[0].trim(), Double.parseDouble(parts[2].trim())));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return expenses;
    }

}
