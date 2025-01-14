// Vatandas.java
public class Vatandas extends Kullanici {
    private int vatandasID;

    public Vatandas(String id, String isim) {
        super(Integer.parseInt(id), isim);
    }

    @Override
    public void girisYap() {
        System.out.println("Vatandaş " + getIsim() + " sisteme giriş yaptı.");
    }

    @Override
    public void cikisYap() {
        System.out.println("Vatandaş " + getIsim() + " sistemden çıkış yaptı.");
    }

    public void trafikBilgisiGoruntule() {
        TrafikYonetimi trafikYonetimi = new TrafikYonetimi(1);
        trafikYonetimi.trafikRaporuOlustur();
    }

    public void enerjiRaporuGoruntule() {
        EnerjiYonetimi enerjiYonetimi = new EnerjiYonetimi(1);
        enerjiYonetimi.uretimTuketimKarsilastir();
    }

    public void suSeviyeleriniTakipEt() {
        SuYonetimi suYonetimi = new SuYonetimi(1, 1000.0);
        suYonetimi.suSeviyeleriniIzle();
    }

    public int getVatandasID() {
        return vatandasID;
    }

    public String trafikBilgisiGoruntuleRaporu() {
        TrafikYonetimi trafikYonetimi = new TrafikYonetimi(1);
        return trafikYonetimi.trafikRaporuOlusturRaporu();
    }

    public String enerjiRaporuGoruntuleRaporu() {
        EnerjiYonetimi enerjiYonetimi = new EnerjiYonetimi(1);
        return enerjiYonetimi.uretimTuketimKarsilastirRaporu();
    }

    public String suSeviyeleriniTakipEtRaporu() {
        SuYonetimi suYonetimi = new SuYonetimi(1, 1000.0);
        return suYonetimi.suSeviyeleriniIzleRaporu();
    }
}