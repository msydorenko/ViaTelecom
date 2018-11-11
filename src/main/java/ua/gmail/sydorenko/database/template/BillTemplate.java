package ua.gmail.sydorenko.database.template;

import ua.gmail.sydorenko.database.entity.Bill;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BillTemplate extends Template<Bill> {

    @Override
    public Bill getResultRow(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int numberColumn = 1; numberColumn <= columnCount; ) {
                bill.setId(resultSet.getInt(numberColumn++));
                bill.setNumber(resultSet.getString(numberColumn++));
                bill.setValue(resultSet.getInt(numberColumn++));
        }
        return bill;
    }
}
