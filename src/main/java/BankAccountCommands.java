public class BankAccountCommands {
    private final BankAccount bankAccount;

    public BankAccountCommands(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String statement() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void deposit(int i) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
