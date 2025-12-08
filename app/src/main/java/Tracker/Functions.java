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
    public void deleteExpense(Scanner scanner, ArrayList<Expense> expenses){
        System.out.println("Enter the ID you wanna delete : ");
        int choice = scanner.nextInt();
        int index = choice - 1001;
        if(index<0 || index>listOfExpenses.size()) {
            System.out.println("Invalid index.");
        }else{
            listOfExpenses.remove(index);
            System.out.println("Expense deleted.");
        }
    }
    public void showExpenses(){
        for (int i = 0; i < listOfExpenses.size(); i++) {
            System.out.println(i + ": " + listOfExpenses.get(i));
        }
    }
    public void returnSummary(){
        double total = 0;
        for (Expense e : listOfExpenses) {
            total += e.getAmount();
        }
        System.out.println("Total expenses: " + total);
    }
    public double returnMonthSummary(){
        return 0;
    }
    public void updateExpense(Scanner scanner, ArrayList<Expense> expenses){
        System.out.print("Enter the ID of the expense to update: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        int index = choice - 1001;
        if (index >= 0 && index < expenses.size()) {
            System.out.print("Enter new input (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();
            Date date;
            try {
                LocalDate ld = LocalDate.parse(input); // validates format
                date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input format. Using today's input instead.");
                LocalDate todayFallback = LocalDate.now();
                date = Date.from(todayFallback.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }
            System.out.print("Enter new description: ");
            String description = scanner.nextLine();
            System.out.print("Enter new amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            expenses.set(index, new Expense(date, description, amount));
            System.out.println("Expense updated.");
        } else {
            System.out.println("Invalid index.");
        }
    }
    //later implementation
    public ArrayList<Expense> filterByCategory(Scanner scanner){
        return new ArrayList<>();
    }
}
