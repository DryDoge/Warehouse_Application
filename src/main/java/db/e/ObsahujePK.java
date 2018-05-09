package db.e;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ObsahujePK implements Serializable {
    private int idsklad;
    private int idnap;

    @Column(name = "idsklad", nullable = false)
    @Id
    public int getIdsklad() {
        return idsklad;
    }

    public void setIdsklad(int idsklad) {
        this.idsklad = idsklad;
    }

    @Column(name = "idnap", nullable = false)
    @Id
    public int getIdnap() {
        return idnap;
    }

    public void setIdnap(int idnap) {
        this.idnap = idnap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObsahujePK that = (ObsahujePK) o;
        return idsklad == that.idsklad &&
                idnap == that.idnap;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idsklad, idnap);
    }
}
