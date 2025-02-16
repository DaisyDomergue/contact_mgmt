package com.bptn.course.Project;

import java.util.Comparator;

public class ContactNameComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact c1, Contact c2) {
        // Compare names in a case-insensitive manner.
        return c1.getName().toLowerCase().compareTo(c2.getName().toLowerCase());
    }
}