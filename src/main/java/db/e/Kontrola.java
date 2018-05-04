package db.e;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(KontrolaPK.class)
public class Kontrola {
    private int idsklad;
    private int idzam;

    @Id
    @Column(name = "idsklad", nullable = false)
    public int getIdsklad() {
        return idsklad;
    }

    public void setIdsklad(int idsklad) {
        this.idsklad = idsklad;
    }

    @Id
    @Column(name = "idzam", nullable = false)
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

        Kontrola kontrola = (Kontrola) o;

        if (idsklad != kontrola.idsklad) return false;
        if (idzam != kontrola.idzam) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsklad;
        result = 31 * result + idzam;
        return result;
    }
}
