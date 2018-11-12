package ua.gmail.sydorenko.database.dao;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.MySQLManager;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Address;
import ua.gmail.sydorenko.database.entity.Bill;
import ua.gmail.sydorenko.database.entity.Contact;
import ua.gmail.sydorenko.database.template.AddressTemplate;
import ua.gmail.sydorenko.database.template.ContactTemplate;
import ua.gmail.sydorenko.database.template.Template;

import java.util.List;

public class ContactDaoImpl implements ContactDao {
    private static final Logger LOG = Logger.getLogger(ContactDaoImpl.class);
    private static final String SQL_CREATE_CONTACT = "INSERT INTO viatelecom.spr_contacts (phone_number, email) VALUES (?, ?)";
    private static final String SQL_READ_ALL_CONTACTS = "SELECT phone_number, email FROM viatelecom.spr_contacts";
    private static final String SQL_READ_CONTACT_BY_ID = "SELECT id, phone_number, email FROM viatelecom.spr_contacts WHERE id = ?";
    private static final String SQL_UPDATE_CONTACT = "UPDATE viatelecom.spr_contacts SET phone_number = ? email = ? WHERE id = ?";
    private static final String SQL_DELETE_CONTACT = "DELETE FROM viatelecom.spr_contacts WHERE id = ?";

    Template template = new ContactTemplate();
    MySQLManager manager = MySQLManager.getInstance();

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

    @Override
    public void create(Contact contact) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_CREATE_CONTACT, contact.getPhoneNumber(), contact.getEmail());
        } catch (DaoSystemException e) {
            LOG.error("Cannot create contact in table spr_contacts! ", e);
            throw new DaoSystemException("Cannot create contact in table spr_contacts! ", e);
        }
    }

    @Override
    public void update(Contact contact) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_UPDATE_CONTACT, contact.getPhoneNumber(), contact.getEmail());
        } catch (DaoSystemException e) {
            LOG.error("Cannot update contact in table spr_contacts! ", e);
            throw new DaoSystemException("Cannot update contact in table spr_contacts! ", e);
        }
    }

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
