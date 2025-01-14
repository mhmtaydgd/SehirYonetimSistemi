// SuYonetimi.java
public class SuYonetimi implements ISuYonetimi {
    private static final double KRITIK_SEVIYE = 1000.0;
    private static final double NORMAL_SEVIYE = 2000.0;
    private static final double OPTIMAL_SEVIYE = 3000.0;

    private int suYonetimiID;
    private double suTuketimi;
    private double suRezervi;

    public SuYonetimi(int suYonetimiID, double suRezervi) {
        this.suYonetimiID = suYonetimiID;
        this.suRezervi = suRezervi;
        this.suTuketimi = suRezervi * 0.15;
    }

    @Override
    public void suSeviyeleriniIzle() {
        System.out.println(suSeviyeleriniIzleRaporu());
    }

    @Override
    public void dusukRezervUyarisiVer() {
        System.out.println(dusukRezervUyarisiRaporu());
    }

    // Getter metodları...
    @Override
    public int getSuYonetimiID() {
        return suYonetimiID;
    }

    @Override
    public double getSuTuketimi() {
        return suTuketimi;
    }

    @Override
    public double getSuRezervi() {
        return suRezervi;
    }

    @Override
    public String suSeviyeleriniIzleRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Su Seviyeleri Raporu ===\n\n");
        rapor.append("Bölge ID: ").append(suYonetimiID).append("\n");
        rapor.append("Mevcut Rezerv: ").append(String.format("%.1f", suRezervi)).append(" m³\n");
        rapor.append("Günlük Tüketim: ").append(String.format("%.1f", suTuketimi)).append(" m³\n\n");

        // Durum analizi
        double dolulukOrani = (suRezervi / OPTIMAL_SEVIYE) * 100;
        rapor.append("Rezerv Doluluk Oranı: %").append(String.format("%.1f", dolulukOrani)).append("\n");

        if (dolulukOrani > 80) {
            rapor.append("Durum: İYİ - Optimal seviyede\n");
        } else if (dolulukOrani > 50) {
            rapor.append("Durum: NORMAL - Takip edilmeli\n");
        } else {
            rapor.append("Durum: KRİTİK - Tasarruf önlemleri gerekli!\n");
        }

        return rapor.toString();
    }

    @Override
    public String dusukRezervUyarisiRaporu() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Su Rezervi Durum Raporu ===\n\n");

        if (suRezervi < KRITIK_SEVIYE) {
            rapor.append("UYARI: KRİTİK DÜŞÜK SU REZERVİ!\n\n");
        } else if (suRezervi < NORMAL_SEVIYE) {
            rapor.append("UYARI: NORMAL SEVİYENİN ALTINDA!\n\n");
        }

        if (suRezervi < KRITIK_SEVIYE) {
            rapor.append("Mevcut Rezerv: ").append(String.format("%.1f", suRezervi)).append(" m³\n");
            rapor.append("Kritik Seviye: ").append(KRITIK_SEVIYE).append(" m³\n\n");

            rapor.append("Önerilen Tasarruf Önlemleri:\n");
            rapor.append("1. Bahçe sulaması kısıtlaması\n");
            rapor.append("2. Araç yıkama kısıtlaması\n");
            rapor.append("3. Endüstriyel kullanım optimizasyonu\n");
            rapor.append("4. Vatandaş bilinçlendirme kampanyası\n");
        } else {
            rapor.append("Su rezervi yeterli seviyede.\n");
            rapor.append("Mevcut Rezerv: ").append(String.format("%.1f", suRezervi)).append(" m³\n");
            rapor.append("Kritik Seviye: ").append(KRITIK_SEVIYE).append(" m³");
        }

        return rapor.toString();
    }

    // Yeni yardımcı metodlar
    public void gunlukTuketimGuncelle(double yeniTuketim) {
        if (yeniTuketim >= 0) {
            this.suTuketimi = yeniTuketim;
            if (yeniTuketim > suRezervi * 0.1) { // Günlük tüketim rezervin %10'unu aşıyorsa
                dusukRezervUyarisiVer();
            }
        }
    }

    public void rezervGuncelle(double yeniRezerv) {
        if (yeniRezerv >= 0) {
            this.suRezervi = yeniRezerv;
            if (suRezervi < KRITIK_SEVIYE) {
                dusukRezervUyarisiVer();
            }
        }
    }
}