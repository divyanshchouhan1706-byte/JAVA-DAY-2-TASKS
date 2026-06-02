import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Bank bank = new Bank("JavaBank");

    public static void main(String[] args) {
        printWelcomeBanner();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1: createAccountFlow(); break;
                case 2: depositFlow(); break;
                case 3: withdrawFlow(); break;
                case 4: transferFlow(); break;
                case 5: checkBalanceFlow(); break;
                case 6: viewStatementFlow(); break;
                case 7: calculateInterestFlow(); break;
                case 8: bank.listAllAccounts(); break;
                case 9: bank.showSavingsAccounts(); break;
                case 0:
                    System.out.println("\n  Thank you for using JavaBank. Goodbye!\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n  ✗ Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // ─── Menu Flows ───────────────────────────────────────────────────────────

    static void createAccountFlow() {
        printSectionHeader("CREATE NEW ACCOUNT");

        System.out.print("  Enter full name      : ");
        String name = scanner.nextLine().trim();

        System.out.print("  Enter email          : ");
        String email = scanner.nextLine().trim();

        System.out.print("  Enter phone number   : ");
        String phone = scanner.nextLine().trim();

        System.out.println("  Account Type:");
        System.out.println("    1. SAVINGS (Interest: 3.5%)");
        System.out.println("    2. CHECKING (Interest: 1.5%)");
        int typeChoice = getIntInput("  Select type: ");
        String accountType = (typeChoice == 2) ? "CHECKING" : "SAVINGS";

        double initialDeposit = getDoubleInput("  Initial deposit ($) : ");

        bank.createAccount(name, email, phone, accountType, initialDeposit);
    }

    static void depositFlow() {
        printSectionHeader("DEPOSIT FUNDS");
        Account account = getAccountByNumber();
        if (account == null) return;

        account.printSummary();
        double amount = getDoubleInput("\n  Enter deposit amount ($): ");
        account.deposit(amount);
    }

    static void withdrawFlow() {
        printSectionHeader("WITHDRAW FUNDS");
        Account account = getAccountByNumber();
        if (account == null) return;

        account.printSummary();
        double amount = getDoubleInput("\n  Enter withdrawal amount ($): ");
        account.withdraw(amount);
    }

    static void transferFlow() {
        printSectionHeader("TRANSFER FUNDS");

        System.out.print("  Enter YOUR account number     : ");
        String fromAccNum = scanner.nextLine().trim();
        Account fromAccount = bank.findAccount(fromAccNum);

        if (fromAccount == null) {
            System.out.println("  ✗ Source account not found.");
            return;
        }

        System.out.print("  Enter TARGET account number   : ");
        String toAccNum = scanner.nextLine().trim();
        Account toAccount = bank.findAccount(toAccNum);

        if (toAccount == null) {
            System.out.println("  ✗ Target account not found.");
            return;
        }

        System.out.printf("  Transferring from: %s (%s)%n",
                fromAccount.getAccountNumber(), fromAccount.getCustomer().getName());
        System.out.printf("  Transferring to  : %s (%s)%n",
                toAccount.getAccountNumber(), toAccount.getCustomer().getName());

        double amount = getDoubleInput("  Enter transfer amount ($): ");
        fromAccount.transferTo(toAccount, amount);
    }

    static void checkBalanceFlow() {
        printSectionHeader("CHECK BALANCE");
        Account account = getAccountByNumber();
        if (account == null) return;
        account.printSummary();
    }

    static void viewStatementFlow() {
        printSectionHeader("ACCOUNT STATEMENT");
        Account account = getAccountByNumber();
        if (account == null) return;
        account.printStatement();
    }

    static void calculateInterestFlow() {
        printSectionHeader("CALCULATE INTEREST");
        Account account = getAccountByNumber();
        if (account == null) return;

        account.printSummary();
        int months = getIntInput("\n  Enter number of months: ");
        double interest = account.calculateInterest(months);

        System.out.println("\n  ┌──────────────────────────────────────────┐");
        System.out.printf( "  │  Principal    : $%-24.2f│%n", account.getBalance());
        System.out.printf( "  │  Interest Rate: %-25s│%n", account.getInterestRate() + "% p.a.");
        System.out.printf( "  │  Period       : %-25s│%n", months + " month(s)");
        System.out.println("  ├──────────────────────────────────────────┤");
        System.out.printf( "  │  Interest Earned: $%-22.2f│%n", interest);
        System.out.printf( "  │  Total Amount   : $%-22.2f│%n", account.getBalance() + interest);
        System.out.println("  └──────────────────────────────────────────┘");
    }

    // ─── Helper Methods ───────────────────────────────────────────────────────

    static Account getAccountByNumber() {
        System.out.print("  Enter account number: ");
        String accNum = scanner.nextLine().trim();
        Account account = bank.findAccount(accNum);
        if (account == null) {
            System.out.println("  ✗ Account not found. Please check the account number.");
        }
        return account;
    }

    static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Invalid input. Please enter a whole number.");
            }
        }
    }

    static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Invalid input. Please enter a valid amount.");
            }
        }
    }

    // ─── UI Elements ─────────────────────────────────────────────────────────

    static void printWelcomeBanner() {
        System.out.println("\n  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║                                                  ║");
        System.out.println("  ║           Welcome to JavaBank System             ║");
        System.out.println("  ║       Secure | Reliable | Always Open            ║");
        System.out.println("  ║                                                  ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝\n");
    }

    static void printMainMenu() {
        System.out.println("\n  ┌──────────────────────────────────────────┐");
        System.out.println("  │              MAIN MENU                   │");
        System.out.println("  ├──────────────────────────────────────────┤");
        System.out.println("  │   1. Create New Account                  │");
        System.out.println("  │   2. Deposit Funds                       │");
        System.out.println("  │   3. Withdraw Funds                      │");
        System.out.println("  │   4. Transfer Funds                      │");
        System.out.println("  │   5. Check Balance                       │");
        System.out.println("  │   6. View Account Statement              │");
        System.out.println("  │   7. Calculate Interest                  │");
        System.out.println("  │   8. List All Accounts                   │");
        System.out.println("  │   9. Show SavingAccounts                 │");
        System.out.println("  │   0. Exit                                │");
        System.out.println("  └──────────────────────────────────────────┘");
    }

    static void printSectionHeader(String title) {
        System.out.println("\n  ══════════════════════════════════════");
        System.out.printf( "   %s%n", title);
        System.out.println("  ══════════════════════════════════════");
    }
}
