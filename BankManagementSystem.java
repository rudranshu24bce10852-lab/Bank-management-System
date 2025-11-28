import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String holderName;
    private double balance;

    public Account(int accountNumber, String holderName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposited " + amount + ". New balance = " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        balance -= amount;
        System.out.println("Withdrew " + amount + ". New balance = " + balance);
    }

    public void displayInfo() {
        System.out.println("Account No : " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance    : " + balance);
    }
}

class Bank {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account acc) {
        accounts.add(acc);
        System.out.println("Account created successfully.");
    }

    public Account findAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    public void removeAccount(int accNo) {
        Account acc = findAccount(accNo);
        if (acc != null) {
            accounts.remove(acc);
            System.out.println("Account closed successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void depositToAccount(int accNo, double amount) {
        Account acc = findAccount(accNo);
        if (acc == null) {
            System.out.println("Account not found.");
        } else {
            acc.deposit(amount);
        }
    }

    public void withdrawFromAccount(int accNo, double amount) {
        Account acc = findAccount(accNo);
        if (acc == null) {
            System.out.println("Account not found.");
        } else {
            acc.withdraw(amount);
        }
    }

    public void displayAccount(int accNo) {
        Account acc = findAccount(accNo);
        if (acc == null) {
            System.out.println("Account not found.");
        } else {
            acc.displayInfo();
        }
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in bank.");
            return;
        }
        for (Account acc : accounts) {
            System.out.println("------------------------");
            acc.displayInfo();
        }
        System.out.println("------------------------");
    }
}

public class BankManagementSystem {
    private static Bank bank = new Bank();
    private static Scanner sc = new Scanner(System.in);
    private static int nextAccountNumber = 1001;

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    closeAccount();
                    break;
                case 6:
                    displayAll();
                    break;
                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("========== BANK MENU ==========");
        System.out.println("1. Create new account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check balance / account details");
        System.out.println("5. Close account");
        System.out.println("6. Display all accounts");
        System.out.println("0. Exit");
        System.out.println("================================");
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        double openingBalance = readDouble("Enter opening balance: ");

        Account acc = new Account(nextAccountNumber, name, openingBalance);
        bank.addAccount(acc);
        System.out.println("New Account Number: " + nextAccountNumber);
        nextAccountNumber++;
    }

    private static void deposit() {
        int accNo = readInt("Enter account number: ");
        double amount = readDouble("Enter amount to deposit: ");
        bank.depositToAccount(accNo, amount);
    }

    private static void withdraw() {
        int accNo = readInt("Enter account number: ");
        double amount = readDouble("Enter amount to withdraw: ");
        bank.withdrawFromAccount(accNo, amount);
    }

    private static void checkBalance() {
        int accNo = readInt("Enter account number: ");
        bank.displayAccount(accNo);
    }

    private static void closeAccount() {
        int accNo = readInt("Enter account number to close: ");
        bank.removeAccount(accNo);
    }

    private static void displayAll() {
        bank.displayAllAccounts();
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String line = sc.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer, try again.");
            }
        }
    }

    private static double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String line = sc.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }
}
