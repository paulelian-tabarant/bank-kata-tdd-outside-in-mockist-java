import java.time.LocalDate;

public record Transaction(LocalDate c0, Type deposit, double i) {
    public enum Type { DEPOSIT }
}
