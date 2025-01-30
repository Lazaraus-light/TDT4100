
public class Person {

    public int getAge(){
        return age;
    }
    
    public int getName(){
        return name;

    }

    @Override
    public String toString() {
        return name + "("+age+")";

    }
    
}
