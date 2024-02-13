public class BankAccountCommands {
    private final BankAccount bankAccount;

    public BankAccountCommands(BankAccount bankAccount, Printer printer) {
        this.bankAccount = bankAccount;
    }

    public String statement() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void deposit(int amount) {
        bankAccount.addTransaction(amount);
    }

    public void run(String s) {
        var amount = Integer.parseInt(s.split(" ")[1]);
        deposit(amount);
    }
}
