import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ISO_DATE;

public class BankAccountCommands {
    public static final String STATEMENT = "statement";
    public static final String DEPOSIT = "deposit";
    public static final String STATEMENT_HEADER = "date || credit || debit || balance";
    private final BankAccount bankAccount;
    private final Printer printer;

    private final DateProvider dateProvider;

    public BankAccountCommands(BankAccount bankAccount, Printer printer, DateProvider dateProvider) {
        this.bankAccount = bankAccount;
        this.printer = printer;
        this.dateProvider = dateProvider;
    }

    public void run(String commandString) {
        var command = Command.of(commandString);

        var name = command.name();

        if (!(name.equals(DEPOSIT) || name.equals(STATEMENT))) {
            throw new UnsupportedOperationException("Not implemented");
        }

        if (name.equals(STATEMENT)) {
            printStatement();
            return;
        }

        var amount = command.argument().orElseThrow();
        deposit(amount);
    }

    private void deposit(int amount) {
        bankAccount.deposit(dateProvider.today(), amount);
    }

    private void printStatement() {
        printer.print(STATEMENT_HEADER);

        for (var transaction : bankAccount.transactions()) {
            printer.print(formatted(transaction));
        }
    }

    private static String formatted(Transaction transaction) {
        return format("%s || %s || ||", transaction.date().format(ISO_DATE), transaction.amount());
    }
}
