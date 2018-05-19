package gui.warehouses;

import org.junit.Test;

import static org.junit.Assert.*;

public class WarehouseGUITest {

    @Test
    public void areValidDataComplete() {
        String city = "Martin";
        String street = "Janka Krala 42";
        String web = "www.webrandom.sk";
        String tel = "789456789";
        String psc = "03601";

        assertTrue(WarehouseGUI.areValidData(city, street, web, tel, psc));
    }

    @Test
    public void areValidDataNoCity() {
        String city = "";
        String street = "Janka Krala 42";
        String web = "www.webrandom.sk";
        String tel = "789456789";
        String psc = "03601";

        assertFalse(WarehouseGUI.areValidData(city, street, web, tel, psc));
    }
}