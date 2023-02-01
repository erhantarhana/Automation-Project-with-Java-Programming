
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
import com.havaalani.Kullanicilar.Yolcu;
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


public class AdminYolcularSayfasıFXMLController implements Initializable {

    @FXML private Label uyariLbl;
    @FXML private Button kaldırBtn;
    @FXML private Button editBtn;
    @FXML private TableView yolcularTV;
    @FXML private TableColumn isimCLM;
    @FXML private TableColumn soyİsimCLM;
    @FXML private TableColumn idCLM;
    @FXML private TableColumn kullanıcıAdıCLM;
    @FXML private TableColumn telefonCLM;
    @FXML private TableColumn emailCLM;
    @FXML private Button kapatBtn;

    public static boolean editSayfasıAçıkMı = false;
    public static Stage editStage;

    public static int editYolcuIndex;

    public static TableView sahteYolcuTV;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sahteYolcuTV = yolcularTV;
        
        idCLM.setCellValueFactory(new PropertyValueFactory<>("id"));
        isimCLM.setCellValueFactory(new PropertyValueFactory<>("isim"));
        soyİsimCLM.setCellValueFactory(new PropertyValueFactory<>("soyİsim"));
        telefonCLM.setCellValueFactory(new PropertyValueFactory<>("telefonNo"));
        emailCLM.setCellValueFactory(new PropertyValueFactory<>("mail"));
        kullanıcıAdıCLM.setCellValueFactory(new PropertyValueFactory<>("kullaniciAdi"));

        for (int i=0 ; i< Main.yolcular.size() ; i++)
        {
            yolcularTV.getItems().add(Main.yolcular.get(i));
        }
        
        kaldırBtn.setOnAction(e -> kaldırBtnAction(e));

        kapatBtn.setOnAction(e ->
        {
            AdminSayfasıFXMLController.yolcuSayfasıAçıkMı = false;
            ((Stage)kapatBtn.getScene().getWindow()).close();

            if (editSayfasıAçıkMı)
            {
                editSayfasıAçıkMı = false;
                editStage.close();
            }
        });
        
        editBtn.setOnAction(e -> editBtnAction(e));
    }    
    
    private void editBtnAction(ActionEvent e)
    {
        if (yolcularTV.getSelectionModel().getSelectedItem() !=null)
        {

            Yolcu yolcu = (Yolcu) yolcularTV.getSelectionModel().getSelectedItem();

            for (int i=0 ; i<Main.yolcular.size() ; i++)
            {
                if (Main.yolcular.get(i).getId().equals(yolcu.getId()))
                {
                    editYolcuIndex = i;
                }
            }

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminYolcuDüzenleSayfasıFXML.fxml"));

            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            editStage = new Stage(StageStyle.UNDECORATED);
            editStage.setScene(new Scene(loader.getRoot()));
            editStage.show();

            editSayfasıAçıkMı = true;
            uyariLbl.setText("");
        }
        else
        {
            uyariLbl.setText("Bir yolcu seçin!!!");
        }
    }
    
    private void kaldırBtnAction(ActionEvent e)
    {

        if (yolcularTV.getSelectionModel().getSelectedItem() !=null)
        {
            Yolcu yolcuKaldır = (Yolcu) yolcularTV.getSelectionModel().getSelectedItem();

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminYolcuKaldır(yolcuKaldır);
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiYolcuKaldır(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex) , yolcuKaldır);
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }

            for (int i=0 ; i<Main.uçaklar.size() ; i++)
            {
                for (int j=0 ; j<Main.uçaklar.get(i).getUçuşlar().size() ; j++)
                {
                    for (int k=0 ; k<Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().size() ; k++)
                    {
                        if (Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().get(k).equals(yolcuKaldır))
                        {
                            Main.uçaklar.get(i).getUçuşlar().get(j).getYolcu().remove(k);
                            String temp = "";
                            temp += (Integer.parseInt(Main.uçaklar.get(i).getUçuşlar().get(j).getSatilanBiletSayisi())-1);
                            Main.uçaklar.get(i).getUçuşlar().get(j).setSatilanBiletSayisi(temp);
                        }
                    }
                }
            }
           
            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();

            for (int i=0 ; i<Main.uçuşlar.size() ; i++)
            {
                for (int j=0 ; j<Main.uçuşlar.get(i).getYolcu().size() ; j++)
                {
                    if (Main.uçuşlar.get(i).getYolcu().get(j).equals(yolcuKaldır))
                    {
                        Main.uçuşlar.get(i).getYolcu().remove(j);
                        String temp = "";
                        temp += (Integer.parseInt(Main.uçuşlar.get(i).getSatilanBiletSayisi())-1);
                        Main.uçuşlar.get(i).setSatilanBiletSayisi(temp);
                    }
                }
            }
            
            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            
            Main.yolcular.remove(yolcuKaldır);
            
            YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>( Main.yolcular , "Yolcu.txt");
            yolcuYazmaOkumaFile.writeList();

            yolcularTV.getItems().clear();
            for (int i=0 ; i< Main.yolcular.size() ; i++)
            {
                yolcularTV.getItems().add(Main.yolcular.get(i));
            }
            uyariLbl.setText("");
        }
        else
        {
            uyariLbl.setText("Bir yolcu seçin!!!");
        }
    }
}
