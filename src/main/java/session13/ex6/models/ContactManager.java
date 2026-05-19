package session13.ex6.models;

import java.util.LinkedHashSet;
import java.util.Set;

public class ContactManager {
    private Set<Contact> contacts = new LinkedHashSet<>();

    public boolean add(Contact contact) {
        return contacts.add(contact);
    }

    public boolean deleteByPhoneNumber(String phoneNumber) {
        return contacts.remove(Contact.ofPhoneNumber(phoneNumber));
    }

    public boolean containsPhoneNumber(String phoneNumber) {
        return contacts.contains(Contact.ofPhoneNumber(phoneNumber));
    }

    public void display() {
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ trống!");
            return;
        }

        System.out.println("Danh sách liên lạc:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
