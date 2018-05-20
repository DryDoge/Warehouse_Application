package db.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Beverage {
    @Id
    private int id;
    private String flavor;
    private short price;
    private String category;
    private String brand;
    private String type;


    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "flavor", nullable = true, length = 50)
    public String getFlavor() {
        if (flavor.equals(""))
            flavor = "Bez prichute";
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public short getPrice() {
        return price;
    }

    public void setPrice(short price) {
        this.price = price;
    }

    @Basic
    @Column(name = "category", nullable = false, length = 50)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "brand", nullable = false, length = 50)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beverage beverage = (Beverage) o;
        return id == beverage.id &&
                price == beverage.price &&
                Objects.equals(flavor, beverage.flavor) &&
                Objects.equals(category, beverage.category) &&
                Objects.equals(brand, beverage.brand) &&
                Objects.equals(type, beverage.type) &&
                Objects.equals(supplier, beverage.supplier);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, flavor, price, category, brand, type, supplier);
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="supplier")
    private Supplier supplier;

    @Column(name = "supplier", nullable = false)
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @ManyToMany(mappedBy = "beverages")
    private List<Warehouse> warehouses;

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    @Override
    public String toString() {
        return " " + id + " | " + flavor + " | " + category + " | " + brand + " | " + type + " | " + price +"€";
    }
}
