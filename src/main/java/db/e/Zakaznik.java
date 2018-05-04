package db.e;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Zakaznik {
    private int idzak;
    private String tel;
    private String email;

    @Id
    @Column(name = "idzak", nullable = false)
    public int getIdzak() {
        return idzak;
    }

    public void setIdzak(int idzak) {
        this.idzak = idzak;
    }

    @Basic
    @Column(name = "tel", nullable = false, length = 9)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zakaznik zakaznik = (Zakaznik) o;

        if (idzak != zakaznik.idzak) return false;
        if (tel != null ? !tel.equals(zakaznik.tel) : zakaznik.tel != null) return false;
        if (email != null ? !email.equals(zakaznik.email) : zakaznik.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idzak;
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
