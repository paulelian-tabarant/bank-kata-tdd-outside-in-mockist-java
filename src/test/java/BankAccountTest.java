import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BankAccountTest {
    private TransactionsStorage transactionsStorage;

    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        transactionsStorage = mock(TransactionsStorage.class);
        bankAccount = new BankAccount(transactionsStorage);
    }

    @Test
    void storesTransaction() {
        // given
        var januaryFirst2020 = LocalDate.of(2020, 1, 1);
        int tenEuros = 10;
        var expectedTransaction = new Transaction(januaryFirst2020, Transaction.Type.DEPOSIT, tenEuros);

        // when
        bankAccount.deposit(januaryFirst2020, tenEuros);

        // then
        verify(transactionsStorage).add(expectedTransaction);
    }

    @Test
    void givesPastTransactions() {
        // given
        List<Transaction> storedTransactions = List.of(
                new Transaction(LocalDate.of(2020, 1, 10), Transaction.Type.DEPOSIT, 10.0),
                new Transaction(LocalDate.of(2021, 2, 11), Transaction.Type.DEPOSIT, 45.0),
                new Transaction(LocalDate.of(2022, 3, 12), Transaction.Type.DEPOSIT, 78.0));

        when(transactionsStorage.all()).thenReturn(storedTransactions);

        // when then
        assertThat(bankAccount.transactions()).isEqualTo(storedTransactions);
    }
}
