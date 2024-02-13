public class BankAccountCommands {
    private final BankAccount bankAccount;
    private final DateProvider dateProvider;

    public BankAccountCommands(BankAccount bankAccount, Printer printer, DateProvider dateProvider) {
        this.bankAccount = bankAccount;
        this.dateProvider = dateProvider;
    }

    public String statement() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void deposit(int amount) {
        bankAccount.deposit(dateProvider.today(), amount);
    }

    public void run(String s) {
        var amount = Integer.parseInt(s.split(" ")[1]);
        deposit(amount);
    }
}
