package db.e;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dodavatel {
    private int iddod;
    private String nazov;
    private String email;
    private String tel;
    private String web;

    @Id
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

        if (iddod != dodavatel.iddod) return false;
        if (nazov != null ? !nazov.equals(dodavatel.nazov) : dodavatel.nazov != null) return false;
        if (email != null ? !email.equals(dodavatel.email) : dodavatel.email != null) return false;
        if (tel != null ? !tel.equals(dodavatel.tel) : dodavatel.tel != null) return false;
        if (web != null ? !web.equals(dodavatel.web) : dodavatel.web != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddod;
        result = 31 * result + (nazov != null ? nazov.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (web != null ? web.hashCode() : 0);
        return result;
    }
}
