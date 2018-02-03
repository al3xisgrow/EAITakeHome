package server.services;

import org.elasticsearch.ElasticsearchException;
import server.dataAccess.DatabaseException;
import server.dataAccess.ElasticsearchFactory;
import server.dataAccess.IDatabaseAPI;
import server.dataAccess.IDatabaseFactory;
import shared.model.Contact;

public class Create {
    private static IDatabaseAPI databaseAPI;
    private static IDatabaseFactory databaseFactory;

    public Create(){
        databaseFactory = new ElasticsearchFactory();
    }

    public String createContact(Contact contact) throws DatabaseException {
        databaseAPI = databaseFactory.getDatabase();

        try {
            // Check for duplicate; names must be unique
            Contact empty = databaseAPI.getContact(contact.getName());
            throw new DatabaseException(DatabaseException.DUPLICATE);

        } catch (DatabaseException e) {
            try {
                if (e.getMessage().equals(DatabaseException.NOT_FOUND)) {

                    databaseAPI.createContact(contact);
                    return "New contact created.\nContact info:\n" + contact.toString();

                } else {
                    throw e;
                }
            } catch (ElasticsearchException exception){
                System.out.println(exception.getMessage());
                throw new DatabaseException(exception.getMessage());
            }

        } finally {
            databaseAPI.close();
        }
    }
}
