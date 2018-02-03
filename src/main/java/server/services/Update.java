package server.services;

import server.dataAccess.DatabaseException;
import server.dataAccess.ElasticsearchFactory;
import server.dataAccess.IDatabaseAPI;
import server.dataAccess.IDatabaseFactory;
import shared.model.Contact;
import shared.model.DataValidationException;

public class Update {
    private static IDatabaseAPI databaseAPI;
    private static IDatabaseFactory databaseFactory;

    public Update(){
        databaseFactory = new ElasticsearchFactory();
    }

    public String updateContact(String name, Contact contact) throws DatabaseException {
        databaseAPI = databaseFactory.getDatabase();
        try {

            contact.setName(name);
            databaseAPI.updateContact(contact);

        } catch (DataValidationException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            databaseAPI.close();
        }
        return "Contact updated.\nCurrent contact info:\n" + contact.toString();
    }
}
