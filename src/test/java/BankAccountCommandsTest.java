import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BankAccountCommandsTest {
    private final Transaction.Type DEPOSIT = Transaction.Type.DEPOSIT;

    private BankAccount bankAccount;
    private Printer printer;
    private DateProvider dateProvider;

    private BankAccountCommands commands;

    @BeforeEach
    void setUp() {
        bankAccount = mock(BankAccount.class);
        printer = mock(Printer.class);
        dateProvider = mock(DateProvider.class);

        commands = new BankAccountCommands(bankAccount, printer, dateProvider);
    }

    @Test
    void givesDepositAmountAndDateToBankAccount() {
        // given
        var transactionDate = LocalDate.of(2020, 1, 10);
        when(dateProvider.today()).thenReturn(transactionDate);

        // when
        commands.run("deposit 10");

        // then
        verify(bankAccount).deposit(transactionDate, 10);
    }

    @Test
    void printsStatementHeader() {
        // given
        var statementHeader = "date || credit || debit || balance";

        // when
        commands.run("statement");

        // then
        verify(printer).print(statementHeader);
    }

    @Test
    void printsDepositTransactionsCorrectly() {
        // given
        when(bankAccount.transactions()).thenReturn(List.of(
                new Transaction(LocalDate.of(2020, 1, 10), DEPOSIT, 10.0),
                new Transaction(LocalDate.of(2021, 2, 11), DEPOSIT, 45.0),
                new Transaction(LocalDate.of(2022, 3, 12), DEPOSIT, 78.0)
        ));

        // when
        commands.run("statement");

        // then
        // TODO: Might use argument captor for easier comparison
        verify(printer).print("2020-01-10 || 10.0 || ||");
        verify(printer).print("2021-02-11 || 45.0 || ||");
        verify(printer).print("2022-03-12 || 78.0 || ||");
    }
}
