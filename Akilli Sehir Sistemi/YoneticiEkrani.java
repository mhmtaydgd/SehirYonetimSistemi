
// YoneticiEkrani.java
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSplitPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class YoneticiEkrani extends JFrame {
    private SehirYoneticisi yonetici;

    public YoneticiEkrani(SehirYoneticisi yonetici) {
        this.yonetici = yonetici;
        setTitle("Yönetici Paneli - " + yonetici.getIsim());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        JButton varlikEkleBtn = new JButton("Varlık Ekle");
        JButton varlikGuncelleBtn = new JButton("Varlık Güncelle");
        JButton hizmetIzleBtn = new JButton("Hizmetleri İzle");
        JButton binaDurumBtn = new JButton("Bina Durumları");
        JButton raporOlusturBtn = new JButton("Rapor Oluştur");
        JButton sorunCozBtn = new JButton("Sorun Çöz");
        JButton cikisBtn = new JButton("Çıkış Yap");

        // Buton işlevleri
        varlikEkleBtn.addActionListener(e -> varlikEkleDialog());
        varlikGuncelleBtn.addActionListener(e -> varlikGuncelleDialog());
        hizmetIzleBtn.addActionListener(e -> sonucGoster(yonetici.hizmetleriIzle()));
        raporOlusturBtn.addActionListener(e -> sonucGoster(yonetici.raporOlustur()));
        sorunCozBtn.addActionListener(e -> sorunCozDialog());
        cikisBtn.addActionListener(e -> cikisYap());
        binaDurumBtn.addActionListener(e -> binaDurumlariniGoster());

        panel.add(varlikEkleBtn);
        panel.add(varlikGuncelleBtn);
        panel.add(hizmetIzleBtn);
        panel.add(binaDurumBtn);
        panel.add(raporOlusturBtn);
        panel.add(sorunCozBtn);
        panel.add(cikisBtn);

        add(panel);
    }

    private void varlikEkleDialog() {
        JDialog dialog = new JDialog(this, "Varlık Ekle", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        String[] varlikTipleri = { "Arac", "Bina", "EnerjiKaynagi" };
        JComboBox<String> tipComboBox = new JComboBox<>(varlikTipleri);

        JTextField idField = new JTextField();
        JTextField turField = new JTextField();
        JTextField kapasiteField = new JTextField();
        JTextField degerField = new JTextField(); // sarjSeviyesi/katSayisi/enerjiVerimliligi

        dialog.add(new JLabel("Varlık Tipi:"));
        dialog.add(tipComboBox);
        dialog.add(new JLabel("ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Tür:"));
        dialog.add(turField);
        dialog.add(new JLabel("Kapasite:"));
        dialog.add(kapasiteField);
        dialog.add(new JLabel("Ek Değer:"));
        dialog.add(degerField);

        JButton ekleButton = new JButton("Ekle");
        ekleButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String tur = turField.getText();
                int kapasite = Integer.parseInt(kapasiteField.getText());
                double deger = Double.parseDouble(degerField.getText());

                yonetici.varlikEkle((String) tipComboBox.getSelectedItem(),
                        id, tur, kapasite, deger);

                sonucGoster("Varlık başarıyla eklendi!");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                sonucGoster("Lütfen geçerli sayısal değerler girin!");
            }
        });

        dialog.add(ekleButton);
        dialog.setVisible(true);
    }

    private void sonucGoster(String mesaj) {
        JDialog sonucDialog = new JDialog(this, "Sonuç", true);
        sonucDialog.setLayout(new BorderLayout());
        sonucDialog.setSize(600, 400);
        sonucDialog.setLocationRelativeTo(this);

        JTextArea sonucArea = new JTextArea(mesaj);
        sonucArea.setEditable(false);
        sonucArea.setWrapStyleWord(true);
        sonucArea.setLineWrap(true);
        sonucArea.setMargin(new Insets(10, 10, 10, 10));

        JButton kapatBtn = new JButton("Kapat");
        kapatBtn.addActionListener(e -> sonucDialog.dispose());

        sonucDialog.add(new JScrollPane(sonucArea), BorderLayout.CENTER);
        sonucDialog.add(kapatBtn, BorderLayout.SOUTH);
        sonucDialog.setVisible(true);
    }

    private void varlikGuncelleDialog() {
        JDialog dialog = new JDialog(this, "Varlık Güncelle", true);
        dialog.setLayout(new GridLayout(0, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        String[] varlikTipleri = { "Arac", "Bina", "EnerjiKaynagi" };
        JComboBox<String> tipComboBox = new JComboBox<>(varlikTipleri);

        JTextField idField = new JTextField();
        JTextField yeniTurField = new JTextField();
        JTextField yeniKapasiteField = new JTextField();
        JTextField yeniDegerField = new JTextField();

        dialog.add(new JLabel("Varlık Tipi:"));
        dialog.add(tipComboBox);
        dialog.add(new JLabel("Güncellenecek ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Yeni Tür:"));
        dialog.add(yeniTurField);
        dialog.add(new JLabel("Yeni Kapasite:"));
        dialog.add(yeniKapasiteField);
        dialog.add(new JLabel("Yeni Ek Değer:"));
        dialog.add(yeniDegerField);

        JButton guncelleButton = new JButton("Güncelle");
        guncelleButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String yeniTur = yeniTurField.getText();
                int yeniKapasite = Integer.parseInt(yeniKapasiteField.getText());
                double yeniDeger = Double.parseDouble(yeniDegerField.getText());

                String sonuc = yonetici.varlikGuncelle(
                        (String) tipComboBox.getSelectedItem(),
                        id,
                        yeniTur,
                        yeniKapasite,
                        yeniDeger);

                sonucGoster(sonuc);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                sonucGoster("Lütfen geçerli sayısal değerler girin!");
            }
        });

        dialog.add(guncelleButton);
        dialog.setVisible(true);
    }

    private void sorunCozDialog() {
        JDialog dialog = new JDialog(this, "Sorun Çözme", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);

        // Sol panel - Sorun listesi
        JPanel sorunlarPanel = new JPanel();
        sorunlarPanel.setLayout(new BoxLayout(sorunlarPanel, BoxLayout.Y_AXIS)); // Y_AXIS kullanarak dikey düzen
        sorunlarPanel.setBorder(BorderFactory.createTitledBorder("Mevcut Sorunlar"));

        // Sorunları kontrol et ve listele
        List<String> sorunlar = new ArrayList<>();

        // Trafik sorunları
        TrafikYonetimi trafikYonetimi = new TrafikYonetimi(1);
        if (trafikYonetimi.getTrafikYogunlugu() > 0.8) {
            sorunlar.add("Yüksek Trafik Yoğunluğu");
        }

        // Su sorunları
        SuYonetimi suYonetimi = new SuYonetimi(1, 1000.0);
        if (suYonetimi.getSuRezervi() < 1000.0) {
            sorunlar.add("Düşük Su Rezervi");
        }

        // Enerji sorunları
        EnerjiYonetimi enerjiYonetimi = new EnerjiYonetimi(1);
        if (enerjiYonetimi.getToplamUretim() < enerjiYonetimi.getToplamTuketim()) {
            sorunlar.add("Yetersiz Enerji Üretimi");
        }

        // Araç sorunları
        for (Arac arac : Arac.getAracListesi()) {
            if (arac.isElektrikli() && arac.getSarjSeviyesi() < 20) {
                sorunlar.add("Kritik Şarj Seviyesi - Araç ID: " + arac.getAracID());
            }
        }

        // Bina sorunları
        for (Bina bina : Bina.getBinaListesi()) {
            if (bina.isAkilli() && bina.getEnerjiVerimliligi() < 90) {
                sorunlar.add("Düşük Enerji Verimliliği - Bina ID: " + bina.getBinaID());
            }
        }

        ButtonGroup group = new ButtonGroup();
        for (String sorun : sorunlar) {
            JRadioButton radioButton = new JRadioButton(sorun);
            radioButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Sola hizala
            group.add(radioButton);
            sorunlarPanel.add(radioButton);
            sorunlarPanel.add(Box.createVerticalStrut(5)); // Düğmeler arası boşluk
        }

        // Sağ panel - Çözüm detayları
        JTextArea cozumDetay = new JTextArea();
        cozumDetay.setEditable(false);
        cozumDetay.setWrapStyleWord(true);
        cozumDetay.setLineWrap(true);
        cozumDetay.setMargin(new Insets(10, 10, 10, 10));

        // Sorun seçildiğinde çözüm önerilerini göster
        for (Component comp : sorunlarPanel.getComponents()) {
            if (comp instanceof JRadioButton) {
                JRadioButton radio = (JRadioButton) comp;
                radio.addActionListener(e -> {
                    String secilenSorun = radio.getText();
                    String cozumOnerisi = getCozumOnerisi(secilenSorun);
                    cozumDetay.setText(cozumOnerisi);
                });
            }
        }

        // Alt panel - Butonlar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cozButton = new JButton("Çöz");
        JButton iptalButton = new JButton("İptal");

        cozButton.addActionListener(e -> {
            String secilenSorun = null;
            for (Component comp : sorunlarPanel.getComponents()) {
                if (comp instanceof JRadioButton) {
                    JRadioButton radio = (JRadioButton) comp;
                    if (radio.isSelected()) {
                        secilenSorun = radio.getText();
                        break;
                    }
                }
            }

            if (secilenSorun != null) {
                // Seçilen soruna göre çözüm uygula
                if (secilenSorun.contains("Trafik Yoğunluğu")) {
                    trafikYonetimi.trafikYogunluguGuncelle(0.5);
                } else if (secilenSorun.contains("Su Rezervi")) {
                    suYonetimi.rezervGuncelle(2000.0);
                } else if (secilenSorun.contains("Enerji Üretimi")) {
                    enerjiYonetimi.uretimTuketimGuncelle();
                } else if (secilenSorun.contains("Şarj Seviyesi")) {
                    int aracId = Integer.parseInt(secilenSorun.split("ID: ")[1]);
                    for (Arac arac : Arac.getAracListesi()) {
                        if (arac.getAracID() == aracId) {
                            arac.sarjSeviyesiGuncelle(100.0);
                            break;
                        }
                    }
                } else if (secilenSorun.contains("Enerji Verimliliği")) {
                    int binaId = Integer.parseInt(secilenSorun.split("ID: ")[1]);
                    for (Bina bina : Bina.getBinaListesi()) {
                        if (bina.getBinaID() == binaId) {
                            if (bina.isAkilli()) {
                                bina.enerjiTuketiminiOptimizeEt();
                                bina.setEnerjiVerimliligi(95.0);
                            } else {
                                bina.setEnerjiVerimliligi(75.0);
                            }
                            break;
                        }
                    }
                }

                // Çözüm sonucunu göster
                String cozumRaporu = "Sorun başarıyla çözüldü!\n\n" + getCozumOnerisi(secilenSorun);

                // Güncel durumu göster
                if (secilenSorun.contains("Enerji Verimliliği")) {
                    cozumRaporu += "\n\n=== Güncel Bina Durumu ===\n";
                    for (Bina bina : Bina.getBinaListesi()) {
                        if (bina.getBinaID() == Integer.parseInt(secilenSorun.split("ID: ")[1])) {
                            cozumRaporu += "\nBina ID: " + bina.getBinaID() +
                                    "\nTür: " + bina.getTur() +
                                    "\nEnerji Verimliliği: %" + String.format("%.1f", bina.getEnerjiVerimliligi()) +
                                    "\nTasarruf Modu: " + (bina.isTasarrufModu() ? "Aktif" : "Pasif") +
                                    "\nDurum: "
                                    + (bina.getEnerjiVerimliligi() >= 90 ? "Optimal çalışıyor" : "İyileştirme yapıldı");
                            break;
                        }
                    }
                }

                sonucGoster(cozumRaporu);
                dialog.dispose();
            } else {
                sonucGoster("Lütfen bir sorun seçin!");
            }
        });

        iptalButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(cozButton);
        buttonPanel.add(iptalButton);

        // Panel yerleşimi
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(sorunlarPanel),
                new JScrollPane(cozumDetay));
        splitPane.setDividerLocation(250);

        dialog.add(splitPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private String getCozumOnerisi(String sorun) {
        if (sorun.contains("Trafik Yoğunluğu")) {
            return "=== Trafik Yoğunluğu Çözüm Planı ===\n\n" +
                    "1. Alternatif güzergahlar aktifleştirilecek\n" +
                    "2. Toplu taşıma seferleri artırılacak\n" +
                    "3. Akıllı trafik ışıkları optimize edilecek\n" +
                    "4. Mobil uygulama üzerinden sürücüler yönlendirilecek";
        } else if (sorun.contains("Su Rezervi")) {
            return "=== Su Tasarrufu Eylem Planı ===\n\n" +
                    "1. Bahçe sulama kısıtlaması uygulanacak\n" +
                    "2. Endüstriyel su kullanımı optimize edilecek\n" +
                    "3. Su kaçakları kontrol edilecek\n" +
                    "4. Vatandaşlar tasarruf konusunda bilgilendirilecek";
        } else if (sorun.contains("Enerji Üretimi")) {
            return "=== Enerji Yönetimi Çözüm Planı ===\n\n" +
                    "1. Yedek enerji kaynakları devreye alınacak\n" +
                    "2. Kritik olmayan tesislerde tüketim kısıtlanacak\n" +
                    "3. Güneş ve rüzgar santralleri kapasitesi artırılacak\n" +
                    "4. Enerji depolama sistemleri optimize edilecek";
        } else if (sorun.contains("Şarj Seviyesi")) {
            return "=== Elektrikli Araç Yönetim Planı ===\n\n" +
                    "1. En yakın şarj istasyonuna yönlendirilecek\n" +
                    "2. Mobil şarj ünitesi gönderilecek\n" +
                    "3. Rota optimizasyonu yapılacak\n" +
                    "4. Yedek araç tahsis edilecek";
        } else if (sorun.contains("Enerji Verimliliği")) {
            return "=== Bina Optimizasyon Planı ===\n\n" +
                    "1. Akıllı sensör sistemleri kontrol edilecek\n" +
                    "2. İklimlendirme sistemleri optimize edilecek\n" +
                    "3. Aydınlatma sistemleri eko moda alınacak\n" +
                    "4. Boş alan sensörleri aktifleştirilecek\n" +
                    "5. Tasarruf modu devreye alınacak\n" +
                    "6. Güneş panelleri maksimum verime ayarlanacak";
        }
        return "Bu sorun için henüz tanımlı çözüm önerisi bulunmuyor.";
    }

    private void cikisYap() {
        yonetici.cikisYap();
        new GirisEkrani().setVisible(true);
        this.dispose();
    }

    private void binaDurumlariniGoster() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("=== Bina Durum Raporu ===\n\n");

        for (Bina bina : Bina.getBinaListesi()) {
            rapor.append("Bina ID: ").append(bina.getBinaID()).append("\n");
            rapor.append("Tür: ").append(bina.getTur())
                    .append(" (").append(bina.isAkilli() ? "Akıllı" : "Geleneksel").append(")\n");
            rapor.append("Kat Sayısı: ").append(bina.getKatSayisi()).append("\n");
            rapor.append("Enerji Verimliliği: %").append(String.format("%.1f", bina.getEnerjiVerimliligi()))
                    .append("\n");
            rapor.append("Tasarruf Modu: ").append(bina.isTasarrufModu() ? "Aktif" : "Pasif").append("\n");

            if (bina.isAkilli()) {
                if (bina.getEnerjiVerimliligi() < 90) {
                    rapor.append("DURUM: Optimizasyon gerekli\n");
                } else {
                    rapor.append("DURUM: Optimal çalışıyor\n");
                }
            } else {
                if (bina.getEnerjiVerimliligi() < 75) {
                    rapor.append("DURUM: İyileştirme gerekli\n");
                } else {
                    rapor.append("DURUM: Normal çalışıyor\n");
                }
            }
            rapor.append("\n------------------------\n\n");
        }

        sonucGoster(rapor.toString());
    }
}