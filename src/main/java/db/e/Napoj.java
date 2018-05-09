package db.e;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorColumn(name="typ")
public class Napoj {
    @Id
    private int idnap;
    private String prichut;
    private short cena;
    private String druh;
    private String znacka;
    private String typ;

    @Column(name = "idnap", nullable = false)
    public int getIdnap() {
        return idnap;
    }

    public void setIdnap(int idnap) {
        this.idnap = idnap;
    }

    @Basic
    @Column(name = "prichut", nullable = true, length = 50)
    public String getPrichut() {
        if (prichut.equals(""))
            prichut = "Bez prichute";
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
    @Column(name = "typ", nullable = false, length = 20)
    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dodavatel")
    private Dodavatel dodavatel;

    @Column(name = "dodavatel", nullable = false)
    public Dodavatel getDodavatel() {
        return dodavatel;
    }

    public void setDodavatel(Dodavatel dodavatel) {
        this.dodavatel = dodavatel;
    }

    @ManyToMany(mappedBy = "napoje")
    private List<Sklad> sklady;

    public List<Sklad> getSklady() {
        return sklady;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Napoj napoj = (Napoj) o;
        return idnap == napoj.idnap &&
                cena == napoj.cena &&
                Objects.equals(prichut, napoj.prichut) &&
                Objects.equals(druh, napoj.druh) &&
                Objects.equals(znacka, napoj.znacka) &&
                Objects.equals(typ, napoj.typ);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idnap, prichut, cena, druh, znacka, typ);
    }

    @Override
    public String toString() {
        if (prichut.equals(""))
            prichut = "Bez prichute";
        return " " + idnap + " | " + prichut + " | " + druh + " | "
                + znacka + " | " +  typ + " | " + cena+"€";
    }
}
