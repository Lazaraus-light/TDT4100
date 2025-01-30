import java.util.ArrayList;
import java.util.List;

public class OOPperson {
    int age;
    String navn;

    public OOPperson(int age, String name) {
        this.age = age;
        this.navn = name;
    }

    

    public OOPperson() {
            this.age = 102;
            navn = "Espen";
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        public int getAge() {
            return age;
        }
    
        public String getNavn() {
            return navn;
        }
    
        public void setNavn(String newName) {
            this.navn = newName;
        }
    
        @Override
        public String toString() {
            return this.navn + " " + this.age;
        }
       
    
        public static void main(String[] args) {
            System.out.println("hei");
            OOPperson person = new OOPperson();

        person.setNavn("Ali");
        person.setAge(3);

        OOPperson person2 = new OOPperson();
        person2.setNavn("Kåre");
        person2.setAge(33);

        System.out.println(person2);
        
        List<OOPperson> liste = new ArrayList<>();
        liste.add(person);
        liste.add(person2);
        System.out.println(liste);
        for (int index = 0; index < liste.size(); index++) {
        System.out.println(liste.get(index));
        
        
        }


    }


}