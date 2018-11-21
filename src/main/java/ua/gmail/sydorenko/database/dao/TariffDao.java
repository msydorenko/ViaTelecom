package ua.gmail.sydorenko.database.dao;

import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Tariff;

import java.util.List;

/**
 * Data access interface for tariff related entities.
 *
 * @author M.Sydorenko
 */
public interface TariffDao extends EntityDao<Tariff> {
    List<Tariff> readByIdService(int id) throws DaoSystemException;
}
