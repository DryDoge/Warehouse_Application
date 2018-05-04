package db.e;

import javax.persistence.*;

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

        if (idsklad != that.idsklad) return false;
        if (idzam != that.idzam) return false;
        if (meno != null ? !meno.equals(that.meno) : that.meno != null) return false;
        if (priezvisko != null ? !priezvisko.equals(that.priezvisko) : that.priezvisko != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (rc != null ? !rc.equals(that.rc) : that.rc != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsklad;
        result = 31 * result + idzam;
        result = 31 * result + (meno != null ? meno.hashCode() : 0);
        result = 31 * result + (priezvisko != null ? priezvisko.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (rc != null ? rc.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
