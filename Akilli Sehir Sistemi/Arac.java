import java.util.ArrayList;
import java.util.List;

public class Arac {
    // Önceden kayıtlı araç listesi
    private static List<Arac> aracListesi = new ArrayList<>();

    private int aracID;
    private String tur;
    private int kapasite;
    private double sarjSeviyesi;

    public Arac(int aracID, String tur, int kapasite, double sarjSeviyesi) {
        this.aracID = aracID;
        this.tur = tur;
        this.kapasite = kapasite;
        this.sarjSeviyesi = sarjSeviyesi;
        // Yeni araç oluşturulduğunda listeye ekle
        aracListesi.add(this);
    }

    public void sarjSeviyesiGuncelle(double yeniSarjSeviyesi) {
        if (!isElektrikli()) {
            throw new IllegalStateException("Bu araç elektrikli değil!");
        }
        if (yeniSarjSeviyesi >= 0 && yeniSarjSeviyesi <= 100) {
            this.sarjSeviyesi = yeniSarjSeviyesi;
            if (sarjSeviyesi < 20) {
                sarjIstasyonunaYonlendir();
            }
        } else {
            throw new IllegalArgumentException("Şarj seviyesi 0-100 arasında olmalıdır!");
        }
    }

    public boolean sarjIstasyonunaYonlendir() {
        if (sarjSeviyesi < 20) {
            System.out.println("Araç " + aracID + " şarj seviyesi kritik (%" + sarjSeviyesi + ")!");
            System.out.println("En yakın şarj istasyonuna yönlendiriliyor...");
            return true;
        } else {
            System.out.println("Araç " + aracID + " şarj seviyesi yeterli (%" + sarjSeviyesi + ").");
            return false;
        }
    }

    // Getter ve Setter metodları
    public int getAracID() {
        return aracID;
    }

    public String getTur() {
        return tur;
    }

    public int getKapasite() {
        return kapasite;
    }

    public double getSarjSeviyesi() {
        return sarjSeviyesi;
    }

    public static List<Arac> getAracListesi() {
        return aracListesi;
    }

    // Setter metodları
    public void setTur(String tur) {
        this.tur = tur;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    // Önceden tanımlı araçları güncelle
    static {
        new Arac(1, "Elektrikli Otobüs", 50, 85.5); // Normal şarjlı
        new Arac(2, "Otobüs", 45, 0.0); // Elektrikli değil
        new Arac(3, "Elektrikli Otomobil", 5, 15.2); // Kritik şarj seviyesi
        new Arac(4, "Motosiklet", 2, 0.0); // Elektrikli değil
        new Arac(5, "Elektrikli Minibüs", 15, 25.0); // Düşük şarj seviyesi
        new Arac(6, "Elektrikli Otobüs", 50, 10.5); // Kritik şarj seviyesi
        new Arac(7, "Tramvay", 120, 90.0); // İyi şarj seviyesi
        new Arac(8, "Metro", 500, 95.0); // İyi şarj seviyesi
        new Arac(9, "Elektrikli Scooter", 1, 18.5); // Kritik şarj seviyesi
        new Arac(10, "Elektrikli Bisiklet", 1, 30.0); // Düşük şarj seviyesi
    }

    public boolean isElektrikli() {
        return tur.toLowerCase().contains("elektrikli");
    }

    @Override
    public String toString() {
        StringBuilder bilgi = new StringBuilder();
        bilgi.append("Araç ID: ").append(aracID).append("\n");
        bilgi.append("Tür: ").append(tur).append("\n");
        bilgi.append("Yolcu Kapasitesi: ").append(kapasite).append("\n");
        if (isElektrikli()) {
            bilgi.append("Şarj Seviyesi: %").append(sarjSeviyesi);
        }
        return bilgi.toString();
    }

    public String sarjIstasyonunaYondlendirRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Araç Şarj Durumu ===\n\n");
        rapor.append("Araç ID: ").append(aracID).append("\n");
        rapor.append("Tür: ").append(tur).append("\n");

        if (isElektrikli()) {
            rapor.append("Şarj Seviyesi: %").append(String.format("%.1f", sarjSeviyesi)).append("\n");
            if (sarjSeviyesi < 20) {
                rapor.append("\nUYARI: Kritik şarj seviyesi!\n");
                rapor.append("En yakın şarj istasyonuna yönlendiriliyorsunuz.");
            } else {
                rapor.append("\nŞarj seviyesi yeterli.");
            }
        } else {
            rapor.append("\nBu araç elektrikli değil.");
        }
        return rapor.toString();
    }
}