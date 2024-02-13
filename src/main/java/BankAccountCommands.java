import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ISO_DATE;

public class BankAccountCommands {
    public static final String STATEMENT = "statement";
    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";
    public static final String STATEMENT_HEADER = "date || credit || debit || balance";
    public static final String DEPOSIT_LINE_FORMAT = "%s || %s || || %s";
    public static final String WITHDRAWAL_LINE_FORMAT = "%s || || %s || %s";
    private final BankAccount account;
    private final Output output;

    private final DateProvider dateProvider;

    public BankAccountCommands(BankAccount account, Output output, DateProvider dateProvider) {
        this.account = account;
        this.output = output;
        this.dateProvider = dateProvider;
    }

    public void run(String commandString) {
        var command = Command.of(commandString);

        if (isUnknown(command)) {
            throw new UnsupportedOperationException("Not implemented");
        }

        if (command.is(STATEMENT)) {
            printStatement();
            return;
        }

        if (command.is(WITHDRAW)) {
            withdraw(command.argument());
            return;
        }

        deposit(command.argument());
    }

    private void withdraw(Double amount) {
        account.addWithdrawal(dateProvider.today(), amount);
    }

    private void deposit(Double amount) {
        account.addDeposit(dateProvider.today(), amount);
    }

    private void printStatement() {
        output.print(STATEMENT_HEADER);

        var balance = 0.0;
        for (var transaction : account.listTransactions()) {
            balance += transaction.balance();
            output.print(statementLine(transaction, balance));
        }
    }

    private static String statementLine(Transaction transaction, double balance) {
        var lineFormat = transaction.type() == Transaction.Type.DEPOSIT
                ? DEPOSIT_LINE_FORMAT
                : WITHDRAWAL_LINE_FORMAT;

        return format(lineFormat, transaction.date().format(ISO_DATE), transaction.amount(), balance);
    }

    private static boolean isUnknown(Command command) {
        return !(command.is(DEPOSIT) || command.is(STATEMENT) || command.is(WITHDRAW));
    }
}
