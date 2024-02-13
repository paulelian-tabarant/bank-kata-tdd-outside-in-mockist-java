import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionsStorage implements TransactionsStorage {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void add(Transaction transaction) {
        transactions.add(transaction);
    }
}
