package ua.gmail.sydorenko.database.dao;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.DBManager;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Bill;
import ua.gmail.sydorenko.database.template.Template;

import java.util.List;

/**
 * Data access object for bill related entities.
 *
 * @author M.Sydorenko
 */
public class BillDaoImpl implements BillDao {
    private static final Logger LOG = Logger.getLogger(BillDaoImpl.class);
    private static final String SQL_CREATE_BILL = "INSERT INTO viatelecom.spr_bills (number, value) VALUES (?, ?)";
    private static final String SQL_READ_ALL_BILLS = "SELECT id, number, value FROM viatelecom.spr_bills";
    private static final String SQL_READ_BILL_BY_ID = "SELECT id, number, value FROM viatelecom.spr_bills WHERE id = ?";
    private static final String SQL_UPDATE_BILL = "UPDATE viatelecom.spr_bills SET value = ? WHERE id = ?";
    private static final String SQL_DELETE_BILL = "DELETE FROM viatelecom.spr_bills WHERE id = ?";

    DBManager manager;
    Template template;

    /**
     * Constructor for BillDaoImpl class to initialize DBManager and Template fields.
     *
     * @param manager connection to database
     * @param template object for executing SQL queries.
     */
    public BillDaoImpl(DBManager manager, Template template) {
        this.manager = manager;
        this.template = template;
    }

    /**
     * Get all bills.
     *
     * @return list of all addresses.
     * @throws DaoSystemException
     */
    @Override
    public List<Bill> readAll() throws DaoSystemException {
        List<Bill> listBills;
        try {
            listBills = template.executeAndReturn(manager, SQL_READ_ALL_BILLS);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of bills! ", e);
            throw new DaoSystemException("Cannot obtain list of bills! ", e);
        }
        return listBills;
    }

    /**
     * Return list of bills by id.
     *
     * @param id of entity object.
     * @return list of bills by id.
     * @throws DaoSystemException
     */
    @Override
    public List<Bill> readById(int id) throws DaoSystemException {
        List<Bill> listBills;
        try {
            listBills = template.executeAndReturn(manager, SQL_READ_BILL_BY_ID, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain bill by id! ", e);
            throw new DaoSystemException("Cannot obtain bill by id! ", e);
        }
        return listBills;
    }

    /**
     * Add new bill entity to data base.
     *
     * @param bill bill entity.
     * @throws DaoSystemException
     */
    @Override
    public void create(Bill bill) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_CREATE_BILL, bill.getNumber(), bill.getValue());
        } catch (DaoSystemException e) {
            LOG.error("Cannot create bill in table spr_bills! ", e);
            throw new DaoSystemException("Cannot create bill in table spr_bills! ", e);
        }
    }

    /**
     * Update existing bill in data base.
     *
     * @param bill Bill entity to update.
     * @throws DaoSystemException
     */
    @Override
    public void update(Bill bill) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_UPDATE_BILL, bill.getValue(), bill.getId());
        } catch (DaoSystemException e) {
            LOG.error("Cannot update bill in table spr_bills! ", e);
            throw new DaoSystemException("Cannot update bill in table spr_bills! ", e);
        }
    }

    /**
     * Delete bill from data base with id.
     *
     * @param id of entity for deleting.
     * @throws DaoSystemException
     */
    @Override
    public void delete(int id) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_DELETE_BILL, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot delete bill in table spr_bills! ", e);
            throw new DaoSystemException("Cannot delete bill in table spr_bills! ", e);
        }
    }
}
