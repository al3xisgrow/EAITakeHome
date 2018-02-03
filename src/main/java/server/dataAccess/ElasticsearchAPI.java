package server.dataAccess;

import org.elasticsearch.client.transport.TransportClient;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import shared.model.Contact;


import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * This is the database API class. Since this is a very small
 * problem, it may not be 100% necessary, but for a real life
 * problem I would definitely want this level of abstraction here.
 */
public class ElasticsearchAPI implements IDatabaseAPI {
    private ContactDao contactDao;
    private TransportClient elasticsearchClient;

    public static ElasticsearchAPI getInstance() {
        if(_instance == null){
            _instance = new ElasticsearchAPI();
        }
        return _instance;
    }
    private static ElasticsearchAPI _instance;
    private ElasticsearchAPI() {}


    public void init(String host, String port, String port1) throws DatabaseException {
        // Get an Elasticsearch client
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "my-application")
                    .build();

            elasticsearchClient = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), Integer.parseInt(port)))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), Integer.parseInt(port1)));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new DatabaseException("Elasticsearch error: Unknown Host Exception!");
        }

        // Init data access object(s) <- one in this case. (Unless I make more later.)
        contactDao = new ContactDao(elasticsearchClient);
    }


    /**
     * Closes database.
     */
    public void close(){
        elasticsearchClient.close();
    }

    /**
     * Creates a new contact in the data store with the given
     * Contact information.
     *
     * @param contact the Contact to be added to data store
     * @throws DatabaseException if any errors occur during creation
     */
    @Override
    public Contact createContact(Contact contact) throws DatabaseException {
        return contactDao.createContact(contact);
    }

    /**
     * Gets the list of Contacts from specified parameters
     *
     * @return an array of Contacts
     * @throws DatabaseException if any errors occur during read
     */
    @Override
    public Contact[] getContacts() throws DatabaseException {
        return contactDao.getContacts();
    }

    /**
     * Gets the contact by its unique name
     *
     * @param name unique username associated with a Contact
     *             already in storage.
     * @return Contact
     * @throws DatabaseException Thrown if there is an error during read
     */
    @Override
    public Contact getContact(String name) throws DatabaseException {
        return contactDao.getContact(name);
    }

    /**
     * Updates a Contact already in the data store. If the contact
     * is not in the data store, this function adds it in.
     *
     * @param contact Contact object with updated information
     * @throws DatabaseException if any errors occur during update
     */
    @Override
    public void updateContact(Contact contact) throws DatabaseException {
        contactDao.updateContact(contact);
    }

    /**
     * Deletes a contact in data store if that Contact is present, identifying
     * the contact by unique name. If no contact by that name is found in the
     * data store, does nothing.
     *
     * @param name Unique name associated with the contact to be deleted
     * @throws DatabaseException if any errors occur during deletion
     */
    @Override
    public long deleteContact(String name) throws DatabaseException {
        return contactDao.deleteContact(name);
    }

    /**
     * "Why do they always include a self destruct button!?" (Clears DB)
     *
     * @throws DatabaseException in case of unwanted errors/explosions
     */
    @Override
    public void selfDestruct() throws DatabaseException {
        contactDao.selfDestruct();
    }


}
