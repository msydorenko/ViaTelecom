package ua.gmail.sydorenko.database.dao;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.MySQLManager;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Service;
import ua.gmail.sydorenko.database.template.ServiceTemplate;
import ua.gmail.sydorenko.database.template.Template;

import java.util.List;

/**
 * @author M.Sydorenko
 */
public class ServiceDaoImpl implements ServiceDao {
    private static final Logger LOG = Logger.getLogger(ServiceDaoImpl.class.getName());
    private static final String SQL_READ_ALL_SERVICE = "SELECT id, name" +
            " FROM viatelecom.spr_services";

    private Template template = new ServiceTemplate();
    private MySQLManager manager;

    {
        manager = MySQLManager.getInstance();
    }

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

    @Override
    public List<Service> readById(int id) throws DaoSystemException {
        return null;
    }

    @Override
    public void create(Service service) throws DaoSystemException {

    }

    @Override
    public void update(Service service) throws DaoSystemException {

    }

    @Override
    public void delete(int id) throws DaoSystemException {

    }
}
