
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Kullanicilar.Kişi;
import com.havaalani.Kullanicilar.Yönetici;
import com.havaalani.Kullanicilar.Çalışan;
import com.havaalani.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class AdminProfilDüzenleSayfasıFXMLController implements Initializable {

    @FXML private Label uyariEmailLbl;
    @FXML private Button editEmailBtn;
    @FXML private Label emailLbl;
    @FXML private TextField yeniMailTxtField;
    @FXML private Label kullaniciAdiLbl;
    @FXML private Label uyariŞifreLbl;
    @FXML private Button editŞifreBtn;
    @FXML private PasswordField eskiŞifreTxtField;
    @FXML private PasswordField yeniŞifreTxtField;
    @FXML private Label soyİsimLbl;
    @FXML private Label isimLbl;
    @FXML private Label idLbl;
    @FXML private Button editTelefonBtn;
    @FXML private TextField yeniTelefonTxtField;
    @FXML private Label telefonLbl;
    @FXML private Label uyariTelefonLbl;
    @FXML private Button kapatBtn;
    @FXML private Label uyariAdresLbl;
    @FXML private Button editAdresBtn;
    @FXML private Label adresLbl;
    @FXML private TextField yeniAdresTxtField;
    @FXML private Label maasLbl;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uyariŞifreLbl.setText("");
        uyariEmailLbl.setText("");
        uyariTelefonLbl.setText("");
        uyariAdresLbl.setText("");

        
        Kişi kişi = null;
        if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
        {
            kişi = Main.Admin;
            adresLbl.setText(Main.Admin.getAdres());
            maasLbl.setText(Main.Admin.getMaas());
        }
        else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
        {
            kişi = Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex);
            adresLbl.setText(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).getAdres());
            maasLbl.setText(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).getMaas());
        }
        else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Çalışan)
        {
            kişi = Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex);
            adresLbl.setText(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).getAdres());
            maasLbl.setText(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).getMaas());
        }

        isimLbl.setText(kişi.getIsim());
        soyİsimLbl.setText(kişi.getSoyİsim());
        kullaniciAdiLbl.setText(kişi.getKullaniciAdi());
        idLbl.setText(kişi.getId());
        emailLbl.setText(kişi.getMail());
        telefonLbl.setText(kişi.getTelefonNo());



        
        kapatBtn.setOnAction(e -> {
            
            BölmedenSekmeyiKaldir bölmedenSekmeyiKaldir = new BölmedenSekmeyiKaldir();
            int index = AdminSayfasıFXMLController.sahteAdminSayfasıTP.getTabs().indexOf(AdminSayfasıFXMLController.profilTab);
            bölmedenSekmeyiKaldir.sekmeKaldır(AdminSayfasıFXMLController.sahteAdminSayfasıTP, index);

            AdminSayfasıFXMLController.profilTabAçıkMı = false;
        });

        editŞifreBtn.setOnAction(e -> {
            try {
                editŞifreBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        editEmailBtn.setOnAction(e -> {
            try {
                editEmailBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        editTelefonBtn.setOnAction(e -> {
            try {
                editTelefonBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        editAdresBtn.setOnAction(e -> {
            try {
                editAdresBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    private void editŞifreBtnAction(ActionEvent e) throws IOException {

        Kişi kişi;
        if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
        {
             kişi=Main.Admin;
        }
        else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
        {
            kişi = Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex);
        }
        else
        {
            kişi = Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex);
        }


        if (eskiŞifreTxtField.getText().isEmpty() || yeniŞifreTxtField.getText().isEmpty())
        {
            uyariŞifreLbl.setText("Her iki alanı da doldurun!!!");
        }
        else if (!eskiŞifreTxtField.getText().equals(kişi.getSifre()))
        {
            uyariŞifreLbl.setText("Şifre doğru değil!!!");
        }
        else if(yeniŞifreTxtField.getText().equals(kişi.getSifre()))
        {
            uyariŞifreLbl.setText("Bu yeni bir şifre değil!!!");
        }
        else
        {
            uyariŞifreLbl.setText("Yeni şifre oluşturuldu!");

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                
                Main.Admin.setSifre(yeniŞifreTxtField.getText());

                YazmaOkumaFile<Yönetici> adminYazmaOkumaFile = new YazmaOkumaFile<>(Main.Admin , "Admin.txt");
                adminYazmaOkumaFile.yaz();

                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminProfilDüzenle();
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                
                Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).setSifre(yeniŞifreTxtField.getText());

                
                YazmaOkumaFile<Yönetici> yöneticiYazmaOkumaFile = new YazmaOkumaFile<>(Main.yöneticiler, "Yöneticiler.txt");
                yöneticiYazmaOkumaFile.writeList();

                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiProfilDüzenle(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();

            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Çalışan)
            {
                Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).setSifre(yeniŞifreTxtField.getText());

                
                YazmaOkumaFile<Çalışan> çalışanYazmaOkumaFile = new YazmaOkumaFile<>(Main.çalışanlar, "Çalışanlar.txt");
                çalışanYazmaOkumaFile.writeList();

                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.çalışanProfilDüzenle(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            yeniŞifreTxtField.setText("");
            eskiŞifreTxtField.setText("");
        }
    }
    
    private void editEmailBtnAction(ActionEvent e) throws IOException {

        Kişi kişi;
        if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
        {
            kişi = Main.Admin;
        }
        else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
        {
            kişi = Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex);
        }
        else
        {
            kişi = Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex);
        }

        if (yeniMailTxtField.getText().isEmpty())
        {
            uyariEmailLbl.setText("Alanı doldurun!!!");
        }
        else if (!(yeniMailTxtField.getText().matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")))
        {
            uyariEmailLbl.setText("Geçersiz format!!!");
        }
        else if(yeniMailTxtField.getText().equals(kişi.getMail()))
        {
            uyariEmailLbl.setText("Bu yeni bir Email adresi değil!!!");
        }
        else if (!ayniHesapKontrol())
        {
            uyariEmailLbl.setText("Bu bilgilere sahip bir kullanıcı zaten var!!!");
        }
        else
        {
            uyariEmailLbl.setText("Yeni email oluşturuldu!");


            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                
                Main.Admin.setMail(yeniMailTxtField.getText());

                emailLbl.setText(Main.Admin.getMail());
                
                YazmaOkumaFile<Yönetici> adminYazmaOkumaFile = new YazmaOkumaFile<>(Main.Admin , "Admin.txt");
                adminYazmaOkumaFile.yaz();

                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminProfilDüzenle();
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                
                Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).setMail(yeniMailTxtField.getText());

                emailLbl.setText(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).getMail());

                YazmaOkumaFile<Yönetici> yöneticiYazmaOkumaFile = new YazmaOkumaFile<>(Main.yöneticiler, "Yöneticiler.txt");
                yöneticiYazmaOkumaFile.writeList();

                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiProfilDüzenle(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Çalışan)
            {
                Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).setMail(yeniMailTxtField.getText());

                emailLbl.setText(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).getMail());

                YazmaOkumaFile<Çalışan> çalışanYazmaOkumaFile = new YazmaOkumaFile<>(Main.çalışanlar, "Çalışanlar.txt");
                çalışanYazmaOkumaFile.writeList();

                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.çalışanProfilDüzenle(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            yeniMailTxtField.setText("");
        }
    }
    
    private void editTelefonBtnAction(ActionEvent e) throws IOException {


        Kişi kişi;
        if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
        {
            kişi = Main.Admin;
        }
        else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
        {
            kişi = Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex);
        }
        else
        {
            kişi = Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex);
        }

        if (yeniTelefonTxtField.getText().isEmpty())
        {
            uyariTelefonLbl.setText("Alanı doldur!!!");
        }
        else if ((yeniTelefonTxtField.getText().length() !=11) || !(yeniTelefonTxtField.getText().substring(0 , 2).equals("05")) || !(yeniTelefonTxtField.getText().matches("[0-9]+")))
        {
            uyariTelefonLbl.setText("Geçersiz format!!!");
        }
        else if(yeniTelefonTxtField.getText().equals(kişi.getTelefonNo()))
        {
            uyariTelefonLbl.setText("Bu yeni bir telefon numarası değil!!!");
        }
        else if (!ayniHesapKontrol())
        {
            uyariTelefonLbl.setText("Bu bilgilere sahip bir kullanıcı zaten var!!!");
        }
        else
        {
            uyariTelefonLbl.setText("Yeni telefon numarası düzenlendi!");

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                
                Main.Admin.setTelefonNo(yeniTelefonTxtField.getText());

                telefonLbl.setText(Main.Admin.getTelefonNo());
                
                YazmaOkumaFile<Yönetici> adminYazmaOkumaFile = new YazmaOkumaFile<>(Main.Admin , "Admin.txt");
                adminYazmaOkumaFile.yaz();

                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminProfilDüzenle();
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                
                Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).setTelefonNo(yeniTelefonTxtField.getText());

                telefonLbl.setText(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo());

                YazmaOkumaFile<Yönetici> yöneticiYazmaOkumaFile = new YazmaOkumaFile<>(Main.yöneticiler, "Yöneticiler.txt");
                yöneticiYazmaOkumaFile.writeList();

                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiProfilDüzenle(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Çalışan)
            {
                Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).setTelefonNo(yeniTelefonTxtField.getText());

                telefonLbl.setText(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo());

                YazmaOkumaFile<Çalışan> çalışanYazmaOkumaFile = new YazmaOkumaFile<>(Main.çalışanlar, "Çalışanlar.txt");
                çalışanYazmaOkumaFile.writeList();

                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.çalışanProfilDüzenle(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            yeniTelefonTxtField.setText("");
        }
    }

    private void editAdresBtnAction(ActionEvent e) throws IOException {

        String adres;
        if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
        {
            adres = Main.Admin.getAdres();
        }
        else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
        {
            adres = Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).getAdres();
        }
        else
        {
            adres = Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).getAdres();
        }
        if (yeniAdresTxtField.getText().isEmpty())
        {
            uyariAdresLbl.setText("Alanı doldurun!!!");
        }
        else if (adres.equals(yeniAdresTxtField.getText()))
        {
            uyariAdresLbl.setText("Bu yeni bir e-posta adresi değil!!!");
        }
        else
        {
            uyariAdresLbl.setText("Yeni adres düzenlendi!!!");

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                
                Main.Admin.setAdres(yeniAdresTxtField.getText());

                adresLbl.setText(Main.Admin.getAdres());

                YazmaOkumaFile<Yönetici> adminYazmaOkumaFile = new YazmaOkumaFile<>(Main.Admin , "Admin.txt");
                adminYazmaOkumaFile.yaz();

                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminProfilDüzenle();
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                
                Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).setAdres(yeniAdresTxtField.getText());

                adresLbl.setText(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex).getAdres());

                YazmaOkumaFile<Yönetici> yöneticiYazmaOkumaFile = new YazmaOkumaFile<>(Main.yöneticiler, "Yöneticiler.txt");
                yöneticiYazmaOkumaFile.writeList();

                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiProfilDüzenle(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();

            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Çalışan)
            {
                Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).setAdres(yeniAdresTxtField.getText());

                adresLbl.setText(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex).getAdres());

                YazmaOkumaFile<Çalışan> çalışanYazmaOkumaFile = new YazmaOkumaFile<>(Main.çalışanlar, "Çalışanlar.txt");
                çalışanYazmaOkumaFile.writeList();

                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.çalışanProfilDüzenle(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            yeniAdresTxtField.setText("");
        }
    }
    
    private boolean ayniHesapKontrol()
    {
        
        if (!yeniMailTxtField.getText().equals(null))
        {

            for (int i=0 ; i<Main.yolcular.size() ; i++)
            {
                if (yeniMailTxtField.getText().toLowerCase().equals(Main.yolcular.get(i).getMail().toLowerCase()))
                {
                    return false;
                }
            }
            for (int i=0 ; i<Main.yöneticiler.size() ; i++)
            {
                if (yeniMailTxtField.getText().toLowerCase().equals(Main.yöneticiler.get(i).getMail().toLowerCase()))
                {
                    if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
                    {
                        if (i!=GirişSayfasıFXMLController.kullaniciİndex)
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }

                }
            }
            for (int i=0 ; i<Main.çalışanlar.size() ; i++ )
            {
                if (yeniMailTxtField.getText().toLowerCase().equals(Main.çalışanlar.get(i).getMail().toLowerCase()) )
                {
                    if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Çalışan)
                    {
                        if (i!=GirişSayfasıFXMLController.kullaniciİndex)
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            if (GirişSayfasıFXMLController.pozisyon != GirişSayfasıFXMLController.pozisyon.Admin)
            {
                if (yeniMailTxtField.getText().toLowerCase().equals(Main.Admin.getMail().toLowerCase()))
                {
                    return false;
                }
            }
        }


        if (!yeniTelefonTxtField.getText().equals(null))
        {
            for (int i=0 ; i<Main.yolcular.size() ; i++)
            {
                if (yeniTelefonTxtField.getText().equals(Main.yolcular.get(i).getTelefonNo()))
                {
                    return false;
                }
            }
            for (int i=0 ; i<Main.yöneticiler.size() ; i++)
            {
                if (yeniTelefonTxtField.getText().equals(Main.yöneticiler.get(i).getTelefonNo()))
                {
                    if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
                    {
                        if (i != GirişSayfasıFXMLController.kullaniciİndex)
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            for (int i=0 ; i<Main.çalışanlar.size() ; i++)
            {
                if (yeniTelefonTxtField.getText().equals(Main.çalışanlar.get(i).getTelefonNo())  )
                {
                    if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Çalışan)
                    {
                        if (i != GirişSayfasıFXMLController.kullaniciİndex)
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            if (GirişSayfasıFXMLController.pozisyon!= GirişSayfasıFXMLController.pozisyon.Admin)
            {
                if (yeniTelefonTxtField.getText().equals(Main.Admin.getTelefonNo()))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
