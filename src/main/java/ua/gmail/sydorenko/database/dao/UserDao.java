package ua.gmail.sydorenko.database.dao;

import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.entity.User;

import java.util.List;

/**
 * Data access interface for user related entities.
 *
 * @author M.Sydorenko
 */
public interface UserDao extends EntityDao<User> {

    /**
     * Get list of users by login.
     *
     * @param login user login.
     * @return list of users.
     * @throws DaoSystemException
     */
    List<User> readByLogin(String login) throws DaoSystemException;

    /**
     * Create tariff for user.
     *
     * @param userId user id.
     * @param tariffId tariff id.
     * @throws DaoSystemException
     */
    void createTariffForUser(int userId, int tariffId) throws DaoSystemException;

    /**
     * Get all tariffs for current user.
     *
     * @param user user entity.
     * @return list of tarrifs.
     * @throws DaoSystemException
     */
    List<Tariff> readTariffForUser(User user) throws DaoSystemException;

    /**
     * Delete user's tariff by id.
     *
     * @param user user entity.
     * @param tariffId tariff id.
     * @throws DaoSystemException
     */
    void deleteTariffForUser(User user, int tariffId) throws DaoSystemException;

}
