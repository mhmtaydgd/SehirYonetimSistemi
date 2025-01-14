
// IEnerjiYonetimi.java
import java.util.List;

public interface IEnerjiYonetimi {
    int getEnerjiYonetimiID();

    List<EnerjiKaynagi> getEnerjiKaynaklari();

    double getToplamUretim();

    double getToplamTuketim();

    void uretimTuketimKarsilastir();

    void yeniEnerjiKaynagiOner();

    void uyumsuzlukUyarisiVer();

    String uretimTuketimKarsilastirRaporu();

    String yeniEnerjiKaynagiOnerRaporu();

    String uyumsuzlukUyarisiRaporu();

    void uretimTuketimGuncelle();
}