
// Bina.java
import java.util.ArrayList;
import java.util.List;

public class Bina implements IBina {
    private static List<Bina> binaListesi = new ArrayList<>();
    private int binaID;
    private String tur;
    private int katSayisi;
    private double enerjiVerimliligi;
    private boolean tasarrufModu = false;

    public Bina(int binaID, String tur, int katSayisi, double enerjiVerimliligi) {
        this.binaID = binaID;
        this.tur = tur;
        this.katSayisi = katSayisi;
        this.enerjiVerimliligi = enerjiVerimliligi;
        binaListesi.add(this);
    }

    // Önceden tanımlı binaları güncelle
    static {
        new Bina(1, "Akıllı Konut", 10, 92.5); // Optimal verimlilik
        new Bina(2, "Geleneksel Ofis", 15, 68.3); // Düşük verimlilik
        new Bina(3, "Akıllı AVM", 5, 88.1); // İyi verimlilik
        new Bina(4, "Akıllı Hastane", 8, 75.7); // Düşük verimlilik
        new Bina(5, "Geleneksel Okul", 4, 71.4); // Düşük verimlilik
        new Bina(6, "Akıllı İş Merkezi", 25, 85.5); // İyi verimlilik
        new Bina(7, "Akıllı Rezidans", 30, 78.9); // Düşük verimlilik
        new Bina(8, "Akıllı Spor Kompleksi", 3, 82.3); // İyi verimlilik
        new Bina(9, "Geleneksel Market", 2, 65.8); // Çok düşük verimlilik
        new Bina(10, "Akıllı Üniversite", 6, 89.5); // İyi verimlilik
    }

    @Override
    public int getBinaID() {
        return binaID;
    }

    @Override
    public String getTur() {
        return tur;
    }

    @Override
    public int getKatSayisi() {
        return katSayisi;
    }

    @Override
    public double getEnerjiVerimliligi() {
        return enerjiVerimliligi;
    }

    @Override
    public void enerjiRaporuOlustur() {
        System.out.println(enerjiRaporuOlusturRaporu());
    }

    @Override
    public String enerjiRaporuOlusturRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Bina Enerji Raporu ===\n\n");
        rapor.append("Bina ID: ").append(binaID).append("\n");
        rapor.append("Tür: ").append(tur).append(" (").append(isAkilli() ? "Akıllı" : "Geleneksel").append(")\n");
        rapor.append("Kat Sayısı: ").append(katSayisi).append("\n");
        rapor.append("Enerji Verimliliği: %").append(String.format("%.1f", enerjiVerimliligi)).append("\n");
        rapor.append("Tasarruf Modu: ").append(tasarrufModu ? "Aktif" : "Pasif").append("\n\n");

        if (enerjiVerimliligi < 75) {
            rapor.append("UYARI: Düşük enerji verimliliği!\n");
            rapor.append("Önerilen Aksiyonlar:\n");
            if (isAkilli()) {
                rapor.append("- Tasarruf modunu aktifleştirin\n");
                rapor.append("- Sensör sistemlerini kontrol edin\n");
                rapor.append("- Otomasyon ayarlarını optimize edin\n");
            } else {
                rapor.append("- Yalıtım iyileştirmesi yapın\n");
                rapor.append("- LED aydınlatmaya geçin\n");
                rapor.append("- Akıllı sistemlere yükseltme yapın\n");
            }
        }

        return rapor.toString();
    }

    @Override
    public void enerjiTuketiminiOptimizeEt() {
        if (isAkilli()) {
            tasarrufModu = true;
            enerjiVerimliligi += 5.0; // Tasarruf modu aktifken verimlilik artar
            if (enerjiVerimliligi > 100)
                enerjiVerimliligi = 100;
        }
    }

    @Override
    public String enerjiTuketiminiOptimizeEtRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Bina Enerji Optimizasyon Raporu ===\n\n");
        rapor.append("Bina ID: ").append(binaID).append("\n");
        rapor.append("Tür: ").append(tur).append("\n");
        rapor.append("Mevcut Verimlilik: %").append(String.format("%.1f", enerjiVerimliligi)).append("\n");
        rapor.append("Tasarruf Modu: ").append(tasarrufModu ? "Aktif" : "Pasif").append("\n\n");

        if (isAkilli()) {
            rapor.append("Optimizasyon Önlemleri:\n");
            rapor.append("1. Akıllı aydınlatma sistemi ")
                    .append(tasarrufModu ? "düşük güç modunda\n" : "normal modda\n");
            rapor.append("2. İklimlendirme sistemi ").append(tasarrufModu ? "eko modda\n" : "normal modda\n");
            rapor.append("3. Güneş panelleri ").append(tasarrufModu ? "maksimum verimde\n" : "normal modda\n");
            rapor.append("4. Boş alan sensörleri ").append(tasarrufModu ? "yüksek hassasiyette\n" : "normal modda\n");
        } else {
            rapor.append("Bu bina akıllı sistemlere sahip değil.\n");
            rapor.append("Önerilen İyileştirmeler:\n");
            rapor.append("1. LED aydınlatmaya geçiş\n");
            rapor.append("2. Yalıtım güçlendirme\n");
            rapor.append("3. Akıllı sistemlere yükseltme\n");
        }

        return rapor.toString();
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public void setKatSayisi(int katSayisi) {
        this.katSayisi = katSayisi;
    }

    public void setEnerjiVerimliligi(double enerjiVerimliligi) {
        this.enerjiVerimliligi = enerjiVerimliligi;
        if (this.enerjiVerimliligi > 100)
            this.enerjiVerimliligi = 100;
    }

    public static List<Bina> getBinaListesi() {
        return binaListesi;
    }

    public boolean isAkilli() {
        return tur.toLowerCase().contains("akıllı");
    }

    public boolean isTasarrufModu() {
        return tasarrufModu;
    }
}