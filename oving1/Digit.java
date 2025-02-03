package Øving1;
public class Digit {

    private int base;
    private int value;

    public Digit(int base) {
        if (base < 2 || base > 36) {
            throw new IllegalArgumentException("Base must be between 2 and 36.");
        }
        this.base = base;
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public boolean increment() {
        value++;
        if (value == base) {
            value = 0;
            return true;
        }
        return false;
    }

    public int getBase() {
        return base;
    }

    @Override
    public String toString() {
        if (value < 10) {
            return String.valueOf(value);
        } else {
            return String.valueOf((char) ('A' + (value - 10)));
        }
    }

    public static void main(String[] args) {
        Digit digit = new Digit(16);
        System.out.println("Base: " + digit.getBase());
        for (int i = 0; i < 20; i++) {
            System.out.println("Value: " + digit + " (overflow: " + digit.increment() + ")");
        }
    }
}