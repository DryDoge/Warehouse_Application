package db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ObalPK implements Serializable {
    private String obal;
    private int idnap;

    @Column(name = "obal", nullable = false, length = 20)
    @Id
    public String getObal() {
        return obal;
    }

    public void setObal(String obal) {
        this.obal = obal;
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

        ObalPK obalPK = (ObalPK) o;

        if (idnap != obalPK.idnap) return false;
        if (obal != null ? !obal.equals(obalPK.obal) : obalPK.obal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = obal != null ? obal.hashCode() : 0;
        result = 31 * result + idnap;
        return result;
    }
}
