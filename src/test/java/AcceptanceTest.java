import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class AcceptanceTest {
    @Test
    public void printsBankAccountStatement() {
        // given
        var printer = mock(Printer.class);
        var dateProvider = mock(DateProvider.class);
        var expectedStatement = """
                date || credit || debit || balance
                2012-01-14 || 100.0 || || 100.0
                """;

        when(dateProvider.today()).thenReturn(LocalDate.of(2012, 1, 14));

        var transactionsStorage = new InMemoryTransactionsStorage();
        var bankAccount = new BankAccount(transactionsStorage);

        // when
        var commands = new BankAccountCommands(bankAccount, printer, dateProvider);
        commands.run("type 100");
        commands.run("statement");

        // then
        verify(printer).print(expectedStatement);
    }
}
