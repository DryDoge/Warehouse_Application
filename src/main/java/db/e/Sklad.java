package db.e;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Sklad {
    @Id
    private int idsklad;
    private String tel;
    private String ulica;
    private String mesto;
    private String psc;
    private String web;

    @Column(name = "idsklad", nullable = false)
    public int getIdsklad() {
        return idsklad;
    }

    public void setIdsklad(int idsklad) {
        this.idsklad = idsklad;
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
    @Column(name = "ulica", nullable = false, length = 100)
    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    @Basic
    @Column(name = "mesto", nullable = false, length = 50)
    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    @Basic
    @Column(name = "psc", nullable = false, length = 5)
    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    @Basic
    @Column(name = "web", nullable = false, length = 50)
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @ManyToMany
    @JoinTable(name="obsahuje",
            joinColumns = @JoinColumn(name="idsklad", referencedColumnName="idsklad" ),
            inverseJoinColumns = @JoinColumn(name="idnap", referencedColumnName="idnap"))
    private List<Napoj> napoje;

    public List<Napoj> getNapoje() {
        return napoje;}

    public void setNapoje(List<Napoj> napoje) {
        this.napoje = napoje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sklad sklad = (Sklad) o;
        return idsklad == sklad.idsklad &&
                Objects.equals(tel, sklad.tel) &&
                Objects.equals(ulica, sklad.ulica) &&
                Objects.equals(mesto, sklad.mesto) &&
                Objects.equals(psc, sklad.psc) &&
                Objects.equals(web, sklad.web);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idsklad, tel, ulica, mesto, psc, web);
    }
}


