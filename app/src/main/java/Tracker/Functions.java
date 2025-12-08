package Tracker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Functions {

    ArrayList<Expense> listOfExpenses = ExpenseStorage.loadExpense();
    public void addExpense(Scanner scanner, ArrayList<Expense> expenses){
        System.out.println("Is the expense older? (Enter n for negetive)");
        String choice = scanner.nextLine().trim().toLowerCase();

        Date date;
        if (choice.equals("n")) {
            // Use today's date
            LocalDate today = LocalDate.now();
            date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("Date set to today's date: " + date);
        }else {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate ld = LocalDate.parse(input); // validates format
                date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Using today's date instead.");
                LocalDate todayFallback = LocalDate.now();
                date = Date.from(todayFallback.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }
        }

        System.out.println("Enter Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter Amount: ");
        Double amount = scanner.nextDouble();
        scanner.nextLine(); // consume leftover newline

        expenses.add(new Expense(date, description, amount));
    }
    public void deleteExpense(Scanner exp, ArrayList<Expense> expenses){

    }
    public void showExpenses(){

    }
    public double returnSummary(){
        return 0;
    }
    public double returnMonthSummary(){
        return 0;
    }
    public void updateExpense(Scanner exp, ArrayList<Expense> expenses){

    }
    //later implementation
    public ArrayList<Expense> filterByCategory(Scanner scanner){
        return new ArrayList<>();
    }
}
