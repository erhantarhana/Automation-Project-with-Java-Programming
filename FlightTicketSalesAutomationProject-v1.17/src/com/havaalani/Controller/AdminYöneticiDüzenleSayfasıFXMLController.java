
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Yönetici;
import com.havaalani.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AdminYöneticiDüzenleSayfasıFXMLController implements Initializable {

    @FXML private Button editBtn;
    @FXML private Label uyariLbl;
    @FXML private TextField krediTxtField;
    @FXML private Button iptalBtn;
    @FXML private TextField idTxtField;
    @FXML private TextField isimTxtField;
    @FXML private TextField emailTxtField;
    @FXML private TextField kullaniciAdiTxtField;
    @FXML private TextField soyİsimTxtField;
    @FXML private TextField telefonTxtField;
    @FXML private TextField şifreTxtField;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Yönetici yönetici = Main.yöneticiler.get(AdminYöneticiListeSayfasıFXMLController.editYöneticiIndex);

        isimTxtField.setText(yönetici.getIsim());
        soyİsimTxtField.setText(yönetici.getSoyİsim());
        idTxtField.setText(yönetici.getId());
        emailTxtField.setText(yönetici.getMail());
        telefonTxtField.setText(yönetici.getTelefonNo());
        şifreTxtField.setText(yönetici.getSifre());
        kullaniciAdiTxtField.setText(yönetici.getKullaniciAdi());
        krediTxtField.setText(yönetici.getMaas());

        iptalBtn.setOnAction(e -> {
            ((Stage) iptalBtn.getScene().getWindow()).close();
            AdminYöneticiListeSayfasıFXMLController.editYöneticiSayfasıAçıkMı = false;
        });

        editBtn.setOnAction(e -> {
            try {
                editBTNaction(e);
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    private void editBTNaction(ActionEvent e) throws IOException {
        if (isimTxtField.getText().isEmpty() || soyİsimTxtField.getText().isEmpty() || kullaniciAdiTxtField.getText().isEmpty() || şifreTxtField.getText().isEmpty()
                || krediTxtField.getText().isEmpty() || emailTxtField.getText().isEmpty() || idTxtField.getText().isEmpty())
        {
            
            uyariLbl.setText("Tüm alanları doldurun!!!");
        }
        else
        {
            if (!boşlukKontrol())
            {
                
                uyariLbl.setText("Alanlardaki tüm boşlukları kaldırın!!!");
            }
            else
            {
                if (!formatKontrol())
                {
                    
                    uyariLbl.setText("Geçerli format kullanın!!!");
                }
                else if (!ayniHesapKontrol())
                {
                    uyariLbl.setText("Bu bilgilere sahip bir kullanıcı zaten var!!!");
                }
                else
                {
                    int index = AdminYöneticiListeSayfasıFXMLController.editYöneticiIndex;

                    Main.yöneticiler.get(index).setMaas(krediTxtField.getText());
                    Main.yöneticiler.get(index).setIsim(isimTxtField.getText());
                    Main.yöneticiler.get(index).setSoyİsim(soyİsimTxtField.getText());
                    Main.yöneticiler.get(index).setId(idTxtField.getText());
                    Main.yöneticiler.get(index).setKullaniciAdi(kullaniciAdiTxtField.getText());
                    Main.yöneticiler.get(index).setSifre(şifreTxtField.getText());
                    Main.yöneticiler.get(index).setMail(emailTxtField.getText());
                    Main.yöneticiler.get(index).setTelefonNo(telefonTxtField.getText());
                    
                    YazmaOkumaFile<Yönetici> yöneticiYazmaOkumaFile = new YazmaOkumaFile<>(Main.yöneticiler, "Yöneticiler.txt");
                    yöneticiYazmaOkumaFile.writeList();

                    AdminYöneticiListeSayfasıFXMLController.sahteYöneticilerTV.getItems().clear();
                    for (int i=0 ; i< Main.yöneticiler.size() ; i++)
                    {
                        AdminYöneticiListeSayfasıFXMLController.sahteYöneticilerTV.getItems().add(Main.yöneticiler.get(i));
                    }
                    AdminYöneticiListeSayfasıFXMLController.sahteYöneticilerTV.refresh();

                    uyariLbl.setText("");
                    ((Stage)editBtn.getScene().getWindow()).close();
                }
            }
        }
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

        if ( (telefonTxtField.getText().length() !=11) || !(telefonTxtField.getText().substring(0 , 2).equals("05")) || !(telefonTxtField.getText().matches("[0-9]+")))
        {
            return false;
        }

        if (!(emailTxtField.getText().matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")))
        {
            return false;
        }
        if (!(krediTxtField.getText().matches("[0-9]+")))
        {
            return false;
        }
        return true;
    }
    
    private boolean boşlukKontrol(){
        
            String kontrol1 = isimTxtField.getText();
            kontrol1 = kontrol1.trim();
            String[] tempKontrol1 = kontrol1.split(" ");
            if (tempKontrol1.length>1)
            {
                return false;
            }

            String kontrol2 = soyİsimTxtField.getText();
            kontrol2 = kontrol2.trim();
            String[] tempKontrol2 = kontrol2.split(" ");
            if (tempKontrol2.length>1)
            {
                return false;
            }

        String kontrol3 = kullaniciAdiTxtField.getText();
        kontrol3 = kontrol3.trim();
        String[] tempKontrol3 = kontrol3.split(" ");
        if (tempKontrol3.length>1)
        {
            return false;
        }

        String kontrol4 = şifreTxtField.getText();
        kontrol4 = kontrol4.trim();
        String[] tempKontrol4 = kontrol4.split(" ");
        if (tempKontrol4.length>1)
        {
            return false;
        }

        String kontrol5 = krediTxtField.getText();
        kontrol5 = kontrol5.trim();
        String[] tempKontrol5 = kontrol5.split(" ");
        if (tempKontrol5.length>1)
        {
            return false;
        }

        String kontrol6 = telefonTxtField.getText();
        kontrol6 = kontrol6.trim();
        String[] tempKontrol6 = kontrol6.split(" ");
        if (tempKontrol6.length>1)
        {
            return false;
        }

        String kontrol8 = emailTxtField.getText();
        kontrol8 = kontrol8.trim();
        String[] tempKontrol8 = kontrol8.split(" ");
        if (tempKontrol8.length>1)
        {
            return false;
        }
        return true;
    }
    
    private boolean ayniHesapKontrol(){
        for (int i = 0; i< Main.çalışanlar.size() ; i++)
        {
            if (idTxtField.getText().equals(Main.çalışanlar.get(i).getId()) )
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yolcular.size() ; i++)
        {
            if (idTxtField.getText().equals(Main.yolcular.get(i).getId()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yöneticiler.size() ; i++)
        {
            if (idTxtField.getText().equals(Main.yöneticiler.get(i).getId()) && i != AdminYöneticiListeSayfasıFXMLController.editYöneticiIndex)
            {
                return false;
            }
        }
        if (idTxtField.getText().equals(Main.Admin.getId()))
        {
            return false;
        }

        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (kullaniciAdiTxtField.getText().equals(Main.çalışanlar.get(i).getKullaniciAdi()) )
            {
                return false;
            }
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
            if (kullaniciAdiTxtField.getText().equals(Main.yöneticiler.get(i).getKullaniciAdi()) && i != AdminYöneticiListeSayfasıFXMLController.editYöneticiIndex)
            {
                return false;
            }
        }
        if (kullaniciAdiTxtField.getText().equals(Main.Admin.getKullaniciAdi()))
        {
            return false;
        }

        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (emailTxtField.getText().toLowerCase().equals(Main.çalışanlar.get(i).getMail().toLowerCase()) )
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yolcular.size() ; i++)
        {
            if (emailTxtField.getText().toLowerCase().equals(Main.yolcular.get(i).getMail().toLowerCase()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yöneticiler.size() ; i++)
        {
            if (emailTxtField.getText().toLowerCase().equals(Main.yöneticiler.get(i).getMail().toLowerCase())&& i != AdminYöneticiListeSayfasıFXMLController.editYöneticiIndex)
            {
                return false;
            }
        }
        if (emailTxtField.getText().toLowerCase().equals(Main.Admin.getMail().toLowerCase()))
        {
            return false;
        }

        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (telefonTxtField.getText().equals(Main.çalışanlar.get(i).getTelefonNo()) )
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yolcular.size() ; i++)
        {
            if (telefonTxtField.getText().equals(Main.yolcular.get(i).getTelefonNo()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yöneticiler.size() ; i++)
        {
            if (telefonTxtField.getText().equals(Main.yöneticiler.get(i).getTelefonNo())&& i != AdminYöneticiListeSayfasıFXMLController.editYöneticiIndex)
            {
                return false;
            }
        }
        if (telefonTxtField.getText().equals(Main.Admin.getTelefonNo()))
        {
            return false;
        }
        return true;
    }
}
