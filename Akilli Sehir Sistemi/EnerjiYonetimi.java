
// EnerjiYonetimi.java
import java.util.List;

public class EnerjiYonetimi implements IEnerjiYonetimi {
    private int enerjiYonetimiID;
    private List<EnerjiKaynagi> enerjiKaynaklari;
    private double toplamUretim;
    private double toplamTuketim;

    public EnerjiYonetimi(int enerjiYonetimiID) {
        this.enerjiYonetimiID = enerjiYonetimiID;
        this.enerjiKaynaklari = EnerjiKaynagi.getEnerjiKaynagiListesi();
        hesaplaToplamlar();
    }

    private void hesaplaToplamlar() {
        this.toplamUretim = enerjiKaynaklari.stream()
                .mapToDouble(EnerjiKaynagi::getAnlikUretim)
                .sum();
        // Örnek olarak tüketimi üretimin %80'i olarak varsayalım
        this.toplamTuketim = toplamUretim * 0.8;
    }

    @Override
    public void uretimTuketimKarsilastir() {
        System.out.println(uretimTuketimKarsilastirRaporu());
    }

    @Override
    public void yeniEnerjiKaynagiOner() {
        System.out.println(yeniEnerjiKaynagiOnerRaporu());
    }

    @Override
    public void uyumsuzlukUyarisiVer() {
        System.out.println(uyumsuzlukUyarisiRaporu());
    }

    public void uretimTuketimGuncelle() {
        hesaplaToplamlar();
        if (toplamUretim < toplamTuketim) {
            uyumsuzlukUyarisiVer();
        }
    }

    public String uretimTuketimKarsilastirRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("Enerji Üretim-Tüketim Analizi:\n");
        rapor.append("Toplam Üretim: ").append(toplamUretim).append(" MW\n");
        rapor.append("Toplam Tüketim: ").append(toplamTuketim).append(" MW\n");
        rapor.append("Fark: ").append(toplamUretim - toplamTuketim).append(" MW");
        return rapor.toString();
    }

    public String yeniEnerjiKaynagiOnerRaporu() {
        if (toplamUretim < toplamTuketim * 1.2) {
            StringBuilder rapor = new StringBuilder();
            rapor.append("Yeni enerji kaynağı önerisi:\n");
            rapor.append("- Güneş enerjisi santrali kurulumu\n");
            rapor.append("- Rüzgar türbini eklenmesi");
            return rapor.toString();
        }
        return "Yeni enerji kaynağına ihtiyaç yok.";
    }

    public String uyumsuzlukUyarisiRaporu() {
        if (toplamUretim < toplamTuketim) {
            StringBuilder rapor = new StringBuilder();
            rapor.append("UYARI: Enerji üretimi tüketimi karşılamıyor!\n");
            rapor.append("Acil önlem alınması gerekiyor.");
            return rapor.toString();
        }
        return "Enerji üretimi yeterli seviyede.";
    }

    // Getter metodları...
    @Override
    public int getEnerjiYonetimiID() {
        return enerjiYonetimiID;
    }

    @Override
    public List<EnerjiKaynagi> getEnerjiKaynaklari() {
        return enerjiKaynaklari;
    }

    @Override
    public double getToplamUretim() {
        return toplamUretim;
    }

    @Override
    public double getToplamTuketim() {
        return toplamTuketim;
    }
}