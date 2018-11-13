package ua.gmail.sydorenko.database.dao;

import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.entity.User;

import java.util.List;

/**
 * @author M.Sydorenko
 */
public interface UserDao extends EntityDao<User> {

    List<User> readByLogin(String login) throws DaoSystemException;

    void createTariffForUser(int userId, int tariffId) throws DaoSystemException;

    List<Tariff> readTariffForUser(User user) throws DaoSystemException;

    void deleteTariffForUser(User user, int tariffId) throws DaoSystemException;

}
