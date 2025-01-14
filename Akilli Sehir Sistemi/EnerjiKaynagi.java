import java.util.ArrayList;
import java.util.List;

public class EnerjiKaynagi {
    // Önceden kayıtlı enerji kaynağı listesi
    private static List<EnerjiKaynagi> enerjiKaynagiListesi = new ArrayList<>();

    private int enerjiKaynagiID;
    private String tur;
    private double kapasite;
    private double anlikUretim;

    public EnerjiKaynagi(int enerjiKaynagiID, String tur, double kapasite) {
        this.enerjiKaynagiID = enerjiKaynagiID;
        this.tur = tur;
        this.kapasite = kapasite;
        this.anlikUretim = kapasite; // Başlangıçta tam kapasite çalıştığını varsayalım
        // Yeni enerji kaynağı oluşturulduğunda listeye ekle
        enerjiKaynagiListesi.add(this);
    }

    public void uretimIzle(double anlikUretim) {
        this.anlikUretim = anlikUretim;
        double verimlilik = (anlikUretim / kapasite) * 100;

        StringBuilder rapor = new StringBuilder();
        rapor.append("Enerji Kaynağı: ").append(tur).append(" (ID: ").append(enerjiKaynagiID).append(")\n");
        rapor.append("Tür: ").append(isYenilenebilir() ? "Yenilenebilir" : "Yenilenemeyen").append("\n");
        rapor.append("Anlık Üretim: ").append(anlikUretim).append(" kW\n");
        rapor.append("Kapasite: ").append(kapasite).append(" kW\n");
        rapor.append("Verimlilik: %").append(String.format("%.2f", verimlilik)).append("\n");

        if (verimlilik < 50) {
            rapor.append("UYARI: Düşük verimlilik! ");
            if (isYenilenebilir()) {
                rapor.append("Hava koşulları kontrol edilmeli.");
            } else {
                rapor.append("Bakım gerekebilir.");
            }
        }
    }

    public boolean yetersizUretimUyarisi() {
        double verimlilik = (anlikUretim / kapasite) * 100;

        if (verimlilik < 50) {
            System.out.println("UYARI! Enerji Kaynağı ID: " + enerjiKaynagiID);
            System.out.println("Kritik düşük üretim seviyesi: %" + String.format("%.2f", verimlilik));
            System.out.println("İnceleme gerekiyor!");
            return true;
        } else {
            System.out.println("Enerji Kaynağı ID: " + enerjiKaynagiID + " normal üretim seviyesinde.");
            return false;
        }
    }

    // Getter metodları
    public int getEnerjiKaynagiID() {
        return enerjiKaynagiID;
    }

    public String getTur() {
        return tur;
    }

    public double getKapasite() {
        return kapasite;
    }

    public double getAnlikUretim() {
        return anlikUretim;
    }

    public static List<EnerjiKaynagi> getEnerjiKaynagiListesi() {
        return enerjiKaynagiListesi;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public void setKapasite(double kapasite) {
        this.kapasite = kapasite;
    }

    public void setAnlikUretim(double anlikUretim) {
        this.anlikUretim = anlikUretim;
    }

    // Önceden tanımlı enerji kaynaklarını güncelle
    static {
        new EnerjiKaynagi(1, "Güneş Paneli A", 1000.0); // Güney bölgesi
        new EnerjiKaynagi(2, "Rüzgar Türbini A", 800.0); // Sahil bölgesi
        new EnerjiKaynagi(3, "Nükleer Santral", 5000.0); // Ana santral
        new EnerjiKaynagi(4, "Güneş Paneli B", 1200.0); // Doğu bölgesi
        new EnerjiKaynagi(5, "Rüzgar Türbini B", 900.0); // Dağ bölgesi
        new EnerjiKaynagi(6, "Hidroelektrik Santral", 3000.0); // Baraj
        new EnerjiKaynagi(7, "Biyokütle Santrali", 500.0); // Organik atık
        new EnerjiKaynagi(8, "Jeotermal Santral", 700.0); // Termal bölge
        new EnerjiKaynagi(9, "Güneş Paneli C", 800.0); // Batı bölgesi
        new EnerjiKaynagi(10, "Rüzgar Türbini C", 600.0); // İç bölge
    }

    public boolean isYenilenebilir() {
        String turLower = tur.toLowerCase();
        return turLower.contains("güneş") || turLower.contains("rüzgar") ||
                turLower.contains("hidroelektrik");
    }
}