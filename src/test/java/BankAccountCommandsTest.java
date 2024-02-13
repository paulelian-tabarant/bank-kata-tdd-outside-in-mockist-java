import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class BankAccountCommandsTest {

    @Test
    void depositShouldGiveCommandToBankAccount() {
        var bankAccount = mock(BankAccount.class);
        var printer = mock(Printer.class);
        var dateProvider = mock(DateProvider.class);

        var transactionDate = LocalDate.of(2020, 1, 10);
        when(dateProvider.today()).thenReturn(transactionDate);

        var commands = new BankAccountCommands(bankAccount, printer, dateProvider);
        commands.run("deposit 10");

        verify(bankAccount).deposit(10, LocalDate.of(2020, 1, 10));
    }
}
