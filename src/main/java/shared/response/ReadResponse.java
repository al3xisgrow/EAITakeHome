package shared.response;

import shared.model.Contact;

import java.util.HashSet;
import java.util.Set;

public class ReadResponse {
    private String message;
    private Set<Contact> contacts;

    public ReadResponse() {
        this.message = "";
        this.contacts = new HashSet<Contact>();
    }

    public void addContact(Contact c){
        contacts.add(c);
    }

    public Set<Contact>getContacts() {
        return contacts;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
