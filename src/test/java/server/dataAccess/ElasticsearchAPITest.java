package server.dataAccess;

import shared.model.Contact;
import shared.model.DataValidationException;

import static org.junit.Assert.*;


public class ElasticsearchAPITest {
    IDatabaseFactory factory;
    IDatabaseAPI databaseAPI;

    @org.junit.Before
    public void setUp() throws DatabaseException {
        factory = new ElasticsearchFactory();
        databaseAPI = factory.getDatabase();
        databaseAPI.init("127.0.0.1", "9300", "9301");

    }

    @org.junit.After
    public void tearDown() {
        databaseAPI.close();
    }

    @org.junit.Test
    public void testCreateContact() throws DatabaseException, DataValidationException {
        /*******************
         *     TEST 1
         ******************/
        // Make regular test contact
        Contact batman = new Contact("Batman", "8019952656",
                "Wayne Manor, Gotham City", "Where's Rachel");

        Contact persistedContact = databaseAPI.createContact(batman);
        assertTrue(persistedContact.equals(batman));


        /*******************
         *     TEST 2
         ******************/
        // Make empty test contact
        Contact originalNobody = new Contact();
        Contact persistedNobody = databaseAPI.createContact(originalNobody);
        assertTrue(persistedNobody.equals(originalNobody));
    }

    @org.junit.Test
    public void testGetAllContacts() {

    }

    @org.junit.Test
    public void testGetContact() throws DatabaseException, DataValidationException {
        /*******************
         *     TEST 1
         ******************/
        // Create contact
        Contact malfoy = new Contact("Malfoy", "8019952656",
                "Skulking around somewhere", "Dyes hair blond");

        Contact persistedContact = databaseAPI.createContact(malfoy);
        assertTrue(persistedContact.equals(malfoy));

        // Read contact back.
        Contact databaseResponse = databaseAPI.getContact(malfoy.getName());
        assertTrue(databaseResponse.equals(malfoy));


        /*******************
         *     TEST 2
         ******************/
        // Read contact that is not in the database; should throw error & not assign anything to Contact
        Contact invalidResponse = null;
        try {
            invalidResponse = databaseAPI.getContact("Aunt Sheryl");
        } catch (DatabaseException e) {
            assertTrue(e.getMessage().equals(DatabaseException.NOT_FOUND)); // Success!
        }
        assertTrue(invalidResponse == null);
    }

    @org.junit.Test
    public void testUpdateContact() {
    }

    @org.junit.Test
    public void testDeleteContact() throws DatabaseException, DataValidationException {
        /*******************
         *     TEST 1
         ******************/
        // Create contact
        Contact nessie = new Contact("Nessie", "8019952656",
                "Loch Ness", "Monster");

        Contact persistedContact = databaseAPI.createContact(nessie);
        assertTrue(persistedContact.equals(nessie));

        // Read contact back (i.e. check to be VERY sure it's there)
        Contact databaseResponse = databaseAPI.getContact(nessie.getName());
        assertTrue(databaseResponse.equals(nessie));

        // Delete contact
        databaseAPI.deleteContact(nessie.getName());

        // Check to make sure it's gone
        Contact invalidResponse = null;
        try {
            invalidResponse = databaseAPI.getContact(nessie.getName());
        } catch (DatabaseException e) {
            assertTrue(e.getMessage().equals(DatabaseException.NOT_FOUND)); // Success!
        }
        assertTrue(invalidResponse == null);

        /*******************
         *     TEST 2
         ******************/
        // Delete something that doesn't exist
        long countDeleted = -1;
        try {
            countDeleted = databaseAPI.deleteContact("Bigfoot");
        } catch (DatabaseException e){
            assertTrue(e.getMessage().equals(DatabaseException.NOT_FOUND));
        }
        assertTrue(countDeleted == -1);
    }

    @org.junit.Test
    public void testSelfDestruct() {
    }
}
