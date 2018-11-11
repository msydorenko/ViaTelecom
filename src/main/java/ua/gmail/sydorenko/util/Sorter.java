package ua.gmail.sydorenko.util;

import ua.gmail.sydorenko.database.entity.Tariff;

import java.util.Comparator;

public class Sorter {
    public static final Comparator<Tariff> SORT_BY_NAME_AZ = new Comparator<Tariff>() {
        @Override
        public int compare(Tariff tariff1, Tariff tariff2) {
            return tariff1.getName().compareTo(tariff2.getName());
        }
    };

    public static final Comparator<Tariff> SORT_BY_NAME_ZA = new Comparator<Tariff>() {
        @Override
        public int compare(Tariff tariff1, Tariff tariff2) {
            return tariff2.getName().compareTo(tariff1.getName());
        }
    };

    public static final Comparator<Tariff> SORT_BY_PRICE_MIN_MAX = new Comparator<Tariff>() {
        @Override
        public int compare(Tariff tariff1, Tariff tariff2) {
            return tariff1.getPrice() - tariff2.getPrice();
        }
    };

    public static final Comparator<Tariff> SORT_BY_PRICE_MAX_MIN = new Comparator<Tariff>() {
        @Override
        public int compare(Tariff tariff1, Tariff tariff2) {
            return tariff2.getPrice() - tariff1.getPrice();
        }
    };
}
