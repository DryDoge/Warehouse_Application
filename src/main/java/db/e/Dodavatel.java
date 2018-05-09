package db.e;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Dodavatel {
    @Id
    private int iddod;
    private String nazov;
    private String email;
    private String tel;
    private String web;


    @Column(name = "iddod", nullable = false)
    public int getIddod() {
        return iddod;
    }

    public void setIddod(int iddod) {
        this.iddod = iddod;
    }

    @Basic
    @Column(name = "nazov", nullable = false, length = 100)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
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
        Dodavatel dodavatel = (Dodavatel) o;
        return iddod == dodavatel.iddod &&
                Objects.equals(nazov, dodavatel.nazov) &&
                Objects.equals(email, dodavatel.email) &&
                Objects.equals(tel, dodavatel.tel) &&
                Objects.equals(web, dodavatel.web);
    }

    @OneToMany(mappedBy="dodavatel")
    private List<Napoj> napoje;


    public List<Napoj> getNapoje() {
        return napoje;
    }


    @Override
    public int hashCode() {

        return Objects.hash(iddod, nazov, email, tel, web);
    }
}
