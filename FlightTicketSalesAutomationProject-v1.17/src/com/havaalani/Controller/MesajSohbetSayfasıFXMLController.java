
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Mesaj;
import com.havaalani.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MesajSohbetSayfasıFXMLController implements Initializable {

    @FXML private TableView mesajTV;
    @FXML private TableColumn gönderenCLM;
    @FXML private TableColumn mesajCLM;
    @FXML private TableColumn zamanCLM;
    @FXML private Button kapatBtn;
    @FXML private Label uyariLbl;
    @FXML private Button silBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        zamanCLM.setCellValueFactory(new PropertyValueFactory<>("zaman"));
        mesajCLM.setCellValueFactory(new PropertyValueFactory<>("yazi"));
        gönderenCLM.setCellValueFactory(new PropertyValueFactory<>("gonderen"));
        
        for (int i=0 ; i<Main.mesajlar.size() ; i++)
        {
            mesajTV.getItems().add(Main.mesajlar.get(i));
        }

        kapatBtn.setOnAction(e -> {

            ((Stage)kapatBtn.getScene().getWindow()).close();

            AdminSayfasıFXMLController.mesajSohbetStageAçıkMı = false;
        });

        silBtn.setOnAction(e -> silBtnAction(e));
    }    
    
    private void silBtnAction(ActionEvent e)
    {
        if (mesajTV.getSelectionModel().getSelectedItem() !=null)
        {
            Mesaj mesajKaldır = (Mesaj) mesajTV.getSelectionModel().getSelectedItem();
            int mesajİndex = -1;
            
            for (int i=0 ; i<Main.mesajlar.size() ; i++)
            {
                if (Main.mesajlar.get(i).getYazi().equals(mesajKaldır.getYazi())  &&
                        Main.mesajlar.get(i).getGonderen().equals(mesajKaldır.getGonderen()))
                {
                    mesajİndex=i;
                    break;
                }
            }

            
            Main.mesajlar.remove(mesajİndex);
            
            YazmaOkumaFile<Mesaj> mesajYazmaOkumaFile = new YazmaOkumaFile<>(Main.mesajlar , "Mesajlar.txt");
            mesajYazmaOkumaFile.writeList();

            
            mesajTV.getItems().clear();
            for (int i=0 ; i<Main.mesajlar.size() ; i++)
            {
                mesajTV.getItems().add(Main.mesajlar.get(i));
            }
            mesajTV.refresh();



            uyariLbl.setText("");
        }
        else
        {
            uyariLbl.setText("Mesaj seçin!!!");
        }
    }
}
