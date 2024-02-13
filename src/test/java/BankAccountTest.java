import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BankAccountTest {

    @Test
    void storesTransaction() {
        // given
        var transactionsStorage = mock(TransactionsStorage.class);
        var transactionDate = LocalDate.of(2020, 1, 1);
        int depositAmount = 10;

        var tenEurosDepositAtDate = new Transaction(transactionDate, Transaction.Type.DEPOSIT, depositAmount);

        // when
        var bankAccount = new BankAccount(transactionsStorage);
        bankAccount.deposit(transactionDate, depositAmount);

        // then
        verify(transactionsStorage).add(tenEurosDepositAtDate);
    }
}
