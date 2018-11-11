package ua.gmail.sydorenko.database.dao;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.MySQLManager;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.entity.User;
import ua.gmail.sydorenko.database.template.TariffTemplate;
import ua.gmail.sydorenko.database.template.Template;
import ua.gmail.sydorenko.database.template.UserTemplate;

import java.util.List;

/**
 * @author M.Sydorenko
 */
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private static final String SQL_READ_ALL_USERS = "SELECT id, login, password, first_name, last_name, " +
            "active_status, spr_bills_id, spr_addresses_id, spr_contacts_id, spr_roles_id " +
            "FROM viatelecom.users";
    private static final String SQL_READ_USER_BY_LOGIN = "SELECT id, login, password, first_name, last_name," +
            " active_status, spr_bills_id, spr_addresses_id, spr_contacts_id, spr_roles_id" +
            " FROM viatelecom.users WHERE login = ?";
    private static final String SQL_READ_USER_BY_ID = "SELECT id, login, password, first_name, last_name," +
            " active_status, spr_bills_id, spr_addresses_id, spr_contacts_id, spr_roles_id" +
            " FROM viatelecom.users WHERE id = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO viatelecom.users (login, password, first_name, last_name, " +
            "active_status, spr_bills_id, spt_addresses_id, spr_contacts_id, spr_roles_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE ";
    private static final String SQL_UPDATE_USER = "UPDATE viatelecom.users SET login = ?, password = ?, first_name = ?, " +
            "last_name = ?, active_status = ?, spr_bills_id, spr_addresses_id, spr_contacts_id, spr_roles_id " +
            " WHERE id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM viateelecom.users WHERE id = ?";

    private static final String SQL_LINK_CREATE_USER_ORDERS = "INSERT INTO viatelecom.user_orders (users_id, tariffs_id) " +
            "VALUE (?, ?)";
    private static final String SQL_LINK_READ_USER_ORDERS = "SELECT t.id, t.spr_services_id, t.name, t.price, t.description " +
            "FROM viatelecom.tariffs AS t JOIN viatelecom.user_orders AS link ON t.id = link.tariffs_id " +
            "AND link.users_id = ?";
    private static final String SQL_LINK_DELETE_USER_ORDERS = "DELETE FROM viatelecom.user_orders WHERE users_id = ?";

    private static final String SQL_GET_NEXT_AUTOINCREMENT = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'viatelecom' AND TABLE_NAME = 'users'";

    private Template template = new UserTemplate();
    private MySQLManager manager = MySQLManager.getInstance();

    @Override
    public List<User> readByLogin(String login) throws DaoSystemException {
        List<User> listUsers;
        try {
            listUsers = template.executeAndReturn(manager, SQL_READ_USER_BY_LOGIN, login);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain user by specified login! ", e);
            throw new DaoSystemException("Cannot obtain user by specified login! ", e);
        }
        return listUsers;
    }

    @Override
    public void createTariffForUser(int userId, int tariffId) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_LINK_CREATE_USER_ORDERS, userId, tariffId);
        } catch (DaoSystemException e) {
            LOG.error("Cannot create link in table user_orders! ", e);
            throw new DaoSystemException("Cannot create link in table user_orders! ", e);
        }
    }

    @Override
    public List<Tariff> readTariffForUser(User user) throws DaoSystemException {
        List<Tariff> tariffList;
        try {
            tariffList = new TariffTemplate().executeAndReturn(manager, SQL_LINK_READ_USER_ORDERS, user.getId());
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of tariffs for user! ", e);
            throw new DaoSystemException("Cannot obtain list of tariffs for user! ", e);
        }
        return tariffList;
    }

    @Override
    public void deleteTariffForUser(User user) throws DaoSystemException {
        try {
            new TariffTemplate().executeQuery(manager, SQL_LINK_DELETE_USER_ORDERS, user.getId());
        } catch (DaoSystemException e) {
            LOG.error("Cannot delete link in table user_orders! ", e);
            throw new DaoSystemException("Cannot delete list in table user_orders! ", e);
        }
    }

    @Override
    public List<User> readAll() throws DaoSystemException {
        List<User> listUsers;
        try {
            listUsers = template.executeAndReturn(manager, SQL_READ_ALL_USERS);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain list of users! ", e);
            throw new DaoSystemException("Cannot obtain list of users! ", e);
        }
        return listUsers;
    }

    @Override
    public List<User> readById(int id) throws DaoSystemException {
        List<User> listUsers;
        try {
            listUsers = template.executeAndReturn(manager, SQL_READ_USER_BY_ID, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot obtain user by id! ", e);
            throw new DaoSystemException("Cannot obtain user by id! ", e);
        }
        return listUsers;
    }

    @Override
    public void create(User user) throws DaoSystemException {
        int id = template.readNextAutoIncrement(manager, SQL_GET_NEXT_AUTOINCREMENT);
        try {
            template.executeQuery(manager, SQL_CREATE_USER, user.getLogin(), user.getPassword(), user.getFirst_name(),
                    user.getLast_name(), user.isActive_status(), id, id, id, user.getRoleId());
        } catch (DaoSystemException e) {
            LOG.error("Cannot create user in table users! ", e);
            throw new DaoSystemException("Cannot create user in table users! ", e);
        }
    }

    @Override
    public void update(User user) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_UPDATE_USER, user.getLogin(), user.getPassword(), user.getFirst_name(),
                    user.getLast_name(), user.isActive_status(), user.getBill(), user.getAddress(), user.getContact(),
                    user.getRoleId());
        } catch (DaoSystemException e) {
            LOG.error("Cannot update user in table users! ", e);
            throw new DaoSystemException("Cannot update user in table users! ", e);
        }
    }

    @Override
    public void delete(int id) throws DaoSystemException {
        try {
            template.executeQuery(manager, SQL_DELETE_USER, id);
        } catch (DaoSystemException e) {
            LOG.error("Cannot delete user in table users! ", e);
            throw new DaoSystemException("Cannot delete user in table users! ", e);
        }
    }
}
