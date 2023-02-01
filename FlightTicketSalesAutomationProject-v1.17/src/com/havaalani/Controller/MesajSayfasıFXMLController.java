
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Mesaj;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class MesajSayfasıFXMLController implements Initializable {

    @FXML private Button iptalBtn;
    @FXML private TextArea mesajTxtArea;
    @FXML private Button gönderBtn;

    public String gönderen;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iptalBtn.setOnAction(e ->{
            ((Stage)iptalBtn.getScene().getWindow()).close();
            YolcuSayfasıFXMLController.mesajPageAçıkMı = false;
        });

        gönderBtn.setOnAction(e -> {
            try {
                gönderBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    private void gönderBtnAction(ActionEvent e) throws IOException {
        if (! mesajTxtArea.getText().isEmpty())
        {
            Mesaj yeniMesaj = new Mesaj(mesajTxtArea.getText() , gönderen);

            Main.mesajlar.add(yeniMesaj);

            YazmaOkumaFile<Mesaj> mesajYazmaOkumaFile = new YazmaOkumaFile<>(Main.mesajlar , "Mesajlar.txt");
            mesajYazmaOkumaFile.writeList();


            ((Stage)iptalBtn.getScene().getWindow()).close();
            YolcuSayfasıFXMLController.mesajPageAçıkMı = false;
        }
    }
}
