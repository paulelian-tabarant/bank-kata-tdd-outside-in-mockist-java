import java.time.LocalDate;

public class BankAccount {
    private final TransactionsStorage transactionsStorage;

    public BankAccount(TransactionsStorage transactionsStorage) {

        this.transactionsStorage = transactionsStorage;
    }

    public void deposit(int i, LocalDate localDate) {
        transactionsStorage.add(new Transaction(localDate, Transaction.Type.DEPOSIT, i));
    }
}
