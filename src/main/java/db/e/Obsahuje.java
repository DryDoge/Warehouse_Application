package db.e;

import javax.persistence.*;
import java.util.Objects;

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
        return idsklad == obsahuje.idsklad &&
                idnap == obsahuje.idnap &&
                mnozstvo == obsahuje.mnozstvo;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idsklad, idnap, mnozstvo);
    }
}
