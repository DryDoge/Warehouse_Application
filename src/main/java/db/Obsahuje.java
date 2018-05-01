package db;

import javax.persistence.*;

@Entity
@IdClass(ObsahujePK.class)
public class Obsahuje {
    private int idsklad;
    private int idnap;
    private int mnozstvo;

    @Id
    @Column(name = "idsklad", nullable = false)
    public int getIdsklad() {
        return idsklad;
    }

    public void setIdsklad(int idsklad) {
        this.idsklad = idsklad;
    }

    @Id
    @Column(name = "idnap", nullable = false)
    public int getIdnap() {
        return idnap;
    }

    public void setIdnap(int idnap) {
        this.idnap = idnap;
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

        Obsahuje obsahuje = (Obsahuje) o;

        if (idsklad != obsahuje.idsklad) return false;
        if (idnap != obsahuje.idnap) return false;
        if (mnozstvo != obsahuje.mnozstvo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsklad;
        result = 31 * result + idnap;
        result = 31 * result + mnozstvo;
        return result;
    }
}
