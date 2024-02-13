import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

class BankAccountCommandsTest {
    private final Transaction.Type DEPOSIT = Transaction.Type.DEPOSIT;

    private BankAccount bankAccount;
    private Printer printer;
    private DateProvider dateProvider;

    private BankAccountCommands commands;

    @BeforeEach
    void setUp() {
        bankAccount = mock(BankAccount.class);
        printer = mock(Printer.class);
        dateProvider = mock(DateProvider.class);

        commands = new BankAccountCommands(bankAccount, printer, dateProvider);
    }

    @Test
    void depositShouldGiveCommandToBankAccount() {
        // given
        var transactionDate = LocalDate.of(2020, 1, 10);
        when(dateProvider.today()).thenReturn(transactionDate);

        // when
        commands.run("type 10");

        // then
        verify(bankAccount).deposit(transactionDate, 10);
    }

    @Test
    void printsStatementHeader() {
        // given
        var expectedStatement = "date || credit || debit || balance";

        // when
        commands.run("statement");

        // then
        verify(printer).print(expectedStatement);
    }

    @Test
    void printsDepositTransactionsCorrectly() {
        // given
        when(bankAccount.transactions()).thenReturn(List.of(
                new Transaction(LocalDate.of(2020, 1, 10), DEPOSIT, 10.0),
                new Transaction(LocalDate.of(2021, 2, 11), DEPOSIT, 45.0),
                new Transaction(LocalDate.of(2022, 3, 12), DEPOSIT, 78.0)
        ));

        // when
        commands.run("statement");

        // then
        // TODO: Refactor to use ArgumentMatcher
        verify(printer).print("date || credit || debit || balance");
        verify(printer).print("2020-01-10 || 10.0 || ||");
        verify(printer).print("2021-02-11 || 45.0 || ||");
        verify(printer).print("2022-03-12 || 78.0 || ||");
    }
}
