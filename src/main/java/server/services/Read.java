package server.services;

import server.dataAccess.DatabaseException;
import server.dataAccess.ElasticsearchFactory;
import server.dataAccess.IDatabaseAPI;
import server.dataAccess.IDatabaseFactory;
import shared.model.Contact;

public class Read {

    private static IDatabaseAPI databaseAPI;
    private static IDatabaseFactory databaseFactory;

    public Read(){
        databaseFactory = new ElasticsearchFactory();
    }

    public Contact readContact(String name) throws DatabaseException {
            databaseAPI = databaseFactory.getDatabase();
            try {

                return (databaseAPI.getContact(name));

            } catch (DatabaseException e) {
                throw new DatabaseException(e.getMessage());
            } finally {
                databaseAPI.close();
            }
    }

    public Contact[] readContact(){
        return new Contact[10];
    }
}
