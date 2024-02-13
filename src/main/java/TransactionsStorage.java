import java.util.List;

public interface TransactionsStorage {
    void add(Transaction transaction);

    List<Transaction> all();
}
