import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

class BankAccountTest {

    @Test
    void storesTransaction() {
        var transactionsStorage = Mockito.mock(TransactionsStorage.class);
        var bankAccount = new BankAccount(transactionsStorage);
        LocalDate transactionDate = LocalDate.of(2020, 1, 1);
        int depositAmount = 10;
        bankAccount.deposit(depositAmount, transactionDate);

        verify(transactionsStorage).add(new Transaction(transactionDate, Transaction.Type.DEPOSIT, depositAmount));
    }
}