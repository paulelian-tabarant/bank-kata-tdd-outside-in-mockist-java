import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BankAccountCommandsTest {

    @Test
    void depositShouldGiveCommandToBankAccount() {
        var bankAccount = mock(BankAccount.class);

        var commands = new BankAccountCommands(bankAccount);
        commands.deposit(100);

        verify(bankAccount).addTransaction(100);
    }
}