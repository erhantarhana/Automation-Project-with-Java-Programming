
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class AdminKayıtlarSayfasıFXMLController implements Initializable {

    @FXML private TableView kayıtlarTV;
    @FXML private TableColumn kayıtCLM;
    @FXML private TableColumn zamanCLM;
    @FXML private Button kapatBtn;
    @FXML private Label uyariLbl;
    @FXML private Button silBtn;

    static TableView sahteKayıtlarTV;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         sahteKayıtlarTV = kayıtlarTV;

        
        zamanCLM.setCellValueFactory(new PropertyValueFactory<>("zaman"));
        kayıtCLM.setCellValueFactory(new PropertyValueFactory<>("yazi"));

        for (int i = 0; i< Main.kayıtlar.size() ; i++)
        {
            kayıtlarTV.getItems().add(Main.kayıtlar.get(i));
        }

        kapatBtn.setOnAction(e -> {

            ((Stage)kapatBtn.getScene().getWindow()).close();
            AdminSayfasıFXMLController.kayıtSayfasıAçıkMı = false;

        });
        silBtn.setOnAction(e -> silBtnAction(e));
    }    
    
     private void silBtnAction(ActionEvent e)
    {
        if (kayıtlarTV.getSelectionModel().getSelectedItem() !=null)
        {
            Kayıt kayıtKaldır = (Kayıt) kayıtlarTV.getSelectionModel().getSelectedItem();
            int kayıtIndex = -1;
            
            for (int i=0 ; i<Main.kayıtlar.size() ; i++)
            {
                if (Main.kayıtlar.get(i).getYazi().equals(kayıtKaldır.getYazi())  &&
                        Main.kayıtlar.get(i).getZaman().equals(kayıtKaldır.getZaman()))
                {
                    kayıtIndex = i;
                    break;
                }
            }

            
            Main.kayıtlar.remove(kayıtIndex);
            
            YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
            kayıtYazmaOkumaFile.writeList();

            
            kayıtlarTV.getItems().clear();
            for (int i=0 ; i<Main.kayıtlar.size() ; i++)
            {
                kayıtlarTV.getItems().add(Main.kayıtlar.get(i));
            }
            kayıtlarTV.refresh();

            uyariLbl.setText("");
        }
        else
        {
            uyariLbl.setText("Bir mesaj seçin!!!");
        }
    }

    public static void yenileKayıtlarTV()
    {
        AdminKayıtlarSayfasıFXMLController.sahteKayıtlarTV.getItems().clear();
        for (int i=0 ; i<Main.kayıtlar.size() ; i++)
        {
            AdminKayıtlarSayfasıFXMLController.sahteKayıtlarTV.getItems().add(Main.kayıtlar.get(i));
        }
        AdminKayıtlarSayfasıFXMLController.sahteKayıtlarTV.refresh();
    }
    
}
