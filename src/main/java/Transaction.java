import java.time.LocalDate;

public record Transaction(LocalDate date, Type type, Double amount) {
    public enum Type {WITHDRAWAL, DEPOSIT}

    public double balance() {
        return type == Type.DEPOSIT ? amount : -amount;
    }
}
