package main.java.server.services;

import main.java.server.dataAccess.ContactDao;
import main.java.server.dataAccess.Database;
import main.java.server.dataAccess.DatabaseException;
import main.java.server.dataAccess.IContactDao;

public class Delete {
    private IContactDao contactDao;

    public Delete(){
        contactDao = new ContactDao();
    }

    public String deleteContact(String name) throws DatabaseException {
        Database db = new Database();
        try {
            db.openTransaction();

            contactDao.deleteContact(name);

            db.closeTransaction(true);
        } catch (DatabaseException e){
            db.closeTransaction(false);
            throw new DatabaseException(e.getMessage());
        }
        return "Contact " + name + " removed.\n";
    }
}
