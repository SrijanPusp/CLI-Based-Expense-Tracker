package Tracker;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = ExpenseStorage.loadExpense();

        while(true){
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Update Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. View Summary of All Expenses");
            System.out.println("6. View Summary of Expenses for a Specific Month");
            System.out.println("7. Filter Expenses By category");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            Functions func = new Functions();
            switch (choice){
                case 1:
                    func.addExpense(scanner, expenses);
                    break;
                case 2:
                    func.updateExpense(scanner, expenses);
                    break;
                case 3:
                    func.deleteExpense(scanner, expenses);
                    break;
                case 4:
                    func.showExpenses();
                    break;
                case 5:
                    func.returnSummary();
                    break;
                case 6:
                    func.returnMonthSummary();
                    break;
                case 7:
                    func.filterByCategory(scanner);
                    break;
                case 8:
                    ExpenseStorage.saveExpense(expenses);
                    System.out.println("Exiting.... Hehe LOL");
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}
