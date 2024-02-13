import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionsStorageIntegrationTest {
    @Test
    void storesTransactions() {
        // given
        TransactionsStorage storage = new InMemoryTransactionsStorage();
        var firstTransaction = new Transaction(LocalDate.of(2020, 1, 10), Transaction.Type.DEPOSIT, 10.0);
        var secondTransaction = new Transaction(LocalDate.of(2021, 2, 11), Transaction.Type.DEPOSIT, 45.0);

        // when
        storage.add(firstTransaction);
        storage.add(secondTransaction);
        storage.all();

        // then
        assertThat(storage.all()).isEqualTo(List.of(firstTransaction, secondTransaction));
    }
}
