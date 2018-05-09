package db.e;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

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
        return idpredaj == predaj.idpredaj &&
                mnozstvo == predaj.mnozstvo;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idpredaj, mnozstvo);
    }
}
