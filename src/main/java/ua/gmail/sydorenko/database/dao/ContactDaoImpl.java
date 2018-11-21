package ua.gmail.sydorenko.database.dao;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.DBManager;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Contact;
import ua.gmail.sydorenko.database.template.Template;

import java.util.List;

/**
 * Data access object for contact related entities.
 *
 * @author M.Sydorenko
 */
public class ContactDaoImpl implements ContactDao {
    private static final Logger LOG = Logger.getLogger(ContactDaoImpl.class);
    private static final String SQL_CREATE_CONTACT = "INSERT INTO viatelecom.spr_contacts (phone_number, email) VALUES (?, ?)";
    private static final String SQL_READ_ALL_CONTACTS = "SELECT phone_number, email FROM viatelecom.spr_contacts";
    private static final String SQL_READ_CONTACT_BY_ID = "SELECT id, phone_number, email FROM viatelecom.spr_contacts WHERE id = ?";
    private static final String SQL_UPDATE_CONTACT = "UPDATE viatelecom.spr_contacts SET phone_number = ?, email = ? WHERE id = ?";
    private static final String SQL_DELETE_CONTACT = "DELETE FROM viatelecom.spr_contacts WHERE id = ?";

    DBManager manager;
    Template template;

    /**
     * Constructor for ContactDaoImpl class to initialize DBManager and Template fields.
     *
     * @param manager connection to database
     * @param template object for executing SQL queries.
     */
    public ContactDaoImpl(DBManager manager, Template template) {
        this.manager = manager;
        this.template = template;
    }

    /**
     * Get all contacts.
     *
     * @return list of all contacts.
     * @throws DaoSystemException
     */
    @Override
    public List<Contact> readAll() throws DaoSystemException {
        List<Contact> listContacts;
        try {
            listContacts = template.executeAndReturn(manager, SQL_READ_ALL_CONTACTS);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of contacts! ", e);
            throw new DaoSystemException("Cannot obtain list of contacts! ", e);
        }
        return listContacts;
    }

    /**
     * Return list of contacts by id.
     *
     * @param id of entity object.
     * @return list of contacts by id.
     * @throws DaoSystemException
     */
    @Override
    public List<Contact> readById(int id) throws DaoSystemException {
        List<Contact> listContacts;
        try {
            listContacts = template.executeAndReturn(manager, SQL_READ_CONTACT_BY_ID, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain contact by id! ", e);
            throw new DaoSystemException("Cannot obtain contact by id! ", e);
        }
        return listContacts;
    }

    /**
     * Add new contact entity to data base.
     *
     * @param contact contact entity.
     * @throws DaoSystemException
     */
    @Override
    public void create(Contact contact) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_CREATE_CONTACT, contact.getPhoneNumber(), contact.getEmail());
        } catch (DaoSystemException e) {
            LOG.error("Cannot create contact in table spr_contacts! ", e);
            throw new DaoSystemException("Cannot create contact in table spr_contacts! ", e);
        }
    }

    /**
     * Update existing contact in data base.
     *
     * @param contact Contact entity to update.
     * @throws DaoSystemException
     */
    @Override
    public void update(Contact contact) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_UPDATE_CONTACT, contact.getPhoneNumber(), contact.getEmail(), contact.getId());
        } catch (DaoSystemException e) {
            LOG.error("Cannot update contact in table spr_contacts! ", e);
            throw new DaoSystemException("Cannot update contact in table spr_contacts! ", e);
        }
    }

    /**
     * Delete contact from data base with id.
     *
     * @param id of entity for deleting.
     * @throws DaoSystemException
     */
    @Override
    public void delete(int id) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_DELETE_CONTACT, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot delete contact in table spr_contacts! ", e);
            throw new DaoSystemException("Cannot delete contact in table spr_contacts! ", e);
        }
    }
}
