package ua.gmail.sydorenko.database.entity;

import java.util.Objects;

/**
 * @author M.Sydorenko
 */
public class Contact extends Entity {
    private static final long serialVersionUID = 6843109398583840887L;
    private long phoneNumber;
    private String email;

    public Contact() {
    }

    public Contact(int id) {
        super(id);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return phoneNumber == contact.phoneNumber &&
                Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        int result = 17;
        return 37 * result + ((int) (phoneNumber ^ (phoneNumber >>> 32))) + email.hashCode();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
