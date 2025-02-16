package com.bptn.course.Project;

import java.util.ArrayList;
import java.util.List;

//Represents a single contact with name, email, a 10-digit phone number, and categories.
public class Contact {
    private String name;
    private long phoneNumber;
    private String email;
    private List<String> categories;

    // Creating a Contact Constructor to initialize the fields
    public Contact(String name, long phoneNumber, String email, List<String> categories) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.categories = new ArrayList<>(categories);
    }

    // Getters and Setters
    public String getName() { 
    	return name; 
    	}
    public void setName(String name) { 
    	this.name = name; 
    	}

    public long getPhoneNumber() { 
    	return phoneNumber; 
    	}
    public void setPhoneNumber(long phoneNumber) { 
    	this.phoneNumber = phoneNumber; 
    	}

    public String getEmail() { 
    	return email; 
    	}
    public void setEmail(String email) { 
    	this.email = email; 
    	}

    public List<String> getCategories() {
    	return new ArrayList<>(categories);
    }
    
    public void setCategories(List<String> categories) {
    	this.categories = new ArrayList<>(categories);   // Return a copy to protect the list
    }
    
 // Returns a string representation of the contact.
    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email + ", Category: " + categories;
    }
}