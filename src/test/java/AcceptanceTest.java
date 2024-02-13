import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AcceptanceTest {
    @Test
    public void printsBankAccountStatement() {
        // given
        var printer = mock(Printer.class);
        var expectedStatement = """
                date || credit || debit || balance
                14/01/2012 || 100.0 || || 100.0
                """;

        var transactionsStorage = new InMemoryTransactionsStorage();
        var bankAccount = new BankAccount(transactionsStorage);

        // when
        var commands = new BankAccountCommands(bankAccount, printer);
        commands.run("deposit 100");
        commands.run("statement");

        // then
        verify(printer).print(expectedStatement);
    }
}
