package db.e;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

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

        if (idsklad != that.idsklad) return false;
        if (idnap != that.idnap) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsklad;
        result = 31 * result + idnap;
        return result;
    }
}
