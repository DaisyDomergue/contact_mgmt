package contact_mgmt;

import java.util.Scanner;

import contact_mgmt.Contact;

public class ContactHelper {
        public static String getNewEmail(Scanner scanner, Contact contact) {
        System.out.print("Enter new Email ("+ contact.getEmail()+"): ");
        String newEmail = scanner.nextLine();
        if (newEmail.isEmpty()) {
            newEmail = contact.getEmail();
        }
        while (!newEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,8}$")) {
            System.out.println("Invalid email format. Please enter a valid email:");
            newEmail = scanner.nextLine();
        }
        return newEmail;
    }

    public static String getNewName(Scanner scanner, Contact contact) {
        System.out.print("Enter new Name ("+ contact.getName()+"): ");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) {
            newName = contact.getName();
        }
        return newName;
    }

    public static String getEmail(Scanner scanner) {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        while (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,8}$")) {
            System.out.println("Invalid email format "+ email +". Please enter a valid email:");
            email = scanner.nextLine();
        }
        return email;
    }

    public static long getPhone(Scanner scanner) {
        System.out.print("Enter Phone (10 digits): ");
        String phoneNumber = scanner.nextLine();
        while (!phoneNumber.matches("^[0-9]{10}$")) {
            System.out.println("Invalid phone number format. Please enter a valid phone number:");
            phoneNumber = scanner.nextLine();
        }
        long phone = Long.parseLong(phoneNumber);
        return phone;
    }

    public static String getName(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        return name;
    }   
    public static Long getPhone(Scanner scanner, Contact contact) {
        System.out.print("Enter new Phone (10 digits)("+ contact.getPhoneNumber()+"): ");
        String newPhoneNumber = scanner.nextLine();
        if (newPhoneNumber.isEmpty()) {
            newPhoneNumber = Long.toString(contact.getPhoneNumber());
        }
        while (!newPhoneNumber.matches("^[0-9]{10}$")) {
            System.out.println("Invalid phone number format. Please enter a valid phone number:");
            newPhoneNumber = scanner.nextLine();
        }
        long newPhone = Long.parseLong(newPhoneNumber);
        return newPhone;
    }
}
