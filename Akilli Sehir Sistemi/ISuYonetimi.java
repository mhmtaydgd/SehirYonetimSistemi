// ISuYonetimi.java
public interface ISuYonetimi {
    int getSuYonetimiID();

    double getSuTuketimi();

    double getSuRezervi();

    void suSeviyeleriniIzle();

    void dusukRezervUyarisiVer();

    String suSeviyeleriniIzleRaporu();

    String dusukRezervUyarisiRaporu();

    void gunlukTuketimGuncelle(double yeniTuketim);

    void rezervGuncelle(double yeniRezerv);
}