// Kullanici.java
public abstract class Kullanici {
    protected int kullaniciID;
    protected String isim;

    public Kullanici(int kullaniciID, String isim) {
        this.kullaniciID = kullaniciID;
        this.isim = isim;
    }

    public void girisYap() {
        System.out.println(isim + " sisteme giriş yaptı.");
    }

    public void cikisYap() {
        System.out.println(isim + " sistemden çıkış yaptı.");
    }

    // Getter metodları
    public int getKullaniciID() {
        return kullaniciID;
    }

    public String getIsim() {
        return isim;
    }
}