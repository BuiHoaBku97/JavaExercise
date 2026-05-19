package session13.ex6.models;

import java.util.Objects;

public class Contact {
    private static int nextId = 1;

    private int id;
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.id = nextId++;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    private Contact(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static Contact ofPhoneNumber(String phoneNumber) {
        return new Contact(phoneNumber);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Contact contact)) {
            return false;
        }
        return Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tên liên lạc: " + name + ", Số điện thoại: " + phoneNumber;
    }
}
