package oving5.Named;

public class Person2 implements Named {
    private String fullName;
    
    public Person2(String fullName) {
        this.fullName = fullName;
    }
    
    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    @Override
    public String getFullName() {
        return fullName;
    }
    
    @Override
    public void setGivenName(String givenName) {
        // Bygger nytt fullName ved å beholde eksisterende etternavn
        String familyName = getFamilyName();
        this.fullName = givenName + " " + familyName;
    }
    
    @Override
    public String getGivenName() {
        String[] parts = fullName.split(" ", 2);
        return parts[0];
    }
    
    @Override
    public void setFamilyName(String familyName) {
        // Bygger nytt fullName ved å beholde eksisterende fornavn
        String givenName = getGivenName();
        this.fullName = givenName + " " + familyName;
    }
    
    @Override
    public String getFamilyName() {
        String[] parts = fullName.split(" ", 2);
        return parts.length == 2 ? parts[1] : "";
    }
}
