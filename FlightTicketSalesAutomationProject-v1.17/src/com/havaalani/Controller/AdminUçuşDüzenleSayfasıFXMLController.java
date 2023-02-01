
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Bilet;
import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
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


public class AdminUçuşDüzenleSayfasıFXMLController implements Initializable {

    @FXML private TextField süreTxtFiled;
    @FXML private TextField hedefTxtField;
    @FXML private TextField başlangıçTxtField;
    @FXML private TextField zamanTxtField;
    @FXML private TextField tarihTxtField;
    @FXML  Label idLbl;
    @FXML private Button iptalBtn;
    @FXML private Button editBtn;
    @FXML private TextField ücretTxtField;
    @FXML private TextField cezaTxtField;
    @FXML private Label uyariLbl;

    private Uçuş uçuş = Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.uçuşEditİndex);

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        başlangıçTxtField.setText(uçuş.getBşlNoktasi());
        hedefTxtField.setText(uçuş.getHedef());
        tarihTxtField.setText(uçuş.getTarih());
        zamanTxtField.setText(uçuş.getKalkisZamani());
        süreTxtFiled.setText(uçuş.getSure());
        ücretTxtField.setText(uçuş.getBilet().getUcret());
        cezaTxtField.setText(uçuş.getBilet().getCeza());



        iptalBtn.setOnAction(e -> {
            ((Stage) iptalBtn.getScene().getWindow()).close();

            AdminUçuşlarSayfasıFXMLController.stageEditAçıkMı = false;
        });

        editBtn.setOnAction(e -> edtBtnAction(e));
    }    
    
     private void edtBtnAction(ActionEvent e)
    {
        if (başlangıçTxtField.getText().isEmpty() || hedefTxtField.getText().isEmpty() || tarihTxtField.getText().isEmpty() ||
            zamanTxtField.getText().isEmpty() || süreTxtFiled.getText().isEmpty() || ücretTxtField.getText().isEmpty() || cezaTxtField.getText().isEmpty()  )
        {
            uyariLbl.setText("Tüm alanları doldurun!!!");
        }
//        else if (!hedefTxtField.getText().matches("[a-z]+") ||
//                !başlangıçTxtField.getText().matches("[a-z]+") ||
//                !tarihTxtField.getText().matches("[0-9][0-9]/[0-1][1-9]/[0-3][1-9]")||
//                !zamanTxtField.getText().matches("[0-2][0-9]:[0-6][0-9]")||
//                !ücretTxtField.getText().matches("[1-9][0-9]*") ||
//                !fineTxtField.getText().matches("[0-9]+%"))
//        {
//            uyariLbl.setText("Geçersiz format!!!");
//        }
        else
        {
            uyariLbl.setText("");
            AdminUçuşlarSayfasıFXMLController.stageEditAçıkMı = false;

            //editting the object
            uçuş.setBşlNoktasi(başlangıçTxtField.getText());
            uçuş.setHedef(hedefTxtField.getText());
            uçuş.setTarih(tarihTxtField.getText());
            uçuş.setKalkisZamani(zamanTxtField.getText());
            uçuş.setSure(süreTxtFiled.getText());
            uçuş.getBilet().setCeza(cezaTxtField.getText());
            uçuş.getBilet().setUcret(ücretTxtField.getText());

            YazmaOkumaFile<Bilet> biletYazmaOkumaFile = new YazmaOkumaFile<>(Main.biletler,"Biletler.txt");
            biletYazmaOkumaFile.writeList();

            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar,"Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar,"Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();



            AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.getItems().clear();

            for (int i = 0; i< Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().size() ; i++)
            {
                AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.getItems().add(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(i));
            }

            AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.refresh();
 
            ((Stage) editBtn.getScene().getWindow()).close();
            AdminUçuşlarSayfasıFXMLController.stageEditAçıkMı = false;
        }
    }
}
