import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class BankAccountCommandsTest {

    @Test
    void depositShouldGiveCommandToBankAccount() {
        // given
        var bankAccount = mock(BankAccount.class);
        var printer = mock(Printer.class);
        var dateProvider = mock(DateProvider.class);

        var transactionDate = LocalDate.of(2020, 1, 10);
        when(dateProvider.today()).thenReturn(transactionDate);

        // when
        var commands = new BankAccountCommands(bankAccount, printer, dateProvider);
        commands.run("deposit 10");

        // then
        verify(bankAccount).deposit(transactionDate, 10);
    }

    @Test
    void printsStatementHeader() {
        // given
        var bankAccount = mock(BankAccount.class);
        var printer = mock(Printer.class);
        var dateProvider = mock(DateProvider.class);

        var expectedStatement = "date || credit || debit || balance";

        // when
        var commands = new BankAccountCommands(bankAccount, printer, dateProvider);
        commands.run("statement");

        // then
        verify(printer).print(expectedStatement);
    }
}
