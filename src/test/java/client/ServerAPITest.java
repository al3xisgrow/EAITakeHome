package client;


import shared.model.Contact;
import shared.model.DataValidationException;

public class ServerAPITest {
    ServerAPI api;

    @org.junit.After
    public void setUp() {
        api = new ServerAPI();
        api.init("192.168.1.151", "8080");
    }

    @org.junit.Before
    public void tearDown() {

    }

    @org.junit.Test
    public void testCreateContact() throws DataValidationException {
        Contact test = new Contact("name", "number", "address", "note");
        api.createContact("/contact", test);

        api.createContact("/contact",test);

    }

    @org.junit.Test
    public void testGetContacts() {
    }

    @org.junit.Test
    public void testUpdateContact() {
    }

    @org.junit.Test
    public void testDeleteContact() {
    }
}
