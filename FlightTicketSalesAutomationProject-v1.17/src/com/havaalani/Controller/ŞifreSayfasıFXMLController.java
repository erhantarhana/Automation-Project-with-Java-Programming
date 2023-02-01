
package com.havaalani.Controller;

import com.havaalani.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ŞifreSayfasıFXMLController implements Initializable {

    @FXML private Button okBtn;
    @FXML private Label sifreLbl;
    @FXML private TextField idTxtField;
    @FXML private Button iptalBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iptalBtn.setOnAction(e -> {

            ((Stage)iptalBtn.getScene().getWindow()).close();
        });

        okBtn.setOnAction(e -> okBtnAction(e));
    }    
    
    private void okBtnAction(ActionEvent e){
        if (!(idTxtField.getText().isEmpty())) {
            
            boolean adminMi = false;
            if (idTxtField.getText().equals(Main.Admin.getId())) {
                sifreLbl.setText("Şifreniz "+Main.Admin.getSifre());
                adminMi=true;
            }
            
            boolean yöneticiMi = false;
            if (!adminMi) {
                for (int i = 0; i < Main.yöneticiler.size(); i++) {
                    if (idTxtField.getText().equals(Main.yöneticiler.get(i).getId())) {
                        sifreLbl.setText("Şifreniz "+Main.yöneticiler.get(i).getSifre());
                        yöneticiMi = true;
                        break;
                    }
                }
            }
            
            boolean çalışanMı = false;
            if (!adminMi && !yöneticiMi) {
                for (int i=0 ; i < Main.çalışanlar.size() ; i++)
                {
                    if (idTxtField.getText().equals(Main.çalışanlar.get(i).getId()))
                    {
                        sifreLbl.setText("Şifreniz "+Main.çalışanlar.get(i).getSifre());
                        çalışanMı=true;
                        break;
                    }
                }
            }
            
            boolean yolcuMu = false;
            if (!adminMi && !yöneticiMi && !çalışanMı){
                for (int i=0 ; i < Main.yolcular.size() ; i++)
                {
                    if (idTxtField.getText().equals(Main.yolcular.get(i).getId()))
                    {
                        sifreLbl.setText("Şifreniz "+Main.yolcular.get(i).getSifre());
                        yolcuMu=true;
                        break;
                    }
                }
            }
            
            if (!adminMi && !yöneticiMi && !çalışanMı && !yolcuMu)
            {
                sifreLbl.setText("Kullanıcı bulunamadı!!!");
            }
        }
    }
}
