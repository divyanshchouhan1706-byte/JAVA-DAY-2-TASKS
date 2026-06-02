public class CheckingAccount extends BankAccount {

    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateInterest(int months) {
        return (balance * 1.5 * months) / (100 * 12);
    }
}