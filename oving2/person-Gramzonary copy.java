//ignorer denne, mye prøving og feiling som ikke endte med noe nyttig.
/*
package oving2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class person {
    private static final Set<String> validCodes = loadValidCodes();

    private static Set<String> loadValidCodes() {
        Set<String> codes = new HashSet<>();
        try (Scanner reader = new Scanner(new FileInputStream("oving2/landskoder.txt"))) {
            while (reader.hasNextLine()) {
                String code = reader.nextLine().trim();
                if (!code.isEmpty()) {
                    if (!code.startsWith(".")) {
                        code = "." + code;
                        }
                    }
                    codes.add(code);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Country codes file not found: landskoder.txt", e);
        }
        return codes;
    }
    private String mellomnavn;
    private String etternavn;
    private String email;
    private LocalDate bday;
    private LocalDate bday;

    public person(String fornavn, String mellomnavn, String etternavn, String email, String bday) {
        this.fornavn = fornavn;
        this.mellomnavn = mellomnavn;
        this.etternavn = etternavn;
        setEmail(email);
        setBday(bday);
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@'.");
        }
        
        
        boolean validSuffix = false;
        for (String code : validCodes) {
            if (email.endsWith(code)) {
                validSuffix = true;
                break;
            }
        }
        if (!validSuffix) {
            throw new IllegalArgumentException("Email must have a valid country code. Valid codes are: " + validCodes);
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

        return String.format("%s %s %s", fornavn, (mellomnavn == null || mellomnavn.isEmpty() ? "" : mellomnavn), etternavn).trim();
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
    */