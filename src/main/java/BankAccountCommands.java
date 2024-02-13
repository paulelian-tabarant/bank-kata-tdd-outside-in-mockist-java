import java.time.format.DateTimeFormatter;

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

    public void deposit(int amount) {
        bankAccount.deposit(dateProvider.today(), amount);
    }

    public void run(String s) {
        String command = s.split(" ")[0];

        if (!(command.equals(DEPOSIT) || command.equals(STATEMENT))) {
            throw new UnsupportedOperationException("Not implemented");
        }

        if (command.equals(STATEMENT)) {
            printStatement();
            return;
        }

        var amount = Integer.parseInt(s.split(" ")[1]);
        deposit(amount);
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
