import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BankAccountCommandsTest {
    private static final Transaction.Type DEPOSIT = Transaction.Type.DEPOSIT;
    private static final Transaction.Type WITHDRAWAL = Transaction.Type.WITHDRAWAL;

    private BankAccount bankAccount;
    private Output output;
    private DateProvider dateProvider;

    private BankAccountCommands commands;

    @BeforeEach
    void setUp() {
        bankAccount = mock(BankAccount.class);
        output = mock(Output.class);
        dateProvider = mock(DateProvider.class);

        commands = new BankAccountCommands(bankAccount, output, dateProvider);
    }

    @Test
    void givesDepositAmountAndDateToBankAccount() {
        // given
        var transactionDate = LocalDate.of(2020, 1, 10);
        when(dateProvider.today()).thenReturn(transactionDate);

        // when
        commands.run("deposit 10");

        // then
        verify(bankAccount).addDeposit(transactionDate, 10.0);
    }

    @Test
    void givesWithdrawalAmountAndDateToBankAccount() {
        // given
        var transactionDate = LocalDate.of(2020, 1, 10);
        when(dateProvider.today()).thenReturn(transactionDate);

        // when
        commands.run("withdraw 20.30");

        // then
        verify(bankAccount).addWithdrawal(transactionDate, 20.30);
    }

    @Test
    void printsStatementHeader() {
        // given
        var statementHeader = "date || credit || debit || balance";

        // when
        commands.run("statement");

        // then
        verify(output).print(statementHeader);
    }

    @Test
    void printsDepositsAndWithdrawalsWithUpdatedBalance() {
        // given
        when(bankAccount.listTransactions()).thenReturn(List.of(
                new Transaction(LocalDate.of(2020, 1, 10), DEPOSIT, 10.0),
                new Transaction(LocalDate.of(2021, 2, 11), WITHDRAWAL, 45.0),
                new Transaction(LocalDate.of(2022, 3, 12), DEPOSIT, 78.0)
        ));

        // when
        commands.run("statement");

        // then
        verify(output).print("2020-01-10 || 10.0 || || 10.0");
        verify(output).print("2021-02-11 || || 45.0 || -35.0");
        verify(output).print("2022-03-12 || 78.0 || || 43.0");
    }
}
