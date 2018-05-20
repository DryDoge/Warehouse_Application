package db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(ContainPK.class)
public class Contain {
    private int storageid;
    private int beverageid;
    private int amount;

    @Id
    @Column(name = "storageid", nullable = false)
    public int getStorageid() {
        return storageid;
    }

    public void setStorageid(int storageid) {
        this.storageid = storageid;
    }

    @Id
    @Column(name = "beverageid", nullable = false)
    public int getBeverageid() {
        return beverageid;
    }

    public void setBeverageid(int beverageid) {
        this.beverageid = beverageid;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contain contain = (Contain) o;
        return storageid == contain.storageid &&
                beverageid == contain.beverageid &&
                amount == contain.amount;
    }

    @Override
    public int hashCode() {

        return Objects.hash(storageid, beverageid, amount);
    }
}
