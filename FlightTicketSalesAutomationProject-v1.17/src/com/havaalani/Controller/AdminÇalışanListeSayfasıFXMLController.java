
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminÇalışanListeSayfasıFXMLController implements Initializable {

    @FXML private TableColumn idCLM;
    @FXML private TableColumn emailCLM;
    @FXML private Label uyariLbl;
    @FXML private Button kaldırBtn;
    @FXML private Button editBtn;
    @FXML private TableView çalışanlarTV;
    @FXML private TableColumn isimCLM;
    @FXML private TableColumn soyİsimCLM;
    @FXML private TableColumn kullanıcıAdıCLM;
    @FXML private TableColumn telefonCLM;
    @FXML private Button kapatBtn;
    
    public static int editÇalışanIndex;
    public static Stage editÇalışanStage;
    public static boolean editÇalışanSayfasıAçıkMı = false;

    public static TableView sahteÇalışanlarTV;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sahteÇalışanlarTV = çalışanlarTV;

        idCLM.setCellValueFactory(new PropertyValueFactory<>("id"));
        isimCLM.setCellValueFactory(new PropertyValueFactory<>("isim"));
        soyİsimCLM.setCellValueFactory(new PropertyValueFactory<>("soyİsim"));
        telefonCLM.setCellValueFactory(new PropertyValueFactory<>("telefonNo"));
        emailCLM.setCellValueFactory(new PropertyValueFactory<>("mail"));
        kullanıcıAdıCLM.setCellValueFactory(new PropertyValueFactory<>("kullaniciAdi"));

        for (int i=0 ; i< Main.çalışanlar.size() ; i++)
        {
            çalışanlarTV.getItems().add(Main.çalışanlar.get(i));
        }

        kapatBtn.setOnAction(e -> {

            AdminÇalışanKayıtSayfasıFXMLController.çalışanListeAçıkMı = false;
            ((Stage)kapatBtn.getScene().getWindow()).close();

            if (editÇalışanSayfasıAçıkMı)
            {
                editÇalışanStage.close();
                editÇalışanSayfasıAçıkMı = false;
            }
        });

        kaldırBtn.setOnAction(e -> kaldırBtnAction(e));

        editBtn.setOnAction(e -> {

            if (çalışanlarTV.getSelectionModel().getSelectedItem() != null)
            {
                Çalışan editlenenÇalışan = (Çalışan) çalışanlarTV.getSelectionModel().getSelectedItem();
                editÇalışanIndex = -1;
                
                for (int i=0 ; i<Main.çalışanlar.size() ; i++)
                {
                    if (Main.çalışanlar.get(i).getId().equals(editlenenÇalışan.getId()))
                    {
                        editÇalışanIndex = i;
                        break;
                    }
                }

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminÇalışanDüzenleSayfasıFXML.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                editÇalışanStage = new Stage(StageStyle.UNDECORATED);
                editÇalışanStage.setScene(new Scene(loader.getRoot()));
                editÇalışanStage.show();


                editÇalışanSayfasıAçıkMı = true;
                uyariLbl.setText("");
            }
            else
            {
                uyariLbl.setText("Bir çalışan seçin!!!");
            }
        });
    }    
    
    private void kaldırBtnAction(ActionEvent e)
    {
        if (çalışanlarTV.getSelectionModel().getSelectedItem() != null)
        {
            Çalışan çalışanKaldır = (Çalışan) çalışanlarTV.getSelectionModel().getSelectedItem();
            int çalışanIndex = -1;
            
            for (int i=0 ; i<Main.çalışanlar.size() ; i++)
            {
                if (Main.çalışanlar.get(i).getId().equals(çalışanKaldır.getId()))
                {
                    çalışanIndex = i;
                    break;
                }
            }
            
            Main.çalışanlar.remove(çalışanIndex);
            
            YazmaOkumaFile<Çalışan> çalışanYazmaOkumaFile = new YazmaOkumaFile<>(Main.çalışanlar, "Çalışanlar.txt");
            çalışanYazmaOkumaFile.writeList();

            çalışanlarTV.getItems().clear();
            for (int i=0 ; i< Main.çalışanlar.size() ; i++)
            {
                çalışanlarTV.getItems().add(Main.çalışanlar.get(i));
            }
            çalışanlarTV.refresh();

            uyariLbl.setText("");
        }
        else
        {
            uyariLbl.setText("Bir çalışan seçin!!!");
        }
    }    
}
