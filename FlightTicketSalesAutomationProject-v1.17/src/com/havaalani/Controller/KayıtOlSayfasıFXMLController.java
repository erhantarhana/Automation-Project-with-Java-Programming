
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Yolcu;
import com.havaalani.Main;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
import javafx.stage.Stage;


public class KayıtOlSayfasıFXMLController implements Initializable {

    @FXML private TextField idTxtField;
    @FXML private TextField adresTxtField;
    @FXML private Button kayitOlBtn;
    @FXML private Label uyariLbl;
    @FXML private TextField isimTxtField;
    @FXML private TextField krediTxtField;
    @FXML private Button iptalBtn;
    @FXML private TextField mailTxtField;
    @FXML private TextField kullaniciAdiTxtField;
    @FXML private TextField soyİsimTxtField;
    @FXML private TextField telefonNoTxtField;
    @FXML private PasswordField sifreTxtField;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iptalBtn.setOnAction(e -> iptanBtnAction(e));
        
        kayitOlBtn.setOnAction(e -> {
            try {
                kayitOlBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    private void iptanBtnAction(ActionEvent e) {
        ((Stage) iptalBtn.getScene().getWindow()).close();
    }
     
    private void kayitOlBtnAction(ActionEvent e) throws IOException{
        if (isimTxtField.getText().isEmpty() || soyİsimTxtField.getText().isEmpty() || kullaniciAdiTxtField.getText().isEmpty() || sifreTxtField.getText().isEmpty()
            || adresTxtField.getText().isEmpty() || krediTxtField.getText().isEmpty() || mailTxtField.getText().isEmpty() || idTxtField.getText().isEmpty() || telefonNoTxtField.getText().isEmpty()) {
            
            uyariLbl.setText("Lütfen tüm boşlukları doldurun!");
        }
        else{
            if (!boşlukKontrol()) {
                uyariLbl.setText("Lütfen tüm boşlukları kaldırın!");
            }
            else{
                if (!formatKontrol()) {
                    uyariLbl.setText("Lütfen bilgileri uygun formatta doldurun!");
                }
                else{
                    if (!ayniHesapKontrol()) {
                        uyariLbl.setText("Bu bilgilerle kayıtlı bir kullanıcı zaten var!");
                    }
                    else{
                        Yolcu yeniYolcu = new Yolcu(krediTxtField.getText(),idTxtField.getText(),isimTxtField.getText(),soyİsimTxtField.getText(),kullaniciAdiTxtField.getText(),sifreTxtField.getText(),telefonNoTxtField.getText(),mailTxtField.getText());
                        Main.yolcular.add(yeniYolcu);
                        
                        YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>(Main.yolcular , "Yolcu.txt");
                        yolcuYazmaOkumaFile.writeList();
                        
                        ((Stage)kayitOlBtn.getScene().getWindow()).close();
                    }
                }
            }
        }
    }
    
    private boolean boşlukKontrol(){
//         String kontrol1=isimTxtField.getText();
//            kontrol1=kontrol1.trim();
//            String[] tempKontrol1=kontrol1.split(" ");
//            if (tempKontrol1.length>1)
//            {
//                return false;
//            }
//
//
//        
//            String kontrol2=soyİsimTxtField.getText();
//            kontrol2=kontrol2.trim();
//            String[] tempKontrol2=kontrol2.split(" ");
//            if (tempKontrol2.length>1)
//            {
//                return false;
//            }

        
            String kontrol3=kullaniciAdiTxtField.getText();
            kontrol3=kontrol3.trim();
            String[] tempKontrol3=kontrol3.split(" ");
            if (tempKontrol3.length>1)
            {
                return false;
            }

        
            String kontrol4=sifreTxtField.getText();
            kontrol4=kontrol4.trim();
            String[] tempKontrol4=kontrol4.split(" ");
            if (tempKontrol4.length>1)
            {
                return false;
            }

        
            String kontrol5=krediTxtField.getText();
            kontrol5=kontrol5.trim();
            String[] tempKontrol5=kontrol5.split(" ");
            if (tempKontrol5.length>1)
            {
                return false;
            }

        
            String kontrol6=telefonNoTxtField.getText();
            kontrol6=kontrol6.trim();
            String[] tempKontrol6=kontrol6.split(" ");
            if (tempKontrol6.length>1)
            {
                return false;
            }

        
//            String kontrol7=adresTxtField.getText();
//            kontrol7=kontrol7.trim();
//            String[] tempKontrol7=kontrol7.split(" ");
//            if (tempKontrol7.length>1)
//            {
//                return false;
//            }

        
            String kontrol8=mailTxtField.getText();
            kontrol8=kontrol8.trim();
            String[] tempKontrol8=kontrol8.split(" ");
            if (tempKontrol8.length>1)
            {
                return false;
            }

        return true;
    }
    
    private boolean formatKontrol(){
         if (!(isimTxtField.getText().matches("[a-zA-Z]+")) || !(soyİsimTxtField.getText().matches("[a-zA-Z]+")))
        {
            return false;
        }

        if (!(idTxtField.getText().matches("[0-9]+")))
        {
            return false;
        }

        if ( (telefonNoTxtField.getText().length() !=11) || !(telefonNoTxtField.getText().substring(0 , 2).equals("05")) || !(telefonNoTxtField.getText().matches("[0-9]+")))
        {
            return false;
        }

        if (!(mailTxtField.getText().matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")))
        {
            return false;
        }
        if (!(krediTxtField.getText().matches("[0-9]+")))
        {
            return false;
        }

        return true;
    }
    
    private boolean ayniHesapKontrol(){
        for (int i=0 ; i<Main.yolcular.size() ; i++)
        {
            if (idTxtField.getText().equals(Main.yolcular.get(i).getId()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yöneticiler.size() ; i++)
        {
            if (idTxtField.getText().equals(Main.yöneticiler.get(i).getId()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (idTxtField.getText().equals(Main.çalışanlar.get(i).getId()))
            {
                return false;
            }
        }
        if (idTxtField.getText().equals(Main.Admin.getId()))
        {
            return false;
        }

        
        for (int i=0 ; i<Main.yolcular.size() ; i++)
        {
            if (kullaniciAdiTxtField.getText().equals(Main.yolcular.get(i).getKullaniciAdi()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yöneticiler.size() ; i++)
        {
            if (kullaniciAdiTxtField.getText().equals(Main.yöneticiler.get(i).getKullaniciAdi()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (kullaniciAdiTxtField.getText().equals(Main.çalışanlar.get(i).getKullaniciAdi()))
            {
                return false;
            }
        }
        if (kullaniciAdiTxtField.getText().equals(Main.Admin.getKullaniciAdi()))
        {
            return false;
        }

        
        for (int i=0 ; i<Main.yolcular.size() ; i++)
        {
            if (mailTxtField.getText().toLowerCase().equals(Main.yolcular.get(i).getMail().toLowerCase()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yöneticiler.size() ; i++)
        {
            if (mailTxtField.getText().toLowerCase().equals(Main.yöneticiler.get(i).getMail().toLowerCase()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (mailTxtField.getText().toLowerCase().equals(Main.çalışanlar.get(i).getMail().toLowerCase()))
            {
                return false;
            }
        }
        if (mailTxtField.getText().toLowerCase().equals(Main.Admin.getMail().toLowerCase()))
        {
            return false;
        }

        
        for (int i=0 ; i<Main.yolcular.size() ; i++)
        {
            if (telefonNoTxtField.getText().equals(Main.yolcular.get(i).getTelefonNo()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yöneticiler.size() ; i++)
        {
            if (telefonNoTxtField.getText().equals(Main.yöneticiler.get(i).getTelefonNo()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (telefonNoTxtField.getText().equals(Main.çalışanlar.get(i).getTelefonNo()))
            {
                return false;
            }
        }
        if (telefonNoTxtField.getText().equals(Main.Admin.getTelefonNo()))
        {
            return false;
        }

        return true;
    }
}
