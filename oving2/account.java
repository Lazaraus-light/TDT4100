package oving2;

public class account {
    private double balance;
    private double interestRate;


    public account(double initialBalance, double interestRate) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.balance = initialBalance;
        this.interestRate = interestRate;
    }


    public double getBalance() {
        return balance;
    }


    public double getInterestRate() {
        return interestRate;
    }


    public void setInterestRate(double newInterestRate) {
        if (newInterestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = newInterestRate;
    }


    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount must be non-negative.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount must be non-negative.");
        }
        if (balance - amount < 0) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal.");
        }
        balance -= amount;
    }


    public void addInterest() {
        balance += balance * (interestRate / 100.0);
    }

    @Override
    public String toString() {
        return "account Balance: " + balance + ", Interest Rate: " + interestRate + "%";
    }

    public static void main(String[] args) {

        account account = new account(0, 0);
        System.out.println("Initial State: " + account);


        account.deposit(1000);
        System.out.println("After depositing 1000: " + account);


        account.setInterestRate(5.0);
        System.out.println("After setting interest rate to 5%: " + account);


        account.addInterest();
        System.out.println("After adding interest: " + account);


        account.withdraw(500);
        System.out.println("After withdrawing 500: " + account);


        try {
            account.deposit(-500);
        } catch (IllegalArgumentException e) {
            System.out.println("Attempt to deposit -500 failed: " + e.getMessage());
        }


        try {
            account.withdraw(1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Attempt to withdraw 1000 failed: " + e.getMessage());
        }
    }
}