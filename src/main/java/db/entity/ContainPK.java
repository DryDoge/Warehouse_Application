package db.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ContainPK implements Serializable {
    private int storageid;
    private int beverageid;

    @Column(name = "storageid", nullable = false)
    @Id
    public int getStorageid() {
        return storageid;
    }

    public void setStorageid(int storageid) {
        this.storageid = storageid;
    }

    @Column(name = "beverageid", nullable = false)
    @Id
    public int getBeverageid() {
        return beverageid;
    }

    public void setBeverageid(int beverageid) {
        this.beverageid = beverageid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContainPK containPK = (ContainPK) o;
        return storageid == containPK.storageid &&
                beverageid == containPK.beverageid;
    }

    @Override
    public int hashCode() {

        return Objects.hash(storageid, beverageid);
    }
}
