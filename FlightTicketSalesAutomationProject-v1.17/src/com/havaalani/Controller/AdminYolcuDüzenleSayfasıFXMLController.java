
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
import com.havaalani.Kullanicilar.Yolcu;
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


public class AdminYolcuDüzenleSayfasıFXMLController implements Initializable {

    @FXML private Button editBtn;
    @FXML private Label uyariLbl;
    @FXML private TextField krediTxtField;
    @FXML private Button iptalBtn;
    @FXML private TextField idTxtField;
    @FXML private TextField isimTextField;
    @FXML private TextField emailTxtField;
    @FXML private TextField kullaniciAdiTxtField;
    @FXML private TextField soyİsimTxtField;
    @FXML private TextField telefonTxtField;
    @FXML private TextField şifreTxtField;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Yolcu yolcu = Main.yolcular.get(AdminYolcularSayfasıFXMLController.editYolcuIndex);
        isimTextField.setText(yolcu.getIsim());
        soyİsimTxtField.setText(yolcu.getSoyİsim());
        idTxtField.setText(yolcu.getId());
        emailTxtField.setText(yolcu.getMail());
        telefonTxtField.setText(yolcu.getTelefonNo());
        şifreTxtField.setText(yolcu.getSifre());
        kullaniciAdiTxtField.setText(yolcu.getKullaniciAdi());
        krediTxtField.setText(yolcu.getKredi());


        iptalBtn.setOnAction(e -> {
            AdminYolcularSayfasıFXMLController.editSayfasıAçıkMı = false;
            ((Stage) iptalBtn.getScene().getWindow()).close();
        });

        editBtn.setOnAction(e -> {
            try {
                editBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    private void editBtnAction(ActionEvent e) throws IOException {
        if (isimTextField.getText().isEmpty() || soyİsimTxtField.getText().isEmpty() || kullaniciAdiTxtField.getText().isEmpty() || şifreTxtField.getText().isEmpty()
                || krediTxtField.getText().isEmpty() || emailTxtField.getText().isEmpty() || idTxtField.getText().isEmpty())
        {
            uyariLbl.setText("Tüm boşlukları doldurun!!!");
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
                    {
                        int index = AdminYolcularSayfasıFXMLController.editYolcuIndex;

                        Main.yolcular.get(index).setKredi(krediTxtField.getText());
                        Main.yolcular.get(index).setIsim(isimTextField.getText());
                        Main.yolcular.get(index).setSoyİsim(soyİsimTxtField.getText());
                        Main.yolcular.get(index).setId(idTxtField.getText());
                        Main.yolcular.get(index).setKullaniciAdi(kullaniciAdiTxtField.getText());
                        Main.yolcular.get(index).setSifre(şifreTxtField.getText());
                        Main.yolcular.get(index).setMail(emailTxtField.getText());
                        Main.yolcular.get(index).setTelefonNo(telefonTxtField.getText());
                        
                        YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>( Main.yolcular , "Yolcu.txt");
                        yolcuYazmaOkumaFile.writeList();

                        for (int i=0 ; i<Main.uçuşlar.size() ; i++)
                        {
                            for (int j=0 ; j<Main.uçuşlar.get(i).getYolcu().size() ; j++)
                            {
                                if (Main.uçuşlar.get(i).getYolcu().get(j).equals(Main.yolcular.get(index)))
                                {
                                    Main.uçuşlar.get(i).getYolcu().get(j).setKredi(krediTxtField.getText());
                                    Main.uçuşlar.get(i).getYolcu().get(j).setIsim(isimTextField.getText());
                                    Main.uçuşlar.get(i).getYolcu().get(j).setSoyİsim(soyİsimTxtField.getText());
                                    Main.uçuşlar.get(i).getYolcu().get(j).setId(idTxtField.getText());
                                    Main.uçuşlar.get(i).getYolcu().get(j).setKullaniciAdi(kullaniciAdiTxtField.getText());
                                    Main.uçuşlar.get(i).getYolcu().get(j).setSifre(şifreTxtField.getText());
                                    Main.uçuşlar.get(i).getYolcu().get(j).setMail(emailTxtField.getText());
                                    Main.uçuşlar.get(i).getYolcu().get(j).setTelefonNo(telefonTxtField.getText());
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
                                        .equals(Main.yolcular.get(index)))
                                    {
                                        Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setKredi(krediTxtField.getText());
                                        Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setIsim(isimTextField.getText());
                                        Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setSoyİsim(soyİsimTxtField.getText());
                                        Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setId(idTxtField.getText());
                                        Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setKullaniciAdi(kullaniciAdiTxtField.getText());
                                        Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setSifre(şifreTxtField.getText());
                                        Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setMail(emailTxtField.getText());
                                        Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).setTelefonNo(telefonTxtField.getText());
                                    }
                                }
                            }
                        }
                        
                        YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
                        uçakYazmaOkumaFile.writeList();

                        AdminYolcularSayfasıFXMLController.sahteYolcuTV.getItems().clear();

                        for (int i=0 ; i< Main.yolcular.size() ; i++)
                        {
                            AdminYolcularSayfasıFXMLController.sahteYolcuTV.getItems().add(Main.yolcular.get(i));
                        }

                        AdminYolcularSayfasıFXMLController.sahteYolcuTV.refresh();

                        ((Stage)editBtn.getScene().getWindow()).close();
                    }
                }
            }
        }
    }
    private boolean formatKontrol(){
       
        if (!(isimTextField.getText().matches("[a-zA-Z]+")) || !(soyİsimTxtField.getText().matches("[a-zA-Z]+")))
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
        
            String kontrol1 = isimTextField.getText();
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
            if (idTxtField.getText().equals(Main.yolcular.get(i).getId()) && i!=AdminYolcularSayfasıFXMLController.editYolcuIndex)
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
        if (idTxtField.getText().equals(Main.Admin.getId()))
        {
            return false;
        }

        for (int i=0 ; i<Main.çalışanlar.size() ; i++)
        {
            if (kullaniciAdiTxtField.getText().equals(Main.çalışanlar.get(i).getKullaniciAdi()))
            {
                return false;
            }
        }
        for (int i=0 ; i<Main.yolcular.size() ; i++)
        {
            if (kullaniciAdiTxtField.getText().equals(Main.yolcular.get(i).getKullaniciAdi())&& i!=AdminYolcularSayfasıFXMLController.editYolcuIndex)
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
            if (emailTxtField.getText().toLowerCase().equals(Main.yolcular.get(i).getMail().toLowerCase())&& i!=AdminYolcularSayfasıFXMLController.editYolcuIndex)
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
            if (telefonTxtField.getText().equals(Main.yolcular.get(i).getTelefonNo())&& i!=AdminYolcularSayfasıFXMLController.editYolcuIndex)
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
        if (telefonTxtField.getText().equals(Main.Admin.getTelefonNo()))
        {
            return false;
        }
        return true;
    }
}
