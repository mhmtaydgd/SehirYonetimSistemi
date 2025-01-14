// SehirYoneticisi.java
public class SehirYoneticisi extends Kullanici {
    public SehirYoneticisi(String id, String isim) {
        super(Integer.parseInt(id), isim);
    }

    @Override
    public void girisYap() {
        System.out.println("Yönetici " + getIsim() + " sisteme giriş yaptı.");
    }

    @Override
    public void cikisYap() {
        System.out.println("Yönetici " + getIsim() + " sistemden çıkış yaptı.");
    }

    public String varlikEkle(String varlikTipi, Object... parametreler) {
        StringBuilder sonuc = new StringBuilder();
        switch (varlikTipi) {
            case "Arac":
                Arac yeniArac = new Arac(
                        (int) parametreler[0],
                        (String) parametreler[1],
                        (int) parametreler[2],
                        (double) parametreler[3]);
                sonuc.append("Yeni araç eklendi: ").append(yeniArac.getAracID());
                break;

            case "Bina":
                Bina yeniBina = new Bina(
                        (int) parametreler[0],
                        (String) parametreler[1],
                        (int) parametreler[2],
                        (double) parametreler[3]);
                sonuc.append("Yeni bina eklendi: ").append(yeniBina.getBinaID());
                break;

            case "EnerjiKaynagi":
                EnerjiKaynagi yeniKaynak = new EnerjiKaynagi(
                        (int) parametreler[0],
                        (String) parametreler[1],
                        (double) parametreler[2]);
                sonuc.append("Yeni enerji kaynağı eklendi: ").append(yeniKaynak.getEnerjiKaynagiID());
                break;
        }
        return sonuc.toString();
    }

    public String varlikGuncelle(String varlikTipi, int varlikID, Object... yeniDegerler) {
        StringBuilder sonuc = new StringBuilder();
        switch (varlikTipi) {
            case "Arac":
                for (Arac arac : Arac.getAracListesi()) {
                    if (arac.getAracID() == varlikID) {
                        // Aracı güncelle
                        arac.setTur((String) yeniDegerler[0]);
                        arac.setKapasite((int) yeniDegerler[1]);
                        arac.sarjSeviyesiGuncelle((double) yeniDegerler[2]);
                        sonuc.append("Araç güncellendi - ID: ").append(varlikID);
                        return sonuc.toString();
                    }
                }
                break;

            case "Bina":
                for (Bina bina : Bina.getBinaListesi()) {
                    if (bina.getBinaID() == varlikID) {
                        // Binayı güncelle
                        bina.setTur((String) yeniDegerler[0]);
                        bina.setKatSayisi((int) yeniDegerler[1]);
                        bina.setEnerjiVerimliligi((double) yeniDegerler[2]);
                        sonuc.append("Bina güncellendi - ID: ").append(varlikID);
                        return sonuc.toString();
                    }
                }
                break;

            case "EnerjiKaynagi":
                for (EnerjiKaynagi kaynak : EnerjiKaynagi.getEnerjiKaynagiListesi()) {
                    if (kaynak.getEnerjiKaynagiID() == varlikID) {
                        // Enerji kaynağını güncelle
                        kaynak.setTur((String) yeniDegerler[0]);
                        kaynak.setKapasite((double) yeniDegerler[1]);
                        kaynak.setAnlikUretim((double) yeniDegerler[2]);
                        sonuc.append("Enerji kaynağı güncellendi - ID: ").append(varlikID);
                        return sonuc.toString();
                    }
                }
                break;
        }
        return "Belirtilen ID'ye sahip " + varlikTipi + " bulunamadı!";
    }

    public String hizmetleriIzle() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Hizmet İzleme Raporu ===\n\n");

        TrafikYonetimi trafikYonetimi = new TrafikYonetimi(1);
        SuYonetimi suYonetimi = new SuYonetimi(1, 1000.0);
        EnerjiYonetimi enerjiYonetimi = new EnerjiYonetimi(1);

        rapor.append("--- Trafik Durumu ---\n");
        rapor.append(trafikYonetimi.araclariIzleRaporu()).append("\n\n");

        rapor.append("--- Su Durumu ---\n");
        rapor.append(suYonetimi.suSeviyeleriniIzleRaporu()).append("\n\n");

        rapor.append("--- Enerji Durumu ---\n");
        rapor.append(enerjiYonetimi.uretimTuketimKarsilastirRaporu());

        return rapor.toString();
    }

    public String raporOlustur() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Genel Durum Raporu ===\n\n");

        TrafikYonetimi trafikYonetimi = new TrafikYonetimi(1);
        SuYonetimi suYonetimi = new SuYonetimi(1, 1000.0);
        EnerjiYonetimi enerjiYonetimi = new EnerjiYonetimi(1);

        rapor.append(trafikYonetimi.trafikRaporuOlusturRaporu()).append("\n\n");
        rapor.append(suYonetimi.suSeviyeleriniIzleRaporu()).append("\n\n");
        rapor.append(enerjiYonetimi.uretimTuketimKarsilastirRaporu());

        return rapor.toString();
    }

    public String sorunCoz(String sorunTipi) {
        return "Sorun çözülüyor: " + sorunTipi;
    }

    public static boolean yoneticiMi(String id, String isim) {
        return id.equals("190290") && isim.equalsIgnoreCase("mehmet");
    }
}