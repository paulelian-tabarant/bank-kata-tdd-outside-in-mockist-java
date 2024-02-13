import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BankAccountCommandsTest {

    @Test
    void depositShouldGiveCommandToBankAccount() {
        var printer = mock(Printer.class);
        var bankAccount = mock(BankAccount.class);

        var commands = new BankAccountCommands(bankAccount, printer);
        commands.run("deposit 10");

        verify(bankAccount).addTransaction(10);
    }
}