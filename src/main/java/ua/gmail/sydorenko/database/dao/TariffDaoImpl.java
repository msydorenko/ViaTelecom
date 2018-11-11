package ua.gmail.sydorenko.database.dao;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.MySQLManager;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.template.TariffTemplate;
import ua.gmail.sydorenko.database.template.Template;

import java.util.List;

/**
 * @author M.Sydorenko
 */
public class TariffDaoImpl implements TariffDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private static final String SQL_READ_ALL_TARIFFS = "SELECT * FROM viatelecom.tariffs";
    private static final String SQL_READ_TARIFF_BY_SERVISE_ID = "SELECT id, spr_services_id, name, price, " +
            "description FROM viatelecom.tariffs WHERE spr_services_id = ?";
    private static final String SQL_READ_TARIFF_BY_ID = "SELECT id, spr_services_id, name, price, " +
            "description FROM viatelecom.tariffs WHERE id = ?";
    private static final String SQL_CREATE_NEW_TARIFF = "INSERT INTO viatelecom.tariffs " +
            "(spr_services_id, name, price, description) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_TARIFF = "UPDATE viatelecom.tariffs SET name = ?, price = ?, " +
            "description = ? WHERE id = ?";
    private static final String SQL_DELETE_TARIFF = "DELETE FROM viatelecom.tariffs WHERE id = ?";

    private Template template = new TariffTemplate();
    private MySQLManager manager = MySQLManager.getInstance();

    @Override
    public List<Tariff> readAll() throws DaoSystemException {
        List<Tariff> tariffList;
        try {
            tariffList = template.executeAndReturn(manager, SQL_READ_ALL_TARIFFS);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of tariffs", e);
            throw new DaoSystemException("Cannot obtain list of tariffs", e);
        }
        return tariffList;
    }

    @Override
    public List<Tariff> readById(int id) throws DaoSystemException {
        List<Tariff> tariffList;
        try {
            tariffList = template.executeAndReturn(manager, SQL_READ_TARIFF_BY_ID, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of tariffs", e);
            throw new DaoSystemException("Cannot obtain list of tariffs", e);
        }
        return tariffList;
    }

    public List<Tariff> readByIdService(int id) throws DaoSystemException {
        List<Tariff> tariffList;
        try {
            tariffList = template.executeAndReturn(manager, SQL_READ_TARIFF_BY_SERVISE_ID, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of tariffs", e);
            throw new DaoSystemException("Cannot obtain list of tariffs", e);
        }
        return tariffList;
    }

    @Override
    public void create(Tariff tariff) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_CREATE_NEW_TARIFF, tariff.getSpr_service_id(), tariff.getName(),
                    tariff.getPrice(), tariff.getDescription());
        } catch (DaoSystemException e) {
            LOG.error("Cannot create tariff by id", e);
            throw new DaoSystemException("Cannot create tariff by id", e);
        }
    }

    @Override
    public void update(Tariff tariff) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_UPDATE_TARIFF, tariff.getName(), tariff.getPrice(),
                    tariff.getDescription(), tariff.getId());
        } catch (DaoSystemException e) {
            LOG.error("Cannot update tariff by id", e);
            throw new DaoSystemException("Cannot update tariff by id", e);
        }
    }

    @Override
    public void delete(int id) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_DELETE_TARIFF, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot delete tariff by id", e);
            throw new DaoSystemException("Cannot delete tariff by id", e);
        }
    }
}
