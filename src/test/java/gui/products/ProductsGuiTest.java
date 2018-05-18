package gui.products;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductsGuiTest {

    @Test
    public void areValidData() {
        ProductsGui nocateg = new ProductsGui();
        String category = "";
        int price = 50;
        String brand = "Relax";
        assertFalse(nocateg.areValidData(category, brand, String.valueOf(price)));

        ProductsGui nobrand = new ProductsGui();
        String category1 = "Vodka";
        int price1 = 33;
        String brand1 = "";
        assertFalse(nocateg.areValidData(category1, brand1, String.valueOf(price1)));
    }
}