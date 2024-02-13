import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class AcceptanceTest {
    @Test
    public void printsBankAccountStatement() {
        var printer = Mockito.mock(Printer.class);
        var expectedStatement = """
                date || credit || debit || balance
                14/01/2012 || 100.0 || || 100.0
                """;

        var commands = new BankAccountCommands(new BankAccount(), printer);
        commands.run("deposit 100");
        commands.run("statement");

        verify(printer).print(expectedStatement);
    }
}
