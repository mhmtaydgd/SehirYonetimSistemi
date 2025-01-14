
// TrafikYonetimi.java
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TrafikYonetimi implements ITrafikYonetimi {
    private int trafikID;
    private List<Arac> aracListesi;
    private double trafikYogunlugu;

    public TrafikYonetimi(int trafikID) {
        this.trafikID = trafikID;
        this.aracListesi = Arac.getAracListesi();
        this.trafikYogunlugu = 0.85;
    }

    @Override
    public void araclariIzle() {
        System.out.println(araclariIzleRaporu());
    }

    @Override
    public void trafikRaporuOlustur() {
        System.out.println(trafikRaporuOlusturRaporu());
    }

    @Override
    public void sikisiklikUyarisiVer() {
        System.out.println(sikisiklikUyarisiRaporu());
    }

    // Getter metodları...
    @Override
    public int getTrafikID() {
        return trafikID;
    }

    @Override
    public List<Arac> getAracListesi() {
        return aracListesi;
    }

    @Override
    public double getTrafikYogunlugu() {
        return trafikYogunlugu;
    }

    @Override
    public String araclariIzleRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Araç Takip Raporu ===\n\n");

        int elektrikliSayisi = 0;
        int kritikSarjSayisi = 0;

        for (Arac arac : aracListesi) {
            rapor.append(arac.toString()).append("\n");
            rapor.append("------------------------\n");

            if (arac.isElektrikli()) {
                elektrikliSayisi++;
                if (arac.getSarjSeviyesi() < 20) {
                    kritikSarjSayisi++;
                }
            }
        }

        rapor.append("\nÖzet:\n");
        rapor.append("Toplam Araç: ").append(aracListesi.size()).append("\n");
        rapor.append("Elektrikli Araç: ").append(elektrikliSayisi).append("\n");
        rapor.append("Kritik Şarj Seviyesinde: ").append(kritikSarjSayisi);

        return rapor.toString();
    }

    @Override
    public String trafikRaporuOlusturRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Trafik Durum Raporu ===\n\n");
        rapor.append("Bölge ID: ").append(trafikID).append("\n");
        rapor.append("Toplam Araç: ").append(aracListesi.size()).append("\n");
        rapor.append("Trafik Yoğunluğu: %").append(String.format("%.1f", trafikYogunlugu * 100)).append("\n\n");

        // Araç türlerine göre dağılım
        Map<String, Integer> turDagilimi = new HashMap<>();
        for (Arac arac : aracListesi) {
            turDagilimi.merge(arac.getTur(), 1, Integer::sum);
        }

        rapor.append("Araç Dağılımı:\n");
        turDagilimi.forEach((tur, sayi) -> rapor.append("- ").append(tur).append(": ").append(sayi).append("\n"));

        return rapor.toString();
    }

    @Override
    public String sikisiklikUyarisiRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Trafik Sıkışıklık Durumu ===\n\n");

        if (trafikYogunlugu > 0.8) {
            rapor.append("UYARI: YÜKSEK TRAFİK YOĞUNLUĞU!\n");
            rapor.append("Yoğunluk Seviyesi: %").append(String.format("%.1f", trafikYogunlugu * 100)).append("\n\n");
            rapor.append("Önerilen Alternatif Rotalar:\n");
            rapor.append("1. Sahil yolu güzergahı\n");
            rapor.append("2. Çevre yolu güzergahı\n");
            rapor.append("3. Toplu taşıma kullanımı\n");
        } else {
            rapor.append("Trafik akışı normal seviyede.\n");
            rapor.append("Mevcut Yoğunluk: %").append(String.format("%.1f", trafikYogunlugu * 100));
        }

        return rapor.toString();
    }

    public void trafikYogunluguGuncelle(double yeniYogunluk) {
        if (yeniYogunluk >= 0 && yeniYogunluk <= 1) {
            this.trafikYogunlugu = yeniYogunluk;
            if (trafikYogunlugu > 0.8) {
                sikisiklikUyarisiVer();
            }
        } else {
            throw new IllegalArgumentException("Trafik yoğunluğu 0-1 arasında olmalıdır!");
        }
    }
}