package db.e;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(ZamestnanecPK.class)
public class Zamestnanec {
    private int idsklad;
    private int idzam;
    private String meno;
    private String priezvisko;
    private String tel;
    private String rc;
    private String email;

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
    @Column(name = "meno", nullable = false, length = 50)
    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    @Basic
    @Column(name = "priezvisko", nullable = false, length = 50)
    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
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
    @Column(name = "rc", nullable = false, length = 11)
    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
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
        Zamestnanec that = (Zamestnanec) o;
        return idsklad == that.idsklad &&
                idzam == that.idzam &&
                Objects.equals(meno, that.meno) &&
                Objects.equals(priezvisko, that.priezvisko) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(rc, that.rc) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idsklad, idzam, meno, priezvisko, tel, rc, email);
    }
}
