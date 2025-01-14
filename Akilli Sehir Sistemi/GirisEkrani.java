
// GirisEkrani.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GirisEkrani extends JFrame {
    private JTextField kullaniciIDField;
    private JTextField isimField;
    private JButton girisButton;

    public GirisEkrani() {
        setTitle("Akıllı Şehir Sistemi Giriş");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Kullanıcı ID:"));
        kullaniciIDField = new JTextField();
        panel.add(kullaniciIDField);

        panel.add(new JLabel("İsim:"));
        isimField = new JTextField();
        panel.add(isimField);

        girisButton = new JButton("Giriş Yap");
        girisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                girisYap();
            }
        });

        panel.add(new JLabel(""));
        panel.add(girisButton);

        add(panel);
    }

    private void girisYap() {
        String id = kullaniciIDField.getText();
        String isim = isimField.getText();

        if (id.equals("190290") && isim.equalsIgnoreCase("mehmet")) {
            SehirYoneticisi yonetici = new SehirYoneticisi(id, isim);
            new YoneticiEkrani(yonetici).setVisible(true);
            this.dispose();
        } else {
            // Vatandaş girişi
            Vatandas vatandas = new Vatandas(id, isim);
            new VatandasEkrani(vatandas).setVisible(true);
            this.dispose();
        }
    }
}