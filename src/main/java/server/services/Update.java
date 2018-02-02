package main.java.server.services;

import main.java.server.dataAccess.ContactDao;
import main.java.server.dataAccess.Database;
import main.java.server.dataAccess.DatabaseException;
import main.java.server.dataAccess.IContactDao;
import main.java.shared.model.Contact;

public class Update {
    private IContactDao contactDao;

    public Update(){
        contactDao = new ContactDao();
    }

    public String updateContact(String name, Contact contact) throws DatabaseException {
        Database db = new Database();
        try {
            db.openTransaction();

            contact.setName(name);
            contactDao.updateContact(contact);

            db.closeTransaction(true);
        } catch (DatabaseException e){
            db.closeTransaction(false);
            throw new DatabaseException(e.getMessage());
        }
        return "Contact updated.\nCurrent contact info:\n" + contact.toString();
    }
}
