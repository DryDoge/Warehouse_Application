package db.e;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dodava {
    private int idnap;

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

        Dodava dodava = (Dodava) o;

        if (idnap != dodava.idnap) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idnap;
    }
}
