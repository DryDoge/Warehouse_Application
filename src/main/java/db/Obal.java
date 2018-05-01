package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ObalPK.class)
public class Obal {
    private String obal;
    private int idnap;

    @Id
    @Column(name = "obal", nullable = false, length = 20)
    public String getObal() {
        return obal;
    }

    public void setObal(String obal) {
        this.obal = obal;
    }

    @Id
    @Column(name = "idnap", nullable = false)
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

        Obal obal1 = (Obal) o;

        if (idnap != obal1.idnap) return false;
        if (obal != null ? !obal.equals(obal1.obal) : obal1.obal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = obal != null ? obal.hashCode() : 0;
        result = 31 * result + idnap;
        return result;
    }
}
