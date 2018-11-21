package ua.gmail.sydorenko.database.dao;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.DBManager;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Address;
import ua.gmail.sydorenko.database.template.Template;

import java.util.List;

/**
 * Data access object for address related entities.
 *
 * @author M.Sydorenko
 */
public class AddressDaoImpl implements AddressDao {
    private static final Logger LOG = Logger.getLogger(AddressDaoImpl.class);
    private static final String SQL_CREATE_ADDRESS = "INSERT INTO viatelecom.spr_addresses (country, city, " +
            "street, house, flat) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_READ_ALL_ADDRESSES = "SELECT id, country, city, street, house, flat " +
            "FROM viatelecom.spr_addresses";
    private static final String SQL_READ_ADDRESS_BY_ID = "SELECT id, country, city, street, house, flat " +
            "FROM viatelecom.spr_addresses WHERE id = ?";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE viatelecom.spr_addresses SET city = ?, street = ?, " +
            "house = ?, flat =? WHERE id = ?";
    private static final String SQL_DELETE_ADDRESS = "DELETE FROM viatelecom.spr_addresses WHERE id = ?";

    DBManager manager;
    Template template;

    /**
     * Constructor for AddressDaoImpl class to initialize DBManager and Template fields.
     *
     * @param manager connection to database
     * @param template object for executing SQL queries.
     */
    public AddressDaoImpl(DBManager manager, Template template) {
        this.manager = manager;
        this.template = template;
    }

    /**
     * Get all addresses.
     *
     * @return list of all addresses.
     * @throws DaoSystemException
     */
    @Override
    public List<Address> readAll() throws DaoSystemException {
        List<Address> listAddresses;
        try {
            listAddresses = template.executeAndReturn(manager, SQL_READ_ALL_ADDRESSES);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of addresses! ", e);
            throw new DaoSystemException("Cannot obtain list of addresses! ", e);
        }
        return listAddresses;
    }

    /**
     * Return list of addresses by id.
     *
     * @param id of entity object.
     * @return list of addresses by id.
     * @throws DaoSystemException
     */
    @Override
    public List<Address> readById(int id) throws DaoSystemException {
        List<Address> listAddresses;
        try {
            listAddresses = template.executeAndReturn(manager, SQL_READ_ADDRESS_BY_ID, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain address by id! ", e);
            throw new DaoSystemException("Cannot obtain address by id! ", e);
        }
        return listAddresses;
    }

    /**
     * Add new address entity to data base.
     *
     * @param address address entity.
     * @throws DaoSystemException
     */
    @Override
    public void create(Address address) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_CREATE_ADDRESS, address.getCountry(), address.getCity(),
                    address.getStreet(), address.getHouse(), address.getFlat());
        } catch (DaoSystemException e) {
            LOG.error("Cannot create address in table spr_addresses! ", e);
            throw new DaoSystemException("Cannot create address in table spr_addresses! ", e);
        }
    }

    /**
     * Update existing address in data base.
     *
     * @param address Address entity to update.
     * @throws DaoSystemException
     */
    @Override
    public void update(Address address) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_UPDATE_ADDRESS, address.getCountry(), address.getCity(),
                    address.getStreet(), address.getHouse(), address.getFlat());
        } catch (DaoSystemException e) {
            LOG.error("Cannot update address in table spr_addresses! ", e);
            throw new DaoSystemException("Cannot update address in table spr_addresses! ", e);
        }
    }

    /**
     * Delete address from data base with id.
     *
     * @param id of entity for deleting.
     * @throws DaoSystemException
     */
    @Override
    public void delete(int id) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_DELETE_ADDRESS, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot delete address in table spr_addresses! ", e);
            throw new DaoSystemException("Cannot delete address in table spr_addresses! ", e);
        }
    }
}
