package db.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Supplier {
    @Id
    private int id;
    private String name;
    private String email;
    private String tel;
    private String web;

    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Supplier supplier = (Supplier) o;
        return id == supplier.id &&
                Objects.equals(name, supplier.name) &&
                Objects.equals(email, supplier.email) &&
                Objects.equals(tel, supplier.tel) &&
                Objects.equals(web, supplier.web);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email, tel, web);
    }

    @OneToMany(mappedBy="supplier")
    private List<Beverage> beverages;


    public List<Beverage> getBeverages() {
        return beverages;
    }

}
