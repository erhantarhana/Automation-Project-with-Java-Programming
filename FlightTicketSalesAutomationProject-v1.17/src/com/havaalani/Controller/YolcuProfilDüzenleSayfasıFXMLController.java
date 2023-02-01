
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
import com.havaalani.Kullanicilar.Yolcu;
import com.havaalani.Main;
import java.io.BufferedReader;
import java.io.FileReader;
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


public class YolcuProfilDüzenleSayfasıFXMLController implements Initializable {

    @FXML private Label uyariEmailLbl;
    @FXML private Button editEmailBtn;
    @FXML private Label emailLbl;
    @FXML private TextField yeniEmailTxtField;
    @FXML private Label kullaniciAdiLbl;
    @FXML private Label uyariSifreLbl;
    @FXML private Button editSifreBtn;
    @FXML private PasswordField eskiSifreTxtField;
    @FXML private PasswordField yeniSifreTxtField;
    @FXML private Label soyİsimLbl;
    @FXML private Label isimLbl;
    @FXML private Label idLbl;
    @FXML private Button editTelefonBtn;
    @FXML private TextField yeniTelefonTxtField;
    @FXML private Label telefonLbl;
    @FXML private Label uyariTelefonLbl;
    @FXML private Button kapatBtn;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uyariSifreLbl.setText("");
        uyariEmailLbl.setText("");
        uyariTelefonLbl.setText("");
        
        isimLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim());
        soyİsimLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim());
        kullaniciAdiLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKullaniciAdi());
        idLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId());
        emailLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail());
        telefonLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo());
        
        kapatBtn.setOnAction(e -> {
            
            BölmedenSekmeyiKaldir kaldir =new BölmedenSekmeyiKaldir();
            int index = YolcuSayfasıFXMLController.sahteYolcuPageTP.getTabs().indexOf(YolcuSayfasıFXMLController.profilTab);
            kaldir.sekmeKaldır(YolcuSayfasıFXMLController.sahteYolcuPageTP, index);

            YolcuSayfasıFXMLController.profilTabAçıkMı = false;
        });

        editSifreBtn.setOnAction(e -> {
            try {
                EditSifreBtnAction(e);
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
    }    
    
    private void editTelefonBtnAction(ActionEvent e) throws IOException {
        if (yeniTelefonTxtField.getText().isEmpty())
        {
            uyariTelefonLbl.setText("Tüm alanları doldurun!!!");
        }
        else if ((yeniTelefonTxtField.getText().length() !=11) || !(yeniTelefonTxtField.getText().substring(0 , 2).equals("05")) || !(yeniTelefonTxtField.getText().matches("[0-9]+")))
        {
            uyariTelefonLbl.setText("Geçersiz format girdiniz!!!");
        }
        else if(yeniTelefonTxtField.getText().equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo()))
        {
            uyariTelefonLbl.setText("Bu yeni telefon numarası değil!!!");
        }
        else if (!ayniHesapKontrol())
        {
            uyariEmailLbl.setText("Bu bilgilerle zaten bir kullanıcı var!!!");
        }
        else
        {
            uyariTelefonLbl.setText("Yeni telefon numarası düzenlendi!");

            
//            String eskiSatır=(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKullaniciAdi()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSifre()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail()+" "+
//                    Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
//
//            
//            String yeniSatır=(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKullaniciAdi()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSifre()+" "
//                    +yeniTelefonTxtField.getText()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail()+" "+
//                    Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
//
//            
//            int düzenlenenSatır = 0;
//            FileReader fileReader = new FileReader("Yolcu.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            String satir;
//            while ((satir=bufferedReader.readLine()) != null)
//            {
//                düzenlenenSatır++;
//                if (satir==eskiSatır)
//                {
//                    break;
//                }
//            }
//
//            String file="Yolcu.txt";
//            
//            DosyadaSatırDeğiştirme dosyadaSatırDeğiştirme = new DosyadaSatırDeğiştirme(); 
//
//            dosyadaSatırDeğiştirme.dosyadaSatırDeğiştir(file,yeniSatır,GirişSayfasıFXMLController.kullaniciİndex+1);

            
            Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).setTelefonNo(yeniTelefonTxtField.getText());
            telefonLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo());
            
            YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>(Main.yolcular , "Yolcu.txt");
            yolcuYazmaOkumaFile.writeList();

            for (int i=0 ; i<Main.uçuşlar.size() ; i++)
            {
                for (int j=0 ; j<Main.uçuşlar.get(i).getYolcu().size() ; j++)
                {
                    if (Main.uçuşlar.get(i).getYolcu().get(j).equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex)))
                    {
                        Main.uçuşlar.get(i).getYolcu().get(j).setTelefonNo(yeniTelefonTxtField.getText());
                    }
                }
            }
            
            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            for (int i=0 ; i<Main.uçaklar.size() ; i++)
            {
                for (int j=0 ; j<Main.uçaklar.get(i).getUçuşlar().size() ; j++)
                {
                    for (int k=0 ; k<Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().size() ; k++)
                    {
                        if (Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k)
                                .equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex)))
                        {

                            Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setTelefonNo(yeniTelefonTxtField.getText());
                        }
                    }
                }
            }
            
            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();

            Kayıt kayıt = new Kayıt();
            Main.kayıtlar.add(kayıt);
            kayıt.yolcuProfilDüzenle(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex));
            
            YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
            kayıtYazmaOkumaFile.writeList();

            yeniTelefonTxtField.setText("");
        }
    }
    
    private void EditSifreBtnAction(ActionEvent e) throws IOException {
        if (eskiSifreTxtField.getText().isEmpty() || yeniSifreTxtField.getText().isEmpty())
        {
            uyariSifreLbl.setText("Tüm alanları doldurun!!!");
        }
        else if (!eskiSifreTxtField.getText().equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSifre()))
        {
            uyariSifreLbl.setText("Şifre doğru değil!!!");
        }
        else if(yeniSifreTxtField.getText().equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSifre()))
        {
            uyariSifreLbl.setText("Bu yeni şifre değil!!!");
        }
        else
        {
            uyariSifreLbl.setText("Yeni şifre ayarlandı!");

            
//
//            String yeniSatir = (Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKullaniciAdi()+" "
//                    +yeniSifreTxtField.getText()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail()+" "+
//                    Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
//
//
//            String file="Yolcu.txt";
//            
//            DosyadaSatırDeğiştirme dosyadaSatırDeğiştirme = new DosyadaSatırDeğiştirme();
//
//            dosyadaSatırDeğiştirme.dosyadaSatırDeğiştir(file,yeniSatir,GirişSayfasıFXMLController.kullaniciİndex+1);

            Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).setSifre(yeniSifreTxtField.getText());
            
            YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>(Main.yolcular , "Yolcu.txt");
            yolcuYazmaOkumaFile.writeList();

            for (int i=0 ; i<Main.uçuşlar.size() ; i++)
            {
                for (int j=0 ; j<Main.uçuşlar.get(i).getYolcu().size() ; j++)
                {
                    if (Main.uçuşlar.get(i).getYolcu().get(j).equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex)))
                    {
                        Main.uçuşlar.get(i).getYolcu().get(j).setSifre(yeniSifreTxtField.getText());
                    }
                }
            }
            
            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            for (int i=0 ; i<Main.uçaklar.size() ; i++)
            {
                for (int j=0 ; j<Main.uçaklar.get(i).getUçuşlar().size() ; j++)
                {
                    for (int k=0 ; k<Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().size() ; k++)
                    {
                        if (Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k)
                                .equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex)))
                        {

                            Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setSifre(yeniSifreTxtField.getText());
                        }
                    }
                }
            }
            
            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();

            Kayıt kayıt = new Kayıt();
            Main.kayıtlar.add(kayıt);
            kayıt.yolcuProfilDüzenle(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex));
            
            YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
            kayıtYazmaOkumaFile.writeList();

            yeniSifreTxtField.setText("");
            eskiSifreTxtField.setText("");
        }
    }
    
    private void editEmailBtnAction(ActionEvent e) throws IOException {
        if (yeniEmailTxtField.getText().isEmpty())
        {
            uyariEmailLbl.setText("Tüm alanları doldurun!!!");
        }
        else if (!(yeniEmailTxtField.getText().matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")))
        {
            uyariEmailLbl.setText("Geçersiz format girdiniz!!!");
        }
        else if(yeniEmailTxtField.getText().equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail()))
        {
            uyariEmailLbl.setText("Bu yeni mail adresi değil!!!");
        }
        else if (!ayniHesapKontrol())
        {
            uyariEmailLbl.setText("Bu bilgilerle kayıtlı kullanıcı zaten var!!!");
        }
        else
        {
            uyariEmailLbl.setText("Yeni mail adresi ayarlandı!");

//            String eskiSatir=(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKullaniciAdi()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSifre()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail()+" "+
//                    Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
//
//
//            String yeniSatir=(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKullaniciAdi()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSifre()+" "
//                    +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo()+" "
//                    +yeniEmailTxtField.getText()+" "+
//                    Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
//
//            int düzenlenenSatir = 1;
//            FileReader fileReader = new FileReader("Yolcu.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            String satir;
//            while ((satir=bufferedReader.readLine()) != null)
//            {
//
//                if (satir==eskiSatir)
//                {
//                    break;
//                }
//                düzenlenenSatir++;
//            }
//
//            String file="Yolcu.txt";
//            
//           DosyadaSatırDeğiştirme dosyadaSatırDeğiştirme = new DosyadaSatırDeğiştirme();
//
//           dosyadaSatırDeğiştirme.dosyadaSatırDeğiştir(file,yeniSatir,GirişSayfasıFXMLController.kullaniciİndex+1);

            Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).setMail(yeniEmailTxtField.getText());
            emailLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail());
            
            YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>(Main.yolcular , "Yolcu.txt");
            yolcuYazmaOkumaFile.writeList();

            for (int i=0 ; i<Main.uçuşlar.size() ; i++)
            {
                for (int j=0 ; j<Main.uçuşlar.get(i).getYolcu().size() ; j++)
                {
                    if (Main.uçuşlar.get(i).getYolcu().get(j).equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex)))
                    {
                        Main.uçuşlar.get(i).getYolcu().get(j).setMail(yeniEmailTxtField.getText());
                    }
                }
            }
            
            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            for (int i=0 ; i<Main.uçaklar.size() ; i++)
            {
                for (int j=0 ; j<Main.uçaklar.get(i).getUçuşlar().size() ; j++)
                {
                    for (int k=0 ; k<Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().size() ; k++)
                    {
                        if (Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k)
                                .equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex)))
                        {

                            Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setMail(yeniEmailTxtField.getText());
                        }
                    }
                }
            }
            
            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();

            Kayıt kayıt = new Kayıt();
            Main.kayıtlar.add(kayıt);
            kayıt.yolcuProfilDüzenle(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex));
            
            YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
            kayıtYazmaOkumaFile.writeList();

            yeniEmailTxtField.setText("");
        }
    }
    
    private boolean ayniHesapKontrol()
    {
        
        if (!yeniEmailTxtField.getText().equals(null))
        {
            for (int i=0 ; i<Main.yolcular.size() ; i++)
            {
                if (yeniEmailTxtField.getText().equals(Main.yolcular.get(i).getMail()) && i!=GirişSayfasıFXMLController.kullaniciİndex)
                {
                    return false;
                }
            }
            for (int i=0 ; i<Main.yöneticiler.size() ; i++)
            {
                if (yeniEmailTxtField.getText().equals(Main.yöneticiler.get(i).getMail()))
                {
                    return false;
                }
            }
            for (int i=0 ; i<Main.çalışanlar.size() ; i++ )
            {
                if (yeniEmailTxtField.getText().equals(Main.çalışanlar.get(i).getMail()))
                {
                    return false;
                }
            }
            if (yeniEmailTxtField.getText().equals(Main.Admin.getMail()))
            {
                return false;
            }
        }

        if (!yeniTelefonTxtField.getText().equals(null))
        {
            for (int i=0 ; i<Main.yolcular.size() ; i++ )
            {
                if (yeniTelefonTxtField.getText().equals(Main.yolcular.get(i).getTelefonNo())&& i!=GirişSayfasıFXMLController.kullaniciİndex)
                {
                    return false;
                }
            }
            for (int i=0 ; i<Main.yöneticiler.size() ; i++)
            {
                if (yeniTelefonTxtField.getText().equals(Main.yöneticiler.get(i).getTelefonNo()))
                {
                    return false;
                }
            }
            for (int i=0 ; i<Main.çalışanlar.size() ; i++)
            {
                if (yeniTelefonTxtField.getText().equals(Main.çalışanlar.get(i).getTelefonNo()))
                {
                    return false;
                }
            }
            if (yeniTelefonTxtField.getText().equals(Main.Admin.getTelefonNo()))
            {
                return false;
            }
        }
        return true;
    }
}
