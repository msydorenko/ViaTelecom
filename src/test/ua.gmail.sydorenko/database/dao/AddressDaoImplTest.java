package ua.gmail.sydorenko.database.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.gmail.sydorenko.database.dao.exception.DaoSystemException;
import ua.gmail.sydorenko.database.entity.Address;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test address DAO layer.
 *
 * @author M.Sydorenko.
 */
public class AddressDaoImplTest {

    private AddressDao addressDao;
    private Address address1;
    private Address address2;
    private Address address3;
    private List<Address> addressList;

    @Before
    public void init() {
        addressDao = mock(AddressDao.class);
        address1 = new Address();
        address1.setId(1);
        address2 = new Address();
        address2.setId(2);
        address3 = new Address();
        address3.setId(3);
        addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);
        addressList.add(address3);
    }

    @Test
    public void readAll() throws DaoSystemException {
        when(addressDao.readAll()).thenReturn(addressList);

        List<Address> list = addressDao.readAll();

        assertEquals(addressList, list);
    }

  /*  @Test
    public void readById(int i) throws DaoSystemException {
        when(addressDao.readById(address1.getId())).thenReturn(addressList);

        List<Address> addresses = addressDao.readById(1);
        assertEquals(addressList, addresses);
    }*/
}
