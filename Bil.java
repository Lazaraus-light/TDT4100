
import java.util.ArrayList;

public class Bil {
    String model;
    int seats;
    Person driver;
    ArrayList<Person> passengers = new ArrayList<>();

    public void setDriver(Person driver) {
        if (this.driver !=null)
        this.driver = driver;
            System.out.println(this.driver+"sitter her allerede");
        else {
            if (driver.getAge() < 18 {
                System.out.println(driver.getName()+"er for ung");
            }
            }

    public Person getDriver() {
        return driver;
    }

    public void addPassenger(Person p) {
        passengers.add();
    }

    public static void main(String[] args) {
        Bil c = new Bil();
        c.setDriver(new Person());
        c.removeDriver
        System.out.println(c.getDriver());

    }
    private void removeDriver(){

    }

 
    
}
