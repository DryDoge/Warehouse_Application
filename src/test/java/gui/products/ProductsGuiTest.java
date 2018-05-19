package gui.products;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductsGuiTest {

    @Test
    public void areValidDataNoCategory() {
        ProductsGui nocateg = new ProductsGui();
        String category = "";
        int price = 50;
        String brand = "Relax";
        assertFalse(nocateg.areValidData(category, brand, String.valueOf(price)));
    }

    @Test
    public void areValidDataNoBrand(){
        ProductsGui nobrand = new ProductsGui();
        String category = "Vodka";
        int price = 33;
        String brand = "";
        assertFalse(nobrand.areValidData(category, brand, String.valueOf(price)));
    }

    @Test
    public void areValidDataBadPrice(){
        ProductsGui badPrice = new ProductsGui();
        String category = "Dzus";
        int price = -1;
        String brand = "Budis";
        assertFalse(badPrice.areValidData(category, brand, String.valueOf(price)));
    }
    @Test
    public void areValidDataComplete(){
       ProductsGui complete = new ProductsGui();
       String category = "Rum";
       String brand = "Bozkov";
       int price = 20;
       assertTrue(complete.areValidData(category, brand, String.valueOf(price)));
    }
}