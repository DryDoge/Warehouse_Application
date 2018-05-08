package db.e;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class Sklad {
    @Id
    @Column(name = "idsklad", nullable = false)
    private int idsklad;
    private String tel;
    private String ulica;
    private String mesto;
    private String psc;
    private String web;



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
    @JoinTable(
            name="obsahuje",
            joinColumns = @JoinColumn(name="idsklad", referencedColumnName="idsklad" ),
            inverseJoinColumns = @JoinColumn(name="idnap", referencedColumnName="idnap"))
    private List<Napoj> napoje;







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sklad sklad = (Sklad) o;

        if (idsklad != sklad.idsklad) return false;
        if (tel != null ? !tel.equals(sklad.tel) : sklad.tel != null) return false;
        if (ulica != null ? !ulica.equals(sklad.ulica) : sklad.ulica != null) return false;
        if (mesto != null ? !mesto.equals(sklad.mesto) : sklad.mesto != null) return false;
        if (psc != null ? !psc.equals(sklad.psc) : sklad.psc != null) return false;
        if (web != null ? !web.equals(sklad.web) : sklad.web != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsklad;
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (ulica != null ? ulica.hashCode() : 0);
        result = 31 * result + (mesto != null ? mesto.hashCode() : 0);
        result = 31 * result + (psc != null ? psc.hashCode() : 0);
        result = 31 * result + (web != null ? web.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sklad{" +
                "idsklad=" + idsklad +
                ", tel='" + tel + '\'' +
                ", ulica='" + ulica + '\'' +
                ", mesto='" + mesto + '\'' +
                ", psc='" + psc + '\'' +
                ", web='" + web + '\'' +
                '}';
    }

    public List<Napoj> getNapoje() {
        return napoje;
    }

    public void setNapoje(List<Napoj> napoje) {
        this.napoje = napoje;
    }


}