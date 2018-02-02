package main.java.shared.request;


import main.java.shared.model.Contact;


public class UpsertRequest {
    private Contact contact;

    public UpsertRequest(){
        this.contact = new Contact();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
