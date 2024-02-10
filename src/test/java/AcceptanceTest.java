import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {
    @Test
    public void printsBankAccountStatement() {
        var expectedStatement = """
                date || credit || debit || balance
                14/01/2012 || 100.0 || || 100.0
                """;

        var commands = new BankAccountCommands();
        commands.deposit(100);

        assertThat(commands.statement()).isEqualTo(expectedStatement);
    }
}
