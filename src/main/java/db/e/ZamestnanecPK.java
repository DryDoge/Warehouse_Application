package db.e;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ZamestnanecPK implements Serializable {
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
        ZamestnanecPK that = (ZamestnanecPK) o;
        return idsklad == that.idsklad &&
                idzam == that.idzam;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idsklad, idzam);
    }
}
