package server.services;

import server.dataAccess.DatabaseException;
import server.dataAccess.ElasticsearchFactory;
import server.dataAccess.IDatabaseAPI;
import server.dataAccess.IDatabaseFactory;


public class Delete {
    private static IDatabaseAPI databaseAPI;
    private static IDatabaseFactory databaseFactory;

    public Delete(){
        databaseFactory = new ElasticsearchFactory();
    }

    public String deleteContact(String name) throws DatabaseException {
        databaseAPI = databaseFactory.getDatabase();
        try {

            databaseAPI.deleteContact(name);

        } catch (DatabaseException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            databaseAPI.close();
        }
        return "Contact " + name + " removed.\n";
    }
}
