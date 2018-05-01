package db;

import javax.persistence.*;

@Entity
@IdClass(SkladnikPK.class)
public class Skladnik {
    private int idsklad;
    private int idzam;
    private boolean vodicakvzv;

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

    @Basic
    @Column(name = "vodicakvzv", nullable = false)
    public boolean isVodicakvzv() {
        return vodicakvzv;
    }

    public void setVodicakvzv(boolean vodicakvzv) {
        this.vodicakvzv = vodicakvzv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skladnik skladnik = (Skladnik) o;

        if (idsklad != skladnik.idsklad) return false;
        if (idzam != skladnik.idzam) return false;
        if (vodicakvzv != skladnik.vodicakvzv) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsklad;
        result = 31 * result + idzam;
        result = 31 * result + (vodicakvzv ? 1 : 0);
        return result;
    }
}
