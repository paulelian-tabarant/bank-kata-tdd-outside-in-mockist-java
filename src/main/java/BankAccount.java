import java.time.LocalDate;
import java.util.List;

public class BankAccount {
    private final TransactionsStorage transactionsStorage;

    public BankAccount(TransactionsStorage transactionsStorage) {
        this.transactionsStorage = transactionsStorage;
    }

    public void addDeposit(LocalDate localDate, double amount) {
        transactionsStorage.add(new Transaction(localDate, Transaction.Type.DEPOSIT, amount));
    }

    public List<Transaction> listTransactions() {
        return transactionsStorage.all();
    }

    public void addWithdrawal(LocalDate transactionDate, double v) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
