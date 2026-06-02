public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateInterest(int months) {
        return (balance * 3.5 * months) / (100 * 12);
    }
}
