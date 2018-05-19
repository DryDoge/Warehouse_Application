package gui.warehouses;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewWarehouseTest {

    @Test
    public void createNewWarehouseSuccess() {
        NewWarehouse nw = new NewWarehouse();
        String city = "Martin";
        String street = "Janka Krala 42";
        String web = "www.webrandom.sk";
        String tel = "789456789";
        String psc = "03601";

        assertTrue(nw.createNewWarehouse(city, street, web, tel, psc));
    }

    @Test
    public void createNewWarehouseFailure() {
        NewWarehouse nw = new NewWarehouse();
        String city = "Martin";
        String street = "Janka Krala 42";
        String web = "www.webrandom.sk";
        String tel = "789456789";
        String psc = "036010";

        assertFalse(nw.createNewWarehouse(city, street, web, tel, psc));
    }

}