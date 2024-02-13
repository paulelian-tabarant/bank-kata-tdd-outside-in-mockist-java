import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AcceptanceTest {
    @Test
    public void printsBankAccountStatement() {
        // given
        var printer = mock(Printer.class);
        var dateProvider = mock(DateProvider.class);
        var expectedStatement = """
                date || credit || debit || balance
                14/01/2012 || 100.0 || || 100.0
                """;

        var transactionsStorage = new InMemoryTransactionsStorage();
        var bankAccount = new BankAccount(transactionsStorage);

        // when
        var commands = new BankAccountCommands(bankAccount, printer, dateProvider);
        commands.run("deposit 100");
        commands.run("statement");

        // then
        verify(printer).print(expectedStatement);
    }
}
