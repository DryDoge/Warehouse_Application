package db;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alko {
    private int idnap;
    private short obsahAlk;

    @Id
    @Column(name = "idnap", nullable = false)
    public int getIdnap() {
        return idnap;
    }

    public void setIdnap(int idnap) {
        this.idnap = idnap;
    }

    @Basic
    @Column(name = "obsah_alk", nullable = false)
    public short getObsahAlk() {
        return obsahAlk;
    }

    public void setObsahAlk(short obsahAlk) {
        this.obsahAlk = obsahAlk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alko alko = (Alko) o;

        if (idnap != alko.idnap) return false;
        if (obsahAlk != alko.obsahAlk) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idnap;
        result = 31 * result + (int) obsahAlk;
        return result;
    }
}
