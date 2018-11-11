package ua.gmail.sydorenko.database.template;

import ua.gmail.sydorenko.database.entity.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class TariffTemplate extends Template<Tariff> {

    @Override
    public Tariff getResultRow(ResultSet resultSet) throws SQLException {
        Tariff tariff = new Tariff();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int numberColumn = 1; numberColumn <= columnCount; ) {
            tariff.setId(resultSet.getInt(numberColumn++));
            tariff.setSpr_service_id(resultSet.getInt(numberColumn++));
            tariff.setName(resultSet.getString(numberColumn++));
            tariff.setPrice(resultSet.getInt(numberColumn++));
            tariff.setDescription(resultSet.getString(numberColumn++));
        }
        return tariff;
    }
}
