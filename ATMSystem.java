import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
            return;
        }
        balance += amount;
        System.out.println("Successfully deposited: $" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance. Transaction failed.");
            return false;
        }
        balance -= amount;
        System.out.println("Successfully withdrawn: $" + amount);
        return true;   
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Deposit Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidIntegerInput();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = getValidDoubleInput();
        account.withdraw(amount);
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = getValidDoubleInput();
        account.deposit(amount);
    }

    private void checkBalance() {
        System.out.println("Your current balance: $" + account.getBalance());
    }

    private int getValidIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private double getValidDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid amount: ");
            }
        }
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00); // Initial balance: $1000
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
