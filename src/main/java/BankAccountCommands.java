import java.time.LocalDate;

public class BankAccountCommands {
    private final BankAccount bankAccount;

    public BankAccountCommands(BankAccount bankAccount, Printer printer, DateProvider dateProvider) {
        this.bankAccount = bankAccount;
    }

    public String statement() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void deposit(int amount) {
        bankAccount.deposit(amount, LocalDate.of(2020, 1, 1));
    }

    public void run(String s) {
        var amount = Integer.parseInt(s.split(" ")[1]);
        deposit(amount);
    }
}
