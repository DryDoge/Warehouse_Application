package db.entity;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Entity
public class Warehouse {
    @Id
    private int id;
    private String tel;
    private String street;
    private String city;
    private String postalcode;
    private String web;
    @ManyToMany
    @JoinTable(name="contain",
            joinColumns = @JoinColumn(name="storageid", referencedColumnName= "id"),
            inverseJoinColumns = @JoinColumn(name="beverageid", referencedColumnName="id"))
    private List<Beverage> beverages;


    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tel", nullable = false, length = 9)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "street", nullable = false, length = 100)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "postalcode", nullable = false, length = 5)
    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    @Basic
    @Column(name = "web", nullable = false, length = 50)
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return id == warehouse.id &&
                Objects.equals(tel, warehouse.tel) &&
                Objects.equals(street, warehouse.street) &&
                Objects.equals(city, warehouse.city) &&
                Objects.equals(postalcode, warehouse.postalcode) &&
                Objects.equals(web, warehouse.web);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, tel, street, city, postalcode, web);
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    public List<Beverage> getBeverages() {
        beverages.sort(Comparator.comparing(Beverage::getId));
        return beverages;}
}
