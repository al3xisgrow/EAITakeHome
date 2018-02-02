package main.java.server.dataAccess;


import main.java.shared.model.Contact;

/**
 * Created by Alyx on 2/2/18.
 */

public interface IContactDao {
    /**
     * Creates a new contact in the data store with the given
     * Contact information.
     * @param contact the Contact to be added to data store
     * @throws DatabaseException if any errors occur during creation
     */
    void createContact(Contact contact) throws DatabaseException;


    /**
     * Gets the full list of Contacts
     * @return an array of Contacts
     * @throws DatabaseException if any errors occur during read
     */
    Contact[] getAllContacts() throws DatabaseException;


    /**
     * Gets the contact by its unique name
     * @param name unique username associated with a Contact
     *                 already in storage.
     * @return Contact
     * @throws DatabaseException Errors if no contact by that name is found.
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
     * @throws DatabaseException if any errors occur during deletion
     */
    void deleteContact(String name) throws DatabaseException;
}
