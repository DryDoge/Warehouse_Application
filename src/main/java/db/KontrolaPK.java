package db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class KontrolaPK implements Serializable {
    private int idsklad;
    private int idzam;

    @Column(name = "idsklad", nullable = false)
    @Id
    public int getIdsklad() {
        return idsklad;
    }

    public void setIdsklad(int idsklad) {
        this.idsklad = idsklad;
    }

    @Column(name = "idzam", nullable = false)
    @Id
    public int getIdzam() {
        return idzam;
    }

    public void setIdzam(int idzam) {
        this.idzam = idzam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KontrolaPK that = (KontrolaPK) o;

        if (idsklad != that.idsklad) return false;
        if (idzam != that.idzam) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsklad;
        result = 31 * result + idzam;
        return result;
    }
}
