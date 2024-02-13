import java.time.LocalDate;
import java.util.List;

public class BankAccount {
    private final TransactionsStorage transactionsStorage;

    public BankAccount(TransactionsStorage transactionsStorage) {
        this.transactionsStorage = transactionsStorage;
    }

    public void addDeposit(LocalDate date, Double amount) {
        transactionsStorage.add(new Transaction(date, Transaction.Type.DEPOSIT, amount));
    }

    public void addWithdrawal(LocalDate date, Double amount) {
        transactionsStorage.add(new Transaction(date, Transaction.Type.WITHDRAWAL, amount));
    }

    public List<Transaction> listTransactions() {
        return transactionsStorage.all();
    }
}
