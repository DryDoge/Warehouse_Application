package db.e;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Napoj {
    @Id
    @Column(name = "idnap", nullable = false)
    private int idnap;
    private String prichut;
    private short cena;
    private String druh;
    private String znacka;
    private Date spotreba;



    public int getIdnap() {
        return idnap;
    }

    public void setIdnap(int idnap) {
        this.idnap = idnap;
    }

    @Basic
    @Column(name = "prichut", nullable = true, length = 50)
    public String getPrichut() {
        return prichut;
    }

    public void setPrichut(String prichut) {
        this.prichut = prichut;
    }

    @Basic
    @Column(name = "cena", nullable = false)
    public short getCena() {
        return cena;
    }

    public void setCena(short cena) {
        this.cena = cena;
    }

    @Basic
    @Column(name = "druh", nullable = false, length = 50)
    public String getDruh() {
        return druh;
    }

    public void setDruh(String druh) {
        this.druh = druh;
    }

    @Basic
    @Column(name = "znacka", nullable = false, length = 50)
    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    @Basic
    @Column(name = "spotreba", nullable = false)
    public Date getSpotreba() {
        return spotreba;
    }

    public void setSpotreba(Date spotreba) {
        this.spotreba = spotreba;
    }

    @Override
    public String toString() {
        if (prichut == null){
            prichut = "Bez prichute";
        }
        return
                " "+ idnap + " | " + prichut + " | " +
                        druh + " | " + znacka + " | " + cena + "€ | " + spotreba;
    }

    @ManyToMany(mappedBy="napoje")
    private List<Sklad> sklady;

    public List<Sklad> getSklady() {
        return sklady;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Napoj napoj = (Napoj) o;

        if (idnap != napoj.idnap) return false;
        if (cena != napoj.cena) return false;
        if (prichut != null ? !prichut.equals(napoj.prichut) : napoj.prichut != null) return false;
        if (druh != null ? !druh.equals(napoj.druh) : napoj.druh != null) return false;
        if (znacka != null ? !znacka.equals(napoj.znacka) : napoj.znacka != null) return false;
        if (spotreba != null ? !spotreba.equals(napoj.spotreba) : napoj.spotreba != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idnap;
        result = 31 * result + (prichut != null ? prichut.hashCode() : 0);
        result = 31 * result + (int) cena;
        result = 31 * result + (druh != null ? druh.hashCode() : 0);
        result = 31 * result + (znacka != null ? znacka.hashCode() : 0);
        result = 31 * result + (spotreba != null ? spotreba.hashCode() : 0);
        return result;
    }

}
