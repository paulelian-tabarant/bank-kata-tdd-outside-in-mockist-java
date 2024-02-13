import java.util.Optional;

import static java.lang.Integer.parseInt;

record Command(String value) {
    public static Command of(String value) {
        return new Command(value);
    }

    public String name() {
        return valueAt(0);
    }

    public Optional<Integer> argument() {
        return value.split(" ").length > 1
                ? Optional.of(parseInt(valueAt(1)))
                : Optional.empty();
    }

    private String valueAt(int index) {
        return value.split(" ")[index];
    }
}
