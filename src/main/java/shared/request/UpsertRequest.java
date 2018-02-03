package shared.request;


import shared.model.Contact;


public class UpsertRequest {
    private Contact contact;

    public UpsertRequest(){
        this.contact = new Contact();
    }
    public UpsertRequest(Contact contact){
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
