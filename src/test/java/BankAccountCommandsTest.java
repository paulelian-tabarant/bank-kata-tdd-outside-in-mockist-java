import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BankAccountCommandsTest {

    @Test
    void depositShouldGiveCommandToBankAccount() {
        var printer = mock(Printer.class);
        var bankAccount = mock(BankAccount.class);

        var commands = new BankAccountCommands(bankAccount, printer);
        commands.run("deposit 10");

        verify(bankAccount).deposit(10, LocalDate.of(2020, 1, 1));
    }
}