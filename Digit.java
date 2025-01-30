public class Digit {
    private int base;  // Tallsystemet
    private int value; // Sifferverdien

    // Konstruktør som initialiserer tallsystemet og setter verdien til 0
    public Digit(int base) {
        if (base < 2 || base > 36) {
            throw new IllegalArgumentException("Base must be between 2 and 36.");
        }
        this.base = base;
        this.value = 0;
    }

    // Henter sifferverdien
    public int getValue() {
        return value;
    }

    // Øker sifferverdien med én og håndterer "rundtelling"
    public boolean increment() {
        value++;
        if (value == base) {
            value = 0;
            return true; // Indikerer at det skjedde en "overflow"
        }
        return false; // Ingen "overflow"
    }

    // Returnerer tallsystemet
    public int getBase() {
        return base;
    }

    // Returnerer sifferverdien som en streng
    @Override
    public String toString() {
        if (value < 10) {
            return String.valueOf(value); // Tallene 0-9
        } else {
            return String.valueOf((char) ('A' + (value - 10))); // Bokstavene A-Z
        }
    }

    // Main-metode for testing
    public static void main(String[] args) {
        // Oppretter et Digit-objekt med tallsystem 16 (heksadesimalt)
        Digit digit = new Digit(16);

        // Tester inkrementering og skriver ut verdier
        System.out.println("Base: " + digit.getBase()); // Skal skrive 16
        for (int i = 0; i < 20; i++) { // Går forbi en full runde i tallsystemet
            System.out.println("Value: " + digit + " (overflow: " + digit.increment() + ")");
        }
    }
}