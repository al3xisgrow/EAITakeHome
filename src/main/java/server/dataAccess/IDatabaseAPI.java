package server.dataAccess;


import shared.model.Contact;



public interface IDatabaseAPI {
    /**
     * Creates a new contact in the data store with the given
     * Contact information.
     * @param contact the Contact to be added to data store
     * @throws DatabaseException if any errors occur during creation
     * @return The persisted version of the contact pulled from the
     * database response. This return val is currently used for testing.
     */
    Contact createContact(Contact contact) throws DatabaseException;


    /**
     * Gets the list of Contacts from specified parameters
     * @return an array of Contacts
     * @throws DatabaseException if any errors occur during read
     */
    Contact[] getContacts() throws DatabaseException;


    /**
     * Gets the contact by its unique name
     * @param name unique username associated with a Contact
     *                 already in storage.
     * @return Contact
     * @throws DatabaseException Thrown if there is an error during read
     */
    Contact getContact(String name) throws DatabaseException;

    /**
     * Updates a Contact already in the data store. If the contact
     * is not in the data store, this function adds it in.
     * @param contact Contact object with updated information
     * @throws DatabaseException if any errors occur during update
     */
    void updateContact(Contact contact) throws DatabaseException;

    /**
     * Deletes a contact in data store if that Contact is present, identifying
     * the contact by unique name. If no contact by that name is found in the
     * data store, does nothing.
     * @param name Unique name associated with the contact to be deleted
     * @return s number of contacts deleted (should always be 1, since it's
     * deleting by name, and names are unique).
     * @throws DatabaseException if any errors occur during deletion
     */
    long deleteContact(String name) throws DatabaseException;


    /**
     * "Why do they always include a self destruct button!?" (Clears DB)
     * @throws DatabaseException in case of unwanted errors/explosions
     */
    void selfDestruct() throws DatabaseException;


    /**
     * Closes database (called on shutdown)
     */
    void close();

    /**
     * Initializes the database host and port
     */
    void init(String host, String port, String port1) throws DatabaseException;

}
