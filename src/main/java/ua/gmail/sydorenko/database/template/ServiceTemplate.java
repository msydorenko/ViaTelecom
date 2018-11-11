package ua.gmail.sydorenko.database.template;

import ua.gmail.sydorenko.database.entity.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class ServiceTemplate extends Template<Service> {

    @Override
    public Service getResultRow(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        for (int numberColumn = 1; numberColumn <= columnCount; ) {
            service.setId(resultSet.getInt(numberColumn++));
            service.setName(resultSet.getString(numberColumn++));
        }
        return service;
    }
}
