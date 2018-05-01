package db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Predaj {
    private int idpredaj;
    private int mnozstvo;

    @Id
    @Column(name = "idpredaj", nullable = false)
    public int getIdpredaj() {
        return idpredaj;
    }

    public void setIdpredaj(int idpredaj) {
        this.idpredaj = idpredaj;
    }

    @Basic
    @Column(name = "mnozstvo", nullable = false)
    public int getMnozstvo() {
        return mnozstvo;
    }

    public void setMnozstvo(int mnozstvo) {
        this.mnozstvo = mnozstvo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predaj predaj = (Predaj) o;

        if (idpredaj != predaj.idpredaj) return false;
        if (mnozstvo != predaj.mnozstvo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpredaj;
        result = 31 * result + mnozstvo;
        return result;
    }
}
