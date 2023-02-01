
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Bilet;
import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
import com.havaalani.Main;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AdminUçuşEkleSayfasıFXMLController implements Initializable {

    @FXML private TextField süreTxtField;
    @FXML private TextField hedefTxtField;
    @FXML private TextField idTxtField;
    @FXML private TextField başlangıçTxtField;
    @FXML private TextField zamanTxtField;
    @FXML private TextField tarihTxtField;
    @FXML private Button iptalBtn;
    @FXML private Button ekleBtn;
    @FXML private TextField ücretTxtField;
    @FXML private TextField cezaTxtField;
    @FXML private Label uyariLbl;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        uyariLbl.setText("");


        iptalBtn.setOnAction(e -> {
            ((Stage) iptalBtn.getScene().getWindow()).close();

            AdminUçuşlarSayfasıFXMLController.stageEkleAçıkMı = false;
        });

        ekleBtn.setOnAction( e -> ekleBtnAction(e));

    }    
    
    private void ekleBtnAction(ActionEvent e)
    {
        if (idTxtField.getText().isEmpty() || başlangıçTxtField.getText().isEmpty() || hedefTxtField.getText().isEmpty() || zamanTxtField.getText().isEmpty()
            || süreTxtField.getText().isEmpty() || tarihTxtField.getText().isEmpty() ||ücretTxtField.getText().isEmpty() ||cezaTxtField.getText().isEmpty() )
        {
            uyariLbl.setText("Tüm alanları doldurun!!!");
        }
//        else if (!idTxtField.getText().matches("[0-9]+") ||
//                !hedefTxtField.getText().matches("[a-z]+") ||
//                !başlangıçTxtField.getText().matches("[a-z]+") ||
//                !tarihTxtField.getText().matches("\\d\\d/\\d\\d/\\d\\d")||
//                !zamanTxtField.getText().matches("[0-2][0-9]:[0-6][0-9]")||
//                !ücretTxtField.getText().matches("[1-9][0-9]*") ||
//                !fineTxtField.getText().matches("[0-9]+%"))
//        {
//            uyariLbl.setText("Geçersiz format!!!");
//        }
//        else if (!zamanKontrol()|| !tarihKontrol())
//        {
//            uyariLbl.setText("Geçersiz tarih ve zaman!!!");
//        }
        else if (müdaheleKontrol())
        {
            uyariLbl.setText("Diğer uçuşlara müdahale eder!!!");
        }
        else
        {
            uyariLbl.setText("");

            AdminUçuşlarSayfasıFXMLController.stageEkleAçıkMı = false;

            String id = "";
            id += Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getId();
            id += "-";
            id += idTxtField.getText();

            Bilet yeniBilet = new Bilet(id , ücretTxtField.getText() , cezaTxtField.getText());
            
            Main.biletler.add(yeniBilet);
            
            YazmaOkumaFile<Bilet> biletYazmaOkumaFile = new YazmaOkumaFile<>(Main.biletler,"Biletler.txt");
            biletYazmaOkumaFile.writeList();


            Uçuş yeniUçuş = new Uçuş(id , Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex) ,
                    yeniBilet , başlangıçTxtField.getText() , hedefTxtField.getText() , tarihTxtField.getText() , süreTxtField.getText(),
                    zamanTxtField.getText());
            
            Main.uçuşlar.add(yeniUçuş);
            
            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar,"Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().add(yeniUçuş);
            
            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar,"Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();


            AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.getItems().clear();

            for (int i = 0; i< Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().size() ; i++)
            {
                AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.getItems().add(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(i));
            }

            AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.refresh();

            ((Stage) ekleBtn.getScene().getWindow()).close();
            AdminUçuşlarSayfasıFXMLController.stageEkleAçıkMı = false;

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminUçuşEkle(yeniUçuş);
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiUçuşEkle(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex) , yeniUçuş);
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.çalışanUçuşEkle(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex) ,yeniUçuş);
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }

        }
    }
    
    private boolean zamanKontrol()
    {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
        String nowString = dtf.format(now);
        String[] date_clockNow = nowString.split(" ");

        String[] şuankiTarihArray = date_clockNow[0].split("/");
        String[] şuankiSaatArray = date_clockNow[1].split(":");
        
        int şuankiYıl = Integer.parseInt(şuankiTarihArray[0])%1000;
        int şuankiAy = Integer.parseInt(şuankiTarihArray[1]);
        int şuankiGün = Integer.parseInt(şuankiTarihArray[2]);

        int şuankiSaat = Integer.parseInt(şuankiSaatArray[0]);
        int şuankiDakika = Integer.parseInt(şuankiSaatArray[1]);

        

        String[] tarih = tarihTxtField.getText().split("/");
        String[] saatArray = zamanTxtField.getText().split(":");
        
        int yıl = Integer.parseInt(tarih[0]);
        int ay = Integer.parseInt(tarih[1]);
        int gün = Integer.parseInt(tarih[2]);

        int saat = Integer.parseInt(saatArray[0]);
        int dakika = Integer.parseInt(saatArray[1]);

        if (şuankiYıl>yıl)
        {
            return false;
        }else if (yıl==şuankiYıl)
        {
            if (şuankiAy>ay)
            {
                return false;
            }else if (şuankiAy==ay)
            {
                if (gün<şuankiGün)
                {
                    return false;
                }else if (gün==şuankiGün)
                {
                    if(saat<şuankiSaat)
                    {
                        return false;
                    }else if (saat==şuankiSaat)
                    {
                        if (dakika<=şuankiDakika)
                        {
                            return false;
                        }
                    }
                }

            }
        }
        return true;
    }
    
    private boolean müdaheleKontrol()
    {
        for (int i=0 ; i<Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().size() ; i++)
        {
            if ( ! Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(i).getTarih()
                    .equals(tarihTxtField.getText()))
            {
                return false;
            }
            else
            {
                if ( ! Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(i).getKalkisZamani()
                        .equals(zamanTxtField.getText()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean tarihKontrol()
    {
        String[] tarihArray = tarihTxtField.getText().split("/");
        String[] saatArray = zamanTxtField.getText().split(":");
        
        int ay = Integer.parseInt(tarihArray[1]);
        int gün = Integer.parseInt(tarihArray[2]);

        int saat = Integer.parseInt(saatArray[0]);
        int dakika = Integer.parseInt(saatArray[1]);

        if (!(ay>=1 && ay<=12) || ! (gün>=1 && gün<=31))
        {
            return false;
        }
        if (!(saat>=0 && saat<=23) || !(dakika>=0 && dakika<=59))
        {
            return false;
        }
        return true;
    }
}
