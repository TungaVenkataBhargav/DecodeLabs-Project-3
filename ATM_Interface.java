import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println( amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;
    private final int PIN = 3016;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public boolean validatePin(int enteredPin) {
        return enteredPin == PIN;
    }

    public void displayMenu() {
        System.out.println("\n===== ATM MENU =====");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + account.getBalance());
    }
}

public class ATM_Interface {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankAccount account = new BankAccount(5000);
        ATM atm = new ATM(account);

        int attempts = 3;
        boolean authenticated = false;

        while (attempts > 0) {
            System.out.print("Enter ATM PIN: ");
            int enteredPin = sc.nextInt();

            if (atm.validatePin(enteredPin)) {
                authenticated = true;
                System.out.println("Login Successful!");
                break;
            } else {
                attempts--;
                System.out.println("Incorrect PIN. Attempts left: " + attempts);
            }
        }

        if (!authenticated) {
            System.out.println("Card Blocked! Too many incorrect attempts.");
            sc.close();
            return;
        }

        int choice;

        do {
            atm.displayMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    atm.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;

                case 3:
                    atm.checkBalance();
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
