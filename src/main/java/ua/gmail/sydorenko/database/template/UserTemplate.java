package ua.gmail.sydorenko.database.template;

import ua.gmail.sydorenko.database.entity.Address;
import ua.gmail.sydorenko.database.entity.Bill;
import ua.gmail.sydorenko.database.entity.Contact;
import ua.gmail.sydorenko.database.entity.User;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserTemplate extends Template<User> {

    @Override
    public User getResultRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
            for (int numberColumn = 1; numberColumn <= columnCount; ) {
                user.setId(resultSet.getInt(numberColumn++));
                user.setLogin(resultSet.getString(numberColumn++));
                user.setPassword(resultSet.getString(numberColumn++));
                user.setFirst_name(resultSet.getString(numberColumn++));
                user.setLast_name(resultSet.getString(numberColumn++));
                user.setBlocked(resultSet.getBoolean(numberColumn++));
                user.setBill(new Bill(resultSet.getInt(numberColumn++)));
                user.setAddress(new Address(resultSet.getInt(numberColumn++)));
                user.setContact(new Contact(resultSet.getInt(numberColumn++)));
                user.setRoleId(resultSet.getInt(numberColumn++));
            }
            return user;
    }
}
