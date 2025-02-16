package contact_mgmt;

import java.util.*;


public class ContactManagement {
	
	public static List<String> chooseCategories(Scanner scanner) {
		List<String> categories = new ArrayList<>();
		
		// Display predefined category options to the user
		
		System.out.println("Select categories (separate multiple choices with commas):");
        System.out.println("1. Clients");
        System.out.println("2. Vendors");
        System.out.println("3. Family");
        System.out.println("4. Friends");
        System.out.println("5. Favorites");
        System.out.print("Your choice(s): ");
        
        String input = scanner.nextLine();
        String[] tokens = input.split(",");
        for (String token : tokens) {
        	token = token.trim();
        	// Map the user's numeric input to the corresponding category name
        	
        	switch (token) {
            case "1":
                categories.add("Clients");
                break;
            case "2":
                categories.add("Vendors");
                break;
            case "3":
                categories.add("Family");
                break;
            case "4":
                categories.add("Friends");
                break;
            case "5":
                categories.add("Favorites");
                break;
            default:
                System.out.println("Invalid category option: " + token);
        	}
        }
        
        return categories;
	}
	
    public static void main(String[] args) {
    	ContactManager manager = new ContactManager();
    	
        Scanner scanner = new Scanner(System.in);
        
        int choice;
        // Do-while loop to run the menu until the user selects exit (choice 0)
        
        do {
        	System.out.println("\nPlease choose a feature:");
            System.out.println("1. Add Contact");
            System.out.println("2. List Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Search Contacts");
            System.out.println("6. Sort Contacts by Name");
            System.out.println("7. Group Contacts by Category");
            System.out.println("8. Export Contacts to CSV");
            System.out.println("9. Import Contacts from CSV");

            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
        	
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
         // Switch-case to handle the user's menu selection.
            switch (choice) {
                case 1:
                	// Add a new contact.
                	
                	String name = getName(scanner);
                    long phone = getPhone(scanner);
                    String email = getEmail(scanner);

                 // Prompt for category selection.
                    List<String> categories = chooseCategories(scanner);
                    manager.addContact(name, phone, email, categories);
                    break;
                 
                case 2:
                	// List all contacts
                	manager.listContact();
                	break;
                	
                case 3:
                	// Update an existing contact.
                	System.out.print("Enter the name of the contact to update: ");
                    String oldName = scanner.nextLine();
                    Contact contact = manager.getContactByName(oldName);
                    if (contact == null) {
                        System.out.println("Contact not found.");
                        break;
                    }
                    String newName = getNewName(scanner, contact);
                    String newEmail = getNewEmail(scanner, contact);
                    Long newPhone = getPhone(scanner,contact);
                    // Prompt for new category selection.
                    List<String> newCategories = chooseCategories(scanner);
                    manager.updateContact(oldName, newName, newPhone, newEmail, newCategories);
                    break;
                case 4:
                    // Delete a contact.
                    System.out.print("Enter the name of the contact to delete: ");
                    String delName = scanner.nextLine();
                    manager.deleteContact(delName);
                    break;
                case 5:
                    // Search contacts.
                    System.out.print("Enter search criteria: ");
                    String criteria = scanner.nextLine();
                    manager.search(criteria);
                    break;
                case 6:
                    // Sort contacts by name.
                	manager.sortByName();
                    break;
               case 7:
                   // Group contacts by category.
               	manager.groupByCategory();
                   break;
               case 8:
                   // Export contacts to a CSV file.
                   System.out.print("Enter export file path: ");
                   String exportPath = scanner.nextLine();
                   manager.exportCSV(exportPath);
                   break;
               case 9:
                   // Import contacts from a CSV file.
                   System.out.print("Enter import file path: ");
                   String importPath = scanner.nextLine();
                   manager.importCSV(importPath);
                   break;
                case 0:
                    // Exit the application.
                    System.out.println("Exiting...");
                    break;
                default:
                    // Handle an invalid menu option.
                    System.out.println("Invalid choice. Please try again.");
            }    
            
        } while (choice != 0); // Continue looping until the user chooses to exit.

            
    }

    private static String getNewEmail(Scanner scanner, Contact contact) {
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

    private static String getNewName(Scanner scanner, Contact contact) {
        System.out.print("Enter new Name ("+ contact.getName()+"): ");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) {
            newName = contact.getName();
        }
        return newName;
    }

    private static String getEmail(Scanner scanner) {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        while (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,8}$")) {
            System.out.println("Invalid email format "+ email +". Please enter a valid email:");
            email = scanner.nextLine();
        }
        return email;
    }

    private static long getPhone(Scanner scanner) {
        System.out.print("Enter Phone (10 digits): ");
        String phoneNumber = scanner.nextLine();
        while (!phoneNumber.matches("^[0-9]{10}$")) {
            System.out.println("Invalid phone number format. Please enter a valid phone number:");
            phoneNumber = scanner.nextLine();
        }
        long phone = Long.parseLong(phoneNumber);
        return phone;
    }

    private static String getName(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        return name;
    }   
    private static Long getPhone(Scanner scanner, Contact contact) {
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
        
            