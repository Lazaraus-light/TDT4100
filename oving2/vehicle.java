package oving2;

public class vehicle {
    private String fuel;
    private String vehicleType;
    private String registrationNumber;


    public vehicle(char vehicleType, char fuelType, String registrationNumber) {
        this.vehicleType = validatevehicleType(vehicleType);
        this.fuel = validateFuelType(fuelType);
        setRegistrationNumber(registrationNumber);
    }


    private String validatevehicleType(char type) {
        if (type == 'M') {
            return "Motorcycle";
        } else if (type == 'C') {
            return "Car";
        } else {
            throw new IllegalArgumentException("Invalid vehicle type. Use 'M' for Motorcycle or 'C' for Car.");
        }
    }


    private String validateFuelType(char fuelType) {
        if (fuelType == 'H') {
            return "Hydrogen";
        } else if (fuelType == 'E') {
            return "Electricity";
        } else if (fuelType == 'D') {
            return "Diesel";
        } else if (fuelType == 'G') {
            return "Gasoline";
        } else {
            throw new IllegalArgumentException("Invalid fuel type. Use 'H' for Hydrogen, 'E' for Electricity, 'D' for Diesel, or 'G' for Gasoline.");
        }
    }


    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.length() < 4) {
            throw new IllegalArgumentException("Registration number is too short.");
        }

        if (vehicleType.equals("Motorcycle") && registrationNumber.length() != 6) {
            throw new IllegalArgumentException("Motorcycles must have a 4-digit registration number after the prefix.");
        }
        if (vehicleType.equals("Car") && registrationNumber.length() != 7) {
            throw new IllegalArgumentException("Cars must have a 5-digit registration number after the prefix.");
        }

        if (fuel.equals("Electricity") && !(registrationNumber.startsWith("EL") || registrationNumber.startsWith("EK"))) {
            throw new IllegalArgumentException("Electric vehicles must have a registration number starting with 'EL' or 'EK'.");
        }

        if (fuel.equals("Hydrogen") && !registrationNumber.startsWith("HY")) {
            throw new IllegalArgumentException("Hydrogen vehicles must have a registration number starting with 'HY'.");
        }

        if ((fuel.equals("Diesel") || fuel.equals("Gasoline")) && (registrationNumber.startsWith("EL") || registrationNumber.startsWith("EK") || registrationNumber.startsWith("HY"))) {
            throw new IllegalArgumentException("Diesel and gasoline vehicles cannot start with 'EL', 'EK', or 'HY'.");
        }

        if (registrationNumber.matches(".*[æøåÆØÅ].*")) {
            throw new IllegalArgumentException("Registration numbers cannot contain Æ, Ø, or Å.");
        }

        this.registrationNumber = registrationNumber.toUpperCase();
    }


    public String getFuelType() {
        if (fuel.equals("Hydrogen")) {
            return "H";
        } else if (fuel.equals("Electricity")) {
            return "E";
        } else if (fuel.equals("Diesel")) {
            return "D";
        } else if (fuel.equals("Gasoline")) {
            return "G";
        }
        return null;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }


    public String getvehicleType() {
        return vehicleType.equals("Motorcycle") ? "M" : "C";
    }


    @Override
    public String toString() {
        return "vehicle Type: " + getvehicleType() + ", Fuel Type: " + getFuelType() + ", Registration Number: " + registrationNumber;
    }

    public static void main(String[] args) {
        try {
            vehicle vehicle1 = new vehicle('C', 'E', "EL22222");
            System.out.println(vehicle1);

            vehicle vehicle2 = new vehicle('M', 'G', "VP5230");
            System.out.println(vehicle2);


            vehicle vehicle3 = new vehicle('C', 'H', "HY13249");
            System.out.println(vehicle3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}