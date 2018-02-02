package main.java.server.services;

import main.java.server.dataAccess.ContactDao;
import main.java.server.dataAccess.Database;
import main.java.server.dataAccess.DatabaseException;
import main.java.server.dataAccess.IContactDao;
import main.java.shared.model.Contact;

public class Create {
    private IContactDao contactDao;

    public Create(){
        contactDao = new ContactDao();
    }

    public String createContact(String name, Contact contact) throws DatabaseException {
        Database db = new Database();
        try {
            db.openTransaction();

            contact.setName(name);
            contactDao.createContact(contact);

            db.closeTransaction(true);
        } catch (DatabaseException e){
            db.closeTransaction(false);
            throw new DatabaseException(e.getMessage());
        }
        return "New contact created.\nContact info:\n" + contact.toString();
    }
}
