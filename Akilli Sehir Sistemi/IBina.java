// IBina.java
public interface IBina {
    int getBinaID();

    String getTur();

    int getKatSayisi();

    double getEnerjiVerimliligi();

    void enerjiRaporuOlustur();

    void enerjiTuketiminiOptimizeEt();

    String enerjiRaporuOlusturRaporu();

    String enerjiTuketiminiOptimizeEtRaporu();

    boolean isAkilli();
}