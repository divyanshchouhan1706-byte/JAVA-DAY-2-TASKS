import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String accountType;       // SAVINGS, CHECKING
    private double balance;
    private double interestRate;
    private Customer customer;
    private List<Transaction> transactions;
    private int transactionCount;
    private boolean isActive;

    // Parameterized Constructor
    public Account(String accountNumber, String accountType, double initialDeposit, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountType = accountType.toUpperCase();
        this.balance = initialDeposit;
        this.customer = customer;
        this.transactions = new ArrayList<>();
        this.transactionCount = 0;
        this.isActive = true;

        // Set interest rate based on account type
        this.interestRate = accountType.equalsIgnoreCase("SAVINGS") ? 3.5 : 1.5;

        // Record opening transaction
        if (initialDeposit > 0) {
            addTransaction("DEPOSIT", initialDeposit, "Account Opening Deposit");
        }
    }

    // ─── Core Banking Operations ───────────────────────────────────────────────

    public boolean deposit(double amount) {
        if (!isActive) {
            System.out.println("  ✗ Account is inactive.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("  ✗ Deposit amount must be greater than zero.");
            return false;
        }
        balance += amount;
        addTransaction("DEPOSIT", amount, "Cash Deposit");
        System.out.printf("  ✓ $%.2f deposited successfully. New Balance: $%.2f%n", amount, balance);
        return true;
    }
    public boolean deposit(double amount, String note) {
    if (!isActive) {
        System.out.println("  ✗ Account is inactive.");
        return false;
    }

    if (amount <= 0) {
        System.out.println("  ✗ Deposit amount must be greater than zero.");
        return false;
    }

    balance += amount;
    addTransaction("DEPOSIT", amount, note);

    System.out.printf("  ✓ $%.2f deposited successfully. New Balance: $%.2f%n",
            amount, balance);

    return true;
}


    public boolean withdraw(double amount) {
        if (!isActive) {
            System.out.println("  ✗ Account is inactive.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("  ✗ Withdrawal amount must be greater than zero.");
            return false;
        }
        if (amount > balance) {
            System.out.printf("  ✗ Insufficient funds. Available balance: $%.2f%n", balance);
            return false;
        }
        balance -= amount;
        addTransaction("WITHDRAW", amount, "Cash Withdrawal");
        System.out.printf("  ✓ $%.2f withdrawn successfully. New Balance: $%.2f%n", amount, balance);
        return true;
    }

    public boolean transferTo(Account targetAccount, double amount) {
        if (!isActive || !targetAccount.isActive) {
            System.out.println("  ✗ One or both accounts are inactive.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("  ✗ Transfer amount must be greater than zero.");
            return false;
        }
        if (amount > balance) {
            System.out.printf("  ✗ Insufficient funds. Available balance: $%.2f%n", balance);
            return false;
        }
        balance -= amount;
        targetAccount.balance += amount;

        addTransaction("TRANSFER OUT", amount, "Transfer to Acc# " + targetAccount.getAccountNumber());
        targetAccount.addTransaction("TRANSFER IN", amount, "Transfer from Acc# " + this.accountNumber);

        System.out.printf("  ✓ $%.2f transferred to Account #%s. Your New Balance: $%.2f%n",
                amount, targetAccount.getAccountNumber(), balance);
        return true;
    }

    public double calculateInterest(int months) {
        // Simple Interest: P * R * T / 100
        double interest = (balance * interestRate * months) / (100.0 * 12);
        return interest;
    }

    // ─── Transaction Logging ──────────────────────────────────────────────────

    private void addTransaction(String type, double amount, String description) {
        transactionCount++;
        String txnId = "TXN" + String.format("%04d", transactionCount);
        transactions.add(new Transaction(txnId, type, amount, balance, description));
    }

    // ─── Display Methods ──────────────────────────────────────────────────────

    public void printStatement() {
        System.out.println("\n  ┌─────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("  │                         ACCOUNT STATEMENT                                  │");
        System.out.println("  ├─────────────────────────────────────────────────────────────────────────────┤");
        System.out.printf("  │  Account #: %-20s  Type: %-20s       │%n", accountNumber, accountType);
        System.out.printf("  │  Owner    : %-45s       │%n", customer.getName());
        System.out.printf("  │  Balance  : $%-44.2f       │%n", balance);
        System.out.println("  ├─────────────────────────────────────────────────────────────────────────────┤");
        if (transactions.isEmpty()) {
            System.out.println("  │  No transactions found.                                                     │");
        } else {
            for (Transaction txn : transactions) {
                System.out.println(txn.toString());
            }
        }
        System.out.println("  └─────────────────────────────────────────────────────────────────────────────┘");
    }

    public void printSummary() {
        System.out.println("\n  ┌──────────────────────────────────────────┐");
        System.out.printf( "  │  Account #  : %-27s│%n", accountNumber);
        System.out.printf( "  │  Type       : %-27s│%n", accountType);
        System.out.printf( "  │  Owner      : %-27s│%n", customer.getName());
        System.out.printf( "  │  Balance    : $%-26.2f│%n", balance);
        System.out.printf( "  │  Interest %% : %-27s│%n", interestRate + "%");
        System.out.printf( "  │  Status     : %-27s│%n", isActive ? "ACTIVE" : "INACTIVE");
        System.out.println("  └──────────────────────────────────────────┘");
    }

    // ─── Getters ──────────────────────────────────────────────────────────────

    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public double getInterestRate() { return interestRate; }
    public Customer getCustomer() { return customer; }
    public boolean isActive() { return isActive; }
    public List<Transaction> getTransactions() { return transactions; }

    public void setActive(boolean active) { this.isActive = active; }
}
