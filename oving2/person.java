package oving2;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class person {
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
    private String email;
    private LocalDate bday;

    public person(String fornavn, String mellomnavn, String etternavn, String email, String bday) {
        this.fornavn = fornavn;
        this.mellomnavn = mellomnavn;
        this.etternavn = etternavn;
        setEmail(email);
        setBday(bday);
    }

    public void setEmail(String email) {
        try { 
        Scanner reader = new Scanner(new FileInputStream("landskoder.txt");
        if (email == null || !email.contains("@") || !(email.endsWith(".no") || email.endsWith(".com") || email.endsWith(".org"))) {
            throw new IllegalArgumentException("Email must contain '@' and a valid country code like '.no', '.com', or similar.");
        }
        this.email = email;
    }

    public void setBday(String bday) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate parsedDate = LocalDate.parse(bday, formatter);

            if (parsedDate.equals(LocalDate.now())) {
                throw new IllegalArgumentException("Birthday cannot be today's date.");
            }

            this.bday = parsedDate;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Wrong date format. Use dd-MM-yyyy.");
        }
    }

    public String getFullName() {
        return String.format("%s %s %s", fornavn, (mellomnavn.isEmpty() ? "" : mellomnavn), etternavn).trim();
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBday() {
        return bday;
    }

    public static void main(String[] args) {
        try {
            person p = new person("Åge", "Alexander", "Mortensen", "age.a.mortensen@kaermorhen.pl", "12-05-1990");
            System.out.println("Full Name: " + p.getFullName());
            System.out.println("Email: " + p.getEmail());
            System.out.println("Birthday: " + p.getBday());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}