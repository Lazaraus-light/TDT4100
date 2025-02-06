package oving2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class persontest {
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
    private String email;
    private LocalDate bday;

    public persontest(String fornavn, String mellomnavn, String etternavn, String email, String bday) {
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
        // Trim the email to remove any leading/trailing whitespace.
        email = email.trim();

        // Create a set to hold valid country codes.
        Set<String> validCodes = new HashSet<>();

        // Read the country codes from the file.
        try (Scanner reader = new Scanner(new FileInputStream("oving2/landskoder.txt"))) { //prøvde  å få chatGPT og github copilot til å få denne funksjonen til å fungere, uten hell.
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                // Split the line by commas.
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    token = token.trim();
                    // Remove surrounding quotation marks if present.
                    if (token.startsWith("\"") && token.endsWith("\"")) {
                        token = token.substring(1, token.length() - 1);
                    }
                    // Ensure the token starts with a dot.
                    if (!token.startsWith(".")) {
                        token = "." + token;
                    }
                    validCodes.add(token);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Country codes file not found: oving2/landskoder.txt", e);
        }

        // (Optional) Debug print to see the loaded valid codes.
        System.out.println("Valid country codes: " + validCodes);
    }
        // Check if the email ends with one of the valid country codes.
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

    public String getEmail() {
        return email;
    }

    public LocalDate getBday() {
        return bday;
    }

    public static void main(String[] args) {
        try {
            persontest p = new persontest("Åge", "Alexander", "Mortensen", "age.a.mortensen@kaermorhen.pl", "12-05-1990");
            System.out.println("Full Name: " + p.getFullName());
            System.out.println("Email: " + p.getEmail());
            System.out.println("Birthday: " + p.getBday());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }}
    }
}
