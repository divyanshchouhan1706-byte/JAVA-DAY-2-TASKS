import java.util.HashMap;


public class Bank {
    private String bankName;
    private HashMap<String, Account> accounts;
    private int accountCounter;

    // Parameterized Constructor
    public Bank(String bankName) {
        this.bankName = bankName;
        this.accounts = new HashMap<>();
        this.accountCounter = 1000;
    }

    // ─── Account Management ───────────────────────────────────────────────────

    public Account createAccount(String ownerName, String email, String phone,
                                  String accountType, double initialDeposit) {
        accountCounter++;
        String customerId = "CUST" + accountCounter;
        String accountNumber = "ACC" + accountCounter;

        Customer customer = new Customer(customerId, ownerName, email, phone);
        Account account = new Account(accountNumber, accountType, initialDeposit, customer);
        accounts.put(account.getAccountNumber(), account);

        System.out.println("\n  ✓ Account created successfully!");
        account.printSummary();
        return account;
    }

    public Account findAccount(String accountNumber) {
    return accounts.get(accountNumber);
    }
    public void listAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\n  No accounts found in the system.");
            return;
        }
        System.out.println("\n  ╔══════════════════════════════════════════════════╗");
        System.out.printf( "  ║  %-48s║%n", bankName + " - All Accounts");
        System.out.println("  ╠══════════════════════════════════════════════════╣");
        for (Account acc : accounts.values()) {
            System.out.printf("  ║  %-12s | %-10s | %-10s | $%-8.2f║%n",
                    acc.getAccountNumber(),
                    acc.getAccountType(),
                    acc.getCustomer().getName().length() > 10
                            ? acc.getCustomer().getName().substring(0, 10)
                            : acc.getCustomer().getName(),
                    acc.getBalance());
        }
        System.out.println("  ╚══════════════════════════════════════════════════╝");
    }
    public void showSavingsAccounts() {

    System.out.println("\n===== SAVINGS ACCOUNTS =====");

    for (Account acc : accounts.values()) {

        if (acc.getAccountType().equalsIgnoreCase("SAVINGS")) {

            acc.printSummary();

        }
    }
}

    public String getBankName() { return bankName; }
    public int getTotalAccounts() { return accounts.size(); }
}
