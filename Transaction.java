import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionId;
    private String type;         // DEPOSIT, WITHDRAW, TRANSFER
    private double amount;
    private double balanceAfter;
    private String description;
    private String dateTime;

    // Parameterized Constructor
    public Transaction(String transactionId, String type, double amount, double balanceAfter, String description) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() { return balanceAfter; }
    public String getDescription() { return description; }
    public String getDateTime() { return dateTime; }

    @Override
    public String toString() {
        return String.format("  [%s] %-10s | %-10s | Amount: $%10.2f | Balance: $%10.2f | %s",
                transactionId, dateTime, type, amount, balanceAfter, description);
    }
}
