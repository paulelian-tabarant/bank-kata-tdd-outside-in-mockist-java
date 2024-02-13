import java.time.LocalDate;

public record Transaction(LocalDate c0, Type deposit, int i) {
    public enum Type { DEPOSIT }
}
