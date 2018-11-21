package ua.gmail.sydorenko.database.template;

import ua.gmail.sydorenko.database.entity.Contact;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author M.Sydorenko
 */
public class ContactTemplate extends Template<Contact> {
    @Override
    public Contact getResultRow(ResultSet resultSet) throws SQLException {
        Contact contact = new Contact();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int numberColumn = 1; numberColumn <= columnCount; ) {
            contact.setId(resultSet.getInt(numberColumn++));
            contact.setPhoneNumber(resultSet.getLong(numberColumn++));
            contact.setEmail(resultSet.getString(numberColumn++));
        }
        return contact;
    }
}
