package server.dataAccess;


import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import shared.model.Contact;
import shared.util.ISerializer;
import shared.util.Serializer;

import java.io.IOException;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;


public class ContactDao {
    private final String INDEX = "contacts";
    private final String TYPE = "contact";
    private final String DEFAULT_FIELD = "name";

    private TransportClient client;
    private ISerializer serializer;

    public ContactDao(TransportClient client){
        this.client = client;
        this.serializer = new Serializer();
    }


    /**
     * Creates a new contact in the data store with the given
     * Contact information.
     *
     * @param contact the Contact to be added to data store
     * @throws DatabaseException if any errors occur during creation
     */
    public Contact createContact(Contact contact) throws DatabaseException {
        try {

            IndexResponse response = client.prepareIndex(INDEX, TYPE)
                .setSource(serializer.toJson(contact), XContentType.JSON)
                .get();

            GetResponse getResponse = client
                .prepareGet(INDEX, TYPE, response.getId())
                .execute().actionGet();

            String source = getResponse.getSourceAsString();

            Contact persistedContact = (Contact) serializer.toObject(source, Contact.class);
            return persistedContact;

        } catch (IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Gets the list of Contacts from specified parameters
     *
     * @return an array of Contacts
     * @throws DatabaseException if any errors occur during read
     */
    public Contact[] getContacts() throws DatabaseException {
        throw new DatabaseException("Ha! Not implemented yet.");
    }

    /**
     * Gets the contact by its unique name
     *
     * @param name unique username associated with a Contact
     *             already in storage.
     * @return Contact
     * @throws DatabaseException Thrown if there is an error during read
     */
    public Contact getContact(String name) throws DatabaseException {
        try {
            QueryBuilder queryBuilder = queryStringQuery(name).defaultField(DEFAULT_FIELD);

            SearchRequestBuilder searchRequestBuilder = client.prepareSearch()
                    .setIndices(INDEX)
                    .setTypes(TYPE)
                    .setQuery(queryBuilder);

            SearchResponse response = searchRequestBuilder.execute().actionGet();

            if(response.getHits().totalHits < 1){
                throw new DatabaseException(DatabaseException.NOT_FOUND);
            }
            return (Contact) serializer.toObject(response.getHits()
                    .getAt(0).getSourceAsString(), Contact.class); // Return top hit!

        } catch (IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Deletes a contact in data store if that Contact is present, identifying
     * the contact by unique name. If no contact by that name is found in the
     * data store, does nothing.
     *
     * @param name Unique name associated with the contact to be deleted
     * @throws DatabaseException if any errors occur during deletion
     */
    public long deleteContact(String name) throws DatabaseException {
        try {
            BulkByScrollResponse response =
                    DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                    .filter(QueryBuilders.matchQuery(DEFAULT_FIELD, name))
                    .source(INDEX)
                    .get();

            long countDeleted = response.getDeleted();

            // Spec says that this should error if entry is not found.
            if(countDeleted == 0) {
                throw new DatabaseException(DatabaseException.NOT_FOUND);
            }

            return countDeleted;

        } catch (ElasticsearchException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    /**
     * "Why do they always include a self destruct button!?" (Clears DB)
     * @throws DatabaseException in case of unwanted errors/explosions
     */
    public void selfDestruct() throws DatabaseException {
        throw new DatabaseException("Ha! Not implemented yet.");
    }

    /**
     * Updates a Contact already in the data store. If the contact
     * is not in the data store, this function adds it in.
     *
     * @param contact Contact object with updated information
     * @throws DatabaseException if any errors occur during update
     */
    public void updateContact(Contact contact) throws DatabaseException {
        throw new DatabaseException("Ha! Not implemented yet.");

    }
}
