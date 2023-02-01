
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Yönetici;
import com.havaalani.Kullanicilar.Çalışan;
import com.havaalani.Main;
import java.io.FileNotFoundException;
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


public class GirişSayfasıFXMLController implements Initializable {

    @FXML private Button sifreUnuttumBtn;
    @FXML private Button kayitOlBtn;
    @FXML private Button girisYapBtn;
    @FXML private PasswordField sifreTxtField;
    @FXML private TextField kullaniciAdiTxtField;
    @FXML private Label uyariLbl;
    
    public static int kullaniciİndex;
    public enum pozisyon{Admin, Yönetici, Çalışan};
    public static pozisyon pozisyon;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        girisYapBtn.setOnAction(e -> {
            try {
                girisYapBtnAction(e);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        
        kayitOlBtn.setOnAction(e -> {
            try {
                kayitOlBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        sifreUnuttumBtn.setOnAction(e -> {
            try {
                sifreUnuttumBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    
    private void sifreUnuttumBtnAction(ActionEvent e) throws IOException{
        FXMLLoader passwordLoader=new FXMLLoader(Main.class.getResource("Fxml/ŞifreSayfasıFXML.fxml"));

        passwordLoader.load();

        Stage passwordStage=new Stage(StageStyle.UNDECORATED);
        passwordStage.setScene(new Scene(passwordLoader.getRoot()));
        passwordStage.show();
    }
    
    private void kayitOlBtnAction(ActionEvent e) throws IOException{
        FXMLLoader registerPageLoader=new FXMLLoader(Main.class.getResource("Fxml/KayıtOlSayfasıFXML.fxml"));

        registerPageLoader.load();

        Stage registerStage =new Stage(StageStyle.UNDECORATED);
        registerStage.setTitle("Kayıt Ol");
        registerStage.setScene(new Scene(registerPageLoader.getRoot()));
        registerStage.show();
    }
    
    private void girisYapBtnAction(ActionEvent e) throws FileNotFoundException{
        if (kullaniciAdiTxtField.getText().equals("") || sifreTxtField.getText().equals("")) {
            uyariLbl.setText("Lütfen boş alan bırakmayın!");
        }
        else{
            
            if (kullaniciAdiTxtField.getText().equals(Main.Admin.getKullaniciAdi())) {
                if (sifreTxtField.getText().equals(Main.Admin.getSifre())) {
                    uyariLbl.setText("");
                    ((Stage) girisYapBtn.getScene().getWindow()).close();
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminSayfasıFXML.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    AdminSayfasıFXMLController controller = loader.getController();
                    controller.bilgiLbl.setText(Main.Admin.getIsim()+ " " + Main.Admin.getSoyİsim());
                    controller.pozisyonLbl.setText("Admin");
                    
                    Stage AdminStage = new Stage(StageStyle.UNDECORATED);
                    AdminStage.setScene(new Scene(loader.getRoot()));
                    AdminStage.show();
                    pozisyon = GirişSayfasıFXMLController.pozisyon.Admin;
                    kullaniciAdiTxtField.setText("");
                    sifreTxtField.setText("");                              
                }
                else{
                    uyariLbl.setText("Yanlış şifre girdiniz!!!");
                }
            }
            else{
                boolean yöneticiGirdiMi = false;
                for (int i = 0; i < Main.yöneticiler.size(); i++) {
                    if (kullaniciAdiTxtField.getText().equals(Main.yöneticiler.get(i).getKullaniciAdi())) {
                        if (sifreTxtField.getText().equals(Main.yöneticiler.get(i).getSifre())) {
                            kullaniciİndex = i;
                            FXMLLoader Loader = new FXMLLoader(Main.class.getResource("Fxml/AdminSayfasıFXML.fxml"));
                            try {
                                Loader.load();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            
                            AdminSayfasıFXMLController controller = Loader.getController();
                            controller.bilgiLbl.setText(Main.yöneticiler.get(kullaniciİndex).getIsim()+ " " + Main.yöneticiler.get(kullaniciİndex).getSoyİsim());
                            controller.pozisyonLbl.setText("Yönetici");
                            
                            Stage stage=new Stage(StageStyle.UNDECORATED);
                            stage.setScene(new Scene(Loader.getRoot()));
                            stage.show();
                            pozisyon = GirişSayfasıFXMLController.pozisyon.Yönetici;
                            kullaniciAdiTxtField.setText("");
                            sifreTxtField.setText(""); 
                            ((Stage)girisYapBtn.getScene().getWindow()).close();
                            yöneticiGirdiMi = true;
                            break;
                        }
                        else{
                            uyariLbl.setText("Yanlış şifre girdiniz!!!");
                            yöneticiGirdiMi = true;
                            break;
                        }
                    }
                }
                boolean çalışanGirdiMi = false;
                if (!yöneticiGirdiMi) {
                    for (int i = 0; i < Main.çalışanlar.size(); i++) {
                        if (kullaniciAdiTxtField.getText().equals(Main.çalışanlar.get(i).getKullaniciAdi())) {
                            if (sifreTxtField.getText().equals(Main.çalışanlar.get(i).getSifre())) {
                                kullaniciİndex = i;
                                
                                FXMLLoader Loader=new FXMLLoader(Main.class.getResource("Fxml/AdminSayfasıFXML.fxml"));
                                try {
                                    Loader.load();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                
                                AdminSayfasıFXMLController controller = Loader.getController();
                                controller.bilgiLbl.setText(Main.çalışanlar.get(kullaniciİndex).getIsim()+ " " + Main.çalışanlar.get(kullaniciİndex).getSoyİsim());
                                controller.pozisyonLbl.setText("Çalışan");
                                
                                Stage stage=new Stage(StageStyle.UNDECORATED);
                                stage.setScene(new Scene(Loader.getRoot()));
                                stage.show();
                                pozisyon = GirişSayfasıFXMLController.pozisyon.Çalışan;
                                
                                ((Stage)girisYapBtn.getScene().getWindow()).close();
                                kullaniciAdiTxtField.setText("");
                                sifreTxtField.setText("");
                                çalışanGirdiMi = true;
                                break;
                            }
                            else{
                                uyariLbl.setText("Yanlış şifre girdiniz!!!");
                                çalışanGirdiMi = true;
                                break;
                            }
                        }
                    }
                }
                boolean yolcuGirdiMi = false;
                if (!yöneticiGirdiMi && !çalışanGirdiMi) {
                    for (int i = 0; i < Main.yolcular.size(); i++) {
                        if (kullaniciAdiTxtField.getText().equals(Main.yolcular.get(i).getKullaniciAdi())) {
                            if (sifreTxtField.getText().equals(Main.yolcular.get(i).getSifre())) {
                                kullaniciİndex = i;
                                
                                FXMLLoader yolcuLoader = new FXMLLoader(Main.class.getResource("Fxml/YolcuSayfasıFXML.fxml"));
                                
                                try {
                                    yolcuLoader.load();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                
                                Stage yolcuStage=new Stage(StageStyle.UNDECORATED);
                                yolcuStage.setScene(new Scene(yolcuLoader.getRoot()));
                                yolcuStage.show();
                                
                                System.out.println(kullaniciİndex);
                                kullaniciAdiTxtField.setText("");
                                sifreTxtField.setText("");
                                ((Stage)girisYapBtn.getScene().getWindow()).close();
                                yolcuGirdiMi=true;
                                break;
                            }
                            else{
                                uyariLbl.setText("Yanlış şifre girdiniz!!!");
                                yolcuGirdiMi = true;
                                break;
                            }
                        }
                    }
                }
                if (!yöneticiGirdiMi && !çalışanGirdiMi && !yolcuGirdiMi) {
                    uyariLbl.setText("Yanlış kullanıcı adı girdiniz!!!");
                }
            }
        }
    }   
}
