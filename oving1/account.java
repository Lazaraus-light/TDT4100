package oving1;
public class account {

    private double balance;
    private double interestRate;

    public account() {
        this.balance = 0.0;
        this.interestRate = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void addInterest() {
        balance += balance * (interestRate / 100);
    }


    public double getBalance() {
        return balance;
    }


    public double getInterestRate() {
        return interestRate;
    }


    public void setInterestRate(double newInterestRate) {
        this.interestRate = newInterestRate;
    }


    @Override
    public String toString() {
        return "Account Balance: " + balance + ", Interest Rate: " + interestRate + "%";
    }

    public static void main(String[] args) {
        
        account account = new account();

    
        System.out.println("Initial State: " + account);

        
        account.deposit(1000);
        System.out.println("After depositing 1000: " + account);

        
        account.setInterestRate(5.0);
        System.out.println("After setting interest rate to 5%: " + account);

        
        account.addInterest();
        System.out.println("After adding interest: " + account);

        
        account.deposit(-500); //fungerer ikke av en eller annen grunn, må få studass til å gå over.
        System.out.println("After attempting to deposit -500: " + account);
    }
}
