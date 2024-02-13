import static java.lang.Integer.parseInt;

record Command(String value) {
    public static Command of(String value) {
        return new Command(value);
    }

    public String name() {
        return valueAt(0);
    }

    public Double argument() {
        return Double.valueOf(valueAt(1));
    }

    private String valueAt(int index) {
        return value.split(" ")[index];
    }

    public boolean is(String name) {
        return name().equals(name);
    }
}
