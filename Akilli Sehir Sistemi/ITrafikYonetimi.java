
// ITrafikYonetimi.java
import java.util.List;

public interface ITrafikYonetimi {
    int getTrafikID();

    List<Arac> getAracListesi();

    double getTrafikYogunlugu();

    void araclariIzle();

    void trafikRaporuOlustur();

    void sikisiklikUyarisiVer();

    String araclariIzleRaporu();

    String trafikRaporuOlusturRaporu();

    String sikisiklikUyarisiRaporu();

    void trafikYogunluguGuncelle(double yeniYogunluk);
}