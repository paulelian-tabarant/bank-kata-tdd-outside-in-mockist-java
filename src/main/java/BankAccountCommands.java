import java.time.format.DateTimeFormatter;

public class BankAccountCommands {
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

        if (!(command.equals("type") || command.equals("statement"))) {
            throw new UnsupportedOperationException("Not implemented");
        }

        if (command.equals("statement")) {
            printer.print("date || credit || debit || balance");
            var transactions = bankAccount.transactions();
            for (var transaction : transactions) {
                var transactionStatement = String.format("%s || %s || ||", transaction.date().format(DateTimeFormatter.ISO_DATE), transaction.amount());
                printer.print(transactionStatement);
            }

            return;
        }

        var amount = Integer.parseInt(s.split(" ")[1]);
        deposit(amount);
    }
}
