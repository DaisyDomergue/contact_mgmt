package contact_mgmt;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Manages contact operations such as adding, updating, deleting, searching, grouping, sorting contacts.

public class ContactManager {
	
	// List to store contacts
    private List<Contact> contacts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
    // Validating phone number is 10 digits long
    private boolean isValidPhone(long phoneNumber) {
    	return phoneNumber >= 1000000000L && phoneNumber <= 9999999999L;
    }
//    public ContactManager() {
//
//    }

    // Add a new contact
    public void addContact(String name, long phoneNumber, String email, List<String> categories) {
    	
    	if (!isValidPhone(phoneNumber)) {
    		System.out.println("Error: Enter a 10 digit phone number");
    		return;
    	}
        contacts.add(new Contact(name, phoneNumber, email, categories));
        System.out.println("Contact added successfully!");
    }
    
    // List all contacts
    public void listContact() {
    	
    	if (contacts.isEmpty()) {
    		System.out.println("No contacts available!");
    	} else {
    		for (Contact c : contacts) {
        		System.out.println(c);
        	}
    	}
    	
    }
    
    //update contact
    public void updateContact(String name, String newName, long newPhoneNumber, String newEmail, List<String> newCategories) {
    	for (Contact c : contacts) {
    		
    		if (c.getName().equalsIgnoreCase(name)) { 
    			if (!isValidPhone(newPhoneNumber)) {
    				System.out.println("Error: Enter a 10 digit phone number");
    				return;
    			}
    			
    			c.setName(newName);
    			c.setPhoneNumber(newPhoneNumber);
    			c.setEmail(newEmail);
    			c.setCategories(newCategories);
    			System.out.println("Contact Update Successful!");
    			return;
    		}
    	}
    	System.out.println("Contact not found.");
    }

	public Contact getContactByName(String name) {
		for (Contact c : contacts) {
			if (c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}
    
    public void deleteContact(String delName) {
    	
    	boolean removed = false;
    	Iterator<Contact> iterator = contacts.iterator();

    	while(iterator.hasNext()) {
    		Contact c = iterator.next();
    		if (c.getName().equalsIgnoreCase(delName)) {
    			iterator.remove();
    			removed = true;
    			
    			System.out.println("Contact deleted successfully!");
    			return;
    		}
    	}
    	System.out.println("contact not found");
    }
    
    // Searches for contacts matching the criteria in name, email, or phone.
    public void search(String criteria) {
        boolean found = false;
        for (Contact c : contacts) {
        	// Check if the criteria is found in the name, email, or phone (converted to string).
            if (c.getName().toLowerCase().contains(criteria.toLowerCase()) ||
            	c.getEmail().toLowerCase().contains(criteria.toLowerCase()) ||
            	String.valueOf(c.getPhoneNumber()).contains(criteria)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) {
        	System.out.println("No matching contacts found.");
        }
    }


	public void sortByName() {
		
		// Create a TreeSet with our custom comparator.
		TreeSet<Contact> sortedContacts = new TreeSet<>(new ContactNameComparator());
		
		sortedContacts.addAll(contacts);
		
		// Clear the original list and add the sorted contacts back into it.
		
		contacts.clear();
		contacts.addAll(sortedContacts);
		System.out.println("Contacts sorted by name.");
	}

	public void groupByCategory() {
		Map<String, List<Contact>> map = new HashMap<>();
		
		// For each contact, add it to the corresponding category group(s)
		
		for (Contact c : contacts) {
			for (String category : c.getCategories()) {
				if (!map.containsKey(category)) {
					map.put(category, new ArrayList<>());
				}
				map.get(category).add(c);
			}
		}
		System.out.println("Contacts grouped by category:");
		for (Map.Entry<String, List<Contact>> entry : map.entrySet()) {
			System.out.println("Category: " + entry.getKey());
			for (Contact c : entry.getValue()) {
				System.out.println(c);
			}
		}
	}
	public void exportCSV(String exportPath) {
		try (PrintWriter writer = new PrintWriter(new File(exportPath))) {
			StringBuilder sb = new StringBuilder();
			sb.append("Name,Phone Number,Email,Categories\n");
			for (Contact c : contacts) {
				sb.append(c.getName()).append(",");
				sb.append(c.getPhoneNumber()).append(",");
				sb.append(c.getEmail()).append(",");
				sb.append(String.join(";", c.getCategories())).append("\n");
			}
			writer.write(sb.toString());
			System.out.println("Contacts exported successfully to " + exportPath);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Unable to export contacts. " + e.getMessage());
		}
	}
	public void importCSV(String importPath) {
		try (Scanner scanner = new Scanner(new File(importPath))) {
			// Skip the header line
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				String name = parts[0];
				long phoneNumber = Long.parseLong(parts[1]);
				String email = parts[2];
				List<String> categories = Arrays.asList(parts[3].split(";"));
				contacts.add(new Contact(name, phoneNumber, email, categories));
			}
			System.out.println("Contacts imported successfully from " + importPath);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Unable to import contacts. " + e.getMessage());
		}
	}
}

