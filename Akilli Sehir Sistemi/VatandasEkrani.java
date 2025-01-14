
// VatandasEkrani.java
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class VatandasEkrani extends JFrame {
    private Vatandas vatandas;

    public VatandasEkrani(Vatandas vatandas) {
        this.vatandas = vatandas;
        setTitle("Vatandaş Paneli - " + vatandas.getIsim());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton trafikBtn = new JButton("Trafik Bilgisi Görüntüle");
        JButton enerjiBtn = new JButton("Enerji Raporu Görüntüle");
        JButton suBtn = new JButton("Su Seviyelerini Takip Et");
        JButton cikisBtn = new JButton("Çıkış Yap");

        trafikBtn.addActionListener(e -> sonucGoster(vatandas.trafikBilgisiGoruntuleRaporu()));
        enerjiBtn.addActionListener(e -> sonucGoster(vatandas.enerjiRaporuGoruntuleRaporu()));
        suBtn.addActionListener(e -> sonucGoster(vatandas.suSeviyeleriniTakipEtRaporu()));
        cikisBtn.addActionListener(e -> cikisYap());

        panel.add(trafikBtn);
        panel.add(enerjiBtn);
        panel.add(suBtn);
        panel.add(cikisBtn);

        add(panel);
    }

    private void cikisYap() {
        vatandas.cikisYap();
        new GirisEkrani().setVisible(true);
        this.dispose();
    }

    private void sonucGoster(String mesaj) {
        JDialog sonucDialog = new JDialog(this, "Rapor", true);
        sonucDialog.setLayout(new BorderLayout());
        sonucDialog.setSize(400, 300);
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
}