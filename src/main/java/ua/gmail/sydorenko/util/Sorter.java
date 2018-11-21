package ua.gmail.sydorenko.util;

import ua.gmail.sydorenko.database.entity.Tariff;

import java.util.Comparator;

/**
 * Different sorting for tariff entities.
 *
 * @author M.Sydorenko
 */
public class Sorter {
    public static final Comparator<Tariff> SORT_BY_NAME_AZ = Comparator.comparing(Tariff::getName);

    public static final Comparator<Tariff> SORT_BY_NAME_ZA = (tariff1, tariff2) -> tariff2.getName().compareTo(tariff1.getName());

    public static final Comparator<Tariff> SORT_BY_PRICE_MIN_MAX = Comparator.comparingInt(Tariff::getPrice);

    public static final Comparator<Tariff> SORT_BY_PRICE_MAX_MIN = (tariff1, tariff2) -> tariff2.getPrice() - tariff1.getPrice();
}
