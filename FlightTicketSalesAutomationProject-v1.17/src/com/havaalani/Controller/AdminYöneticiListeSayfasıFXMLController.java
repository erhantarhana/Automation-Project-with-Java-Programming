
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Yönetici;
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


public class AdminYöneticiListeSayfasıFXMLController implements Initializable {

    @FXML private Label uyariLbl;
    @FXML private Button kaldırBtn;
    @FXML private Button editBtn;
    @FXML private TableView yöneticilerTV;
    @FXML private TableColumn isimCLM;
    @FXML private TableColumn soyİsimCLM;
    @FXML private TableColumn idCLM;
    @FXML private TableColumn kullaniciAdiCLM;
    @FXML private TableColumn telefonCLM;
    @FXML private TableColumn emailCLM;
    @FXML private Button kapatBtn;
    
    public static int editYöneticiIndex;
    public static Stage editYöneticiStage;
    public static boolean editYöneticiSayfasıAçıkMı = false;

    public static TableView sahteYöneticilerTV;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sahteYöneticilerTV = yöneticilerTV;

        idCLM.setCellValueFactory(new PropertyValueFactory<>("id"));
        isimCLM.setCellValueFactory(new PropertyValueFactory<>("isim"));
        soyİsimCLM.setCellValueFactory(new PropertyValueFactory<>("soyİsim"));
        telefonCLM.setCellValueFactory(new PropertyValueFactory<>("telefonNo"));
        emailCLM.setCellValueFactory(new PropertyValueFactory<>("mail"));
        kullaniciAdiCLM.setCellValueFactory(new PropertyValueFactory<>("kullaniciAdi"));

        for (int i=0 ; i< Main.yöneticiler.size() ; i++)
        {
            yöneticilerTV.getItems().add(Main.yöneticiler.get(i));
        }

        kapatBtn.setOnAction(e -> {

            AdminYöneticiKayıtSayfasıFXMLController.yöneticiListeSayfasıAçıkMı = false;
            ((Stage)kapatBtn.getScene().getWindow()).close();

            if (editYöneticiSayfasıAçıkMı)
            {
                editYöneticiStage.close();
                editYöneticiSayfasıAçıkMı = false;
            }
        });

        kaldırBtn.setOnAction(e -> kaldırBtnAction(e));

        editBtn.setOnAction(e -> {

            if (yöneticilerTV.getSelectionModel().getSelectedItem() != null)
            {
                Yönetici editlenenYönetici = (Yönetici) yöneticilerTV.getSelectionModel().getSelectedItem();
                editYöneticiIndex = -1;
                
                for (int i=0 ; i<Main.yöneticiler.size() ; i++)
                {
                    if (Main.yöneticiler.get(i).getId().equals(editlenenYönetici.getId()))
                    {
                        editYöneticiIndex = i;
                        break;
                    }
                }

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminYöneticiDüzenleSayfasıFXML.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                editYöneticiStage = new Stage(StageStyle.UNDECORATED);
                editYöneticiStage.setScene(new Scene(loader.getRoot()));
                editYöneticiStage.show();


                editYöneticiSayfasıAçıkMı = true;
                uyariLbl.setText("");
            }
            else
            {
                uyariLbl.setText("Bir yönetici seçin!!!");
            }
        });
    }
    
    private void kaldırBtnAction(ActionEvent e)
    {
        if (yöneticilerTV.getSelectionModel().getSelectedItem() != null)
        {
            Yönetici yöneticiKaldır = (Yönetici) yöneticilerTV.getSelectionModel().getSelectedItem();
            int yöneticiIndex = -1;
           
            for (int i=0 ; i<Main.yöneticiler.size() ; i++)
            {
                if (Main.yöneticiler.get(i).getId().equals(yöneticiKaldır.getId()))
                {
                    yöneticiIndex = i;
                    break;
                }
            }
            
            Main.yöneticiler.remove(yöneticiIndex);
            
            YazmaOkumaFile<Yönetici> yöneticiYazmaOkumaFile = new YazmaOkumaFile<>(Main.yöneticiler, "Yöneticiler.txt");
            yöneticiYazmaOkumaFile.writeList();

            yöneticilerTV.getItems().clear();
            for (int i=0 ; i< Main.yöneticiler.size() ; i++)
            {
                yöneticilerTV.getItems().add(Main.yöneticiler.get(i));
            }
            yöneticilerTV.refresh();

            uyariLbl.setText("");
        }
        else
        {
            uyariLbl.setText("Bir yönetici seçin!!!");
        }
    }
}
