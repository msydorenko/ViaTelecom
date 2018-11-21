package ua.gmail.sydorenko.database.dao;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.DBManager;
import ua.gmail.sydorenko.database.entity.Service;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.template.Template;

import java.util.List;

/**
 * Data access object for service related entities.
 *
 * @author M.Sydorenko
 */
public class ServiceDaoImpl implements ServiceDao {
    private static final Logger LOG = Logger.getLogger(ServiceDaoImpl.class.getName());
    private static final String SQL_READ_ALL_SERVICE = "SELECT id, name FROM viatelecom.spr_services";
    private static final String SQL_READ_SERVICE_BY_ID = "SELECT id, name FROM viatelecom.spr_services WHERE id = ?";
    private static final String SQL_CREATE_SERVICE = "INSERT INTO viatelecom.spr_services (name) VALUES (?)";
    private static final String SQL_UPDATE_SERVICE = "UPDATE viatelecom.spr_services SET name = ? WHERE id = ?";
    private static final String SQL_DELETE_SERVICE = "DELETE FROM viatelecom.spr_services WHERE id = ?";

    private DBManager manager;
    private Template template;

    /**
     * Constructor for ServiceDaoImpl class to initialize DBManager and Template fields.
     *
     * @param manager  connection to database
     * @param template object for executing SQL queries.
     */
    public ServiceDaoImpl(DBManager manager, Template template) {
        this.manager = manager;
        this.template = template;
    }

    /**
     * Get all services.
     *
     * @return list of all services.
     * @throws DaoSystemException
     */
    @Override
    public List<Service> readAll() throws DaoSystemException {
        List<Service> listService;
        try {
            listService = template.executeAndReturn(manager, SQL_READ_ALL_SERVICE);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of services! ", e);
            throw new DaoSystemException("Cannot obtain list of services! ", e);
        }
        return listService;
    }

    /**
     * Return list of services by id.
     *
     * @param id of entity object.
     * @return list of services.
     * @throws DaoSystemException
     */
    @Override
    public List<Service> readById(int id) throws DaoSystemException {
        List<Service> listService;
        try {
            listService = template.executeAndReturn(manager, SQL_READ_SERVICE_BY_ID, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain service by id! ", e);
            throw new DaoSystemException("Cannot obtain service by id! ", e);
        }
        return listService;
    }

    /**
     * Add new service entity to data base.
     *
     * @param service service entity.
     * @throws DaoSystemException
     */
    @Override
    public void create(Service service) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_CREATE_SERVICE, service.getName());
        } catch (DaoSystemException e) {
            LOG.error("Cannot create service in table spr_services! ", e);
            throw new DaoSystemException("Cannot create service in table spr_services! ", e);
        }
    }

    /**
     * Update existing service in data base.
     *
     * @param service service entity to update.
     * @throws DaoSystemException
     */
    @Override
    public void update(Service service) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_UPDATE_SERVICE, service.getName());
        } catch (DaoSystemException e) {
            LOG.error("Cannot update service in table spr_services! ", e);
            throw new DaoSystemException("Cannot update service in table spr_services! ", e);
        }
    }

    /**
     * Delete service from data base with id.
     *
     * @param id of entity for deleting.
     * @throws DaoSystemException
     */
    @Override
    public void delete(int id) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_DELETE_SERVICE, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot delete service in table spr_services! ", e);
            throw new DaoSystemException("Cannot delete service in table spr_services! ", e);
        }
    }
}
