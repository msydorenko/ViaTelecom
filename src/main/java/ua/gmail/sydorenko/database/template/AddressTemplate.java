package ua.gmail.sydorenko.database.template;

import ua.gmail.sydorenko.database.entity.Address;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AddressTemplate extends Template<Address> {

    @Override
    public Address getResultRow(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int numberColumn = 1; numberColumn <= columnCount; ) {
            address.setId(resultSet.getInt(numberColumn++));
            address.setCountry(resultSet.getString(numberColumn++));
            address.setCity(resultSet.getString(numberColumn++));
            address.setStreet(resultSet.getString(numberColumn++));
            address.setHouse(resultSet.getInt(numberColumn++));
            address.setFlat(resultSet.getInt(numberColumn++));
        }
        return address;
    }
}
