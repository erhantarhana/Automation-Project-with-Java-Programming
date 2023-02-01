
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Çalışan;
import com.havaalani.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminÇalışanKayıtSayfasıFXMLController implements Initializable {

    @FXML private TextField idTxtField;
    @FXML private TextField adresTxtField;
    @FXML private Button kayıtBtn;
    @FXML private Label uyariLbl;
    @FXML private TextField isimTxtField;
    @FXML private TextField maaşTxtField;
    @FXML private Button listeBtn;
    @FXML private TextField emailTxtField;
    @FXML private TextField kullaniciAdiTxtField;
    @FXML private TextField soyİsimTxtField;
    @FXML private TextField telefonTxtField;
    @FXML private PasswordField şifreTxtField;
    @FXML private Button kapatBtn;
    
    public static Stage çalışanListeStage;
    public static boolean çalışanListeAçıkMı = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        kapatBtn.setOnAction(e -> {

            BölmedenSekmeyiKaldir bölmedenSekmeyiKaldir = new BölmedenSekmeyiKaldir();
            int index = AdminSayfasıFXMLController.sahteAdminSayfasıTP.getTabs().indexOf(AdminSayfasıFXMLController.çalışanKayitTab);
            bölmedenSekmeyiKaldir.sekmeKaldır(AdminSayfasıFXMLController.sahteAdminSayfasıTP, index);

            AdminSayfasıFXMLController.çalışanKayıtTabAçıkMı = false;

            if (çalışanListeAçıkMı)
            {
                çalışanListeStage.close();
                çalışanListeAçıkMı = false;

                if (AdminÇalışanListeSayfasıFXMLController.editÇalışanSayfasıAçıkMı)
                {
                    AdminÇalışanListeSayfasıFXMLController.editÇalışanSayfasıAçıkMı = false;
                    AdminÇalışanListeSayfasıFXMLController.editÇalışanStage.close();
                }
            }
        });

        kayıtBtn.setOnAction(e -> {
            try {
                kayıtBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        listeBtn.setOnAction(e -> {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminÇalışanListeSayfasıFXML.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            çalışanListeStage = new Stage(StageStyle.UNDECORATED);
            çalışanListeStage.setScene(new Scene(loader.getRoot()));
            çalışanListeStage.show();

            çalışanListeAçıkMı = true;
        }); 
    }    
    
    private void kayıtBtnAction(ActionEvent e) throws IOException {
        if (isimTxtField.getText().isEmpty() || soyİsimTxtField.getText().isEmpty() || kullaniciAdiTxtField.getText().isEmpty() || şifreTxtField.getText().isEmpty()
                || maaşTxtField.getText().isEmpty() || adresTxtField.getText().isEmpty() || emailTxtField.getText().isEmpty() || idTxtField.getText().isEmpty())
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
                else
                {
                    if (!ayniHesapKontrol())
                    {
                        uyariLbl.setText("Bu bilgilere sahip bir kullanıcı zaten var!!!");
                    }
                    else
                    {

                        Çalışan yeniÇalışan = new Çalışan(maaşTxtField.getText(), adresTxtField.getText(), idTxtField.getText(), isimTxtField.getText(), soyİsimTxtField.getText(), kullaniciAdiTxtField.getText(), şifreTxtField.getText(), telefonTxtField.getText(), emailTxtField.getText());
                        Main.çalışanlar.add(yeniÇalışan);

                        YazmaOkumaFile<Çalışan> çalışanYazmaOkumaFile = new YazmaOkumaFile<>(Main.çalışanlar, "Çalışanlar.txt");
                        çalışanYazmaOkumaFile.writeList();

                        uyariLbl.setText("Tamamlandı!!!");
                    }
                }
            }
        }
    }
    
    private boolean ayniHesapKontrol()
    {
        
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
            if (emailTxtField.getText().toLowerCase().equals(Main.yolcular.get(i).getMail().toLowerCase()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yöneticiler.size() ; i++)
        {
            if (emailTxtField.getText().toLowerCase().equals(Main.yöneticiler.get(i).getMail().toLowerCase()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (emailTxtField.getText().toLowerCase().equals(Main.çalışanlar.get(i).getMail().toLowerCase()))
            {
                return false;
            }
        }
        if (emailTxtField.getText().toLowerCase().equals(Main.Admin.getMail().toLowerCase()))
        {
            return false;
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
            if (telefonTxtField.getText().equals(Main.yöneticiler.get(i).getTelefonNo()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (telefonTxtField.getText().equals(Main.çalışanlar.get(i).getTelefonNo()))
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
    
    private boolean formatKontrol()
    {
        
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
        if (!(maaşTxtField.getText().matches("[0-9]+")))
        {
            return false;
        }
        return true;
    }
    
    private boolean boşlukKontrol()
    {
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

        String kontrol5 = maaşTxtField.getText();
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

            String kontrol7 = adresTxtField.getText();
            kontrol7 = kontrol7.trim();
            String[] tempKontrol7 = kontrol7.split(" ");
            if (tempKontrol7.length>1)
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
}
