import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcceptanceTest {
    @Test
    public void printsBankAccountStatement() {
        // given
        var bankAccount = new BankAccount(new InMemoryTransactionsStorage());
        var printer = mock(Output.class);
        var dateProvider = mock(DateProvider.class);

        when(dateProvider.today()).thenReturn(LocalDate.of(2012, 1, 14));

        // when
        var commands = new BankAccountCommands(bankAccount, printer, dateProvider);
        commands.run("deposit 100");
        commands.run("withdraw 30.5");
        commands.run("statement");

        // then
        verify(printer).print("date || credit || debit || balance");
        verify(printer).print("2012-01-14 || 100.0 || || 100.0");
        verify(printer).print("2012-01-14 || 30.5 || || 69.5");
    }
}
