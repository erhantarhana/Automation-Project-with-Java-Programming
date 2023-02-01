
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Bilet;
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


public class AdminUçuşlarSayfasıFXMLController implements Initializable {

    @FXML private Label uyariLbl;
    @FXML private Button yolcularBtn;
    @FXML private Button editBtn;
    @FXML private Button ekleBtn;
    @FXML private Button kaldırBtn;
    @FXML  Label uçakLbl;
    @FXML private TableView uçuşlarTV;
    @FXML private TableColumn idCLM;
    @FXML private TableColumn başlangıçCLM;
    @FXML private TableColumn hedefCLM;
    @FXML private TableColumn tarihCLM;
    @FXML private TableColumn zamanCLM;
    @FXML private TableColumn süreCLM;
    @FXML private TableColumn biletNumarasiCLM;
    @FXML private TableColumn durumCLM;
    @FXML private TableColumn ücretCLM;
    @FXML private Button kapatBtn;

    public static int uçuşKaldırİndex;
    public static int uçuşEditİndex;
    public static int yolcuUçuşİndex;


    static boolean stageEkleAçıkMı = false;
    public static Stage stageEkle;

    static boolean stageEditAçıkMı = false;
    public static Stage editStage;

    static boolean yolcuStageAçıkMı = false;
    public static Stage yolcuStage;

    public  static TableView sahteUçuşlarTV;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sahteUçuşlarTV = uçuşlarTV;
        
        idCLM.setCellValueFactory(new PropertyValueFactory<>("id"));
        başlangıçCLM.setCellValueFactory(new PropertyValueFactory<>("bşlNoktasi"));
        hedefCLM.setCellValueFactory(new PropertyValueFactory<>("hedef"));
        tarihCLM.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        zamanCLM.setCellValueFactory(new PropertyValueFactory<>("kalkisZamani"));
        süreCLM.setCellValueFactory(new PropertyValueFactory<>("sure"));
        biletNumarasiCLM.setCellValueFactory(new PropertyValueFactory<>("satilanBiletSayisi"));
        durumCLM.setCellValueFactory(new PropertyValueFactory<>("uçuşDurumu"));
        ücretCLM.setCellValueFactory(new PropertyValueFactory<>("ucusUcreti"));
        
        for (int i = 0; i< Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().size() ; i++) {
            uçuşlarTV.getItems().add(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(i));
        }
        
        kapatBtn.setOnAction(e -> {
            ((Stage) kapatBtn.getScene().getWindow()).close();

            AdminUçuşlarUçaklarSayfasıFXMLController.uçuşStageAçıkMı = false;

            if (stageEkleAçıkMı )
            {
                stageEkle.close();
                stageEkleAçıkMı = false;

            }
            if (stageEditAçıkMı)
            {
                editStage.close();
                stageEditAçıkMı = false;
            }
            if (yolcuStageAçıkMı)
            {
                yolcuStage.close();
                yolcuStageAçıkMı = false;
            }
        });
        
        ekleBtn.setOnAction(e -> {

            if (!stageEkleAçıkMı)
            {
                
                FXMLLoader addloader = new FXMLLoader(Main.class.getResource("Fxml/AdminUçuşEkleSayfasıFXML.fxml"));

                try {
                    addloader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                stageEkle = new Stage(StageStyle.UNDECORATED);

                stageEkle.setScene(new Scene(addloader.getRoot()));
                stageEkle.show();

                stageEkle = stageEkle;

                stageEkleAçıkMı = true;
            }
        });
        
        editBtn.setOnAction(e -> {

            if (!stageEditAçıkMı)
            {
                if (uçuşlarTV.getSelectionModel().getSelectedItem()!=null)
                {
                    uçuşEditİndex = Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().indexOf(uçuşlarTV.getSelectionModel().getSelectedItem());
                    
                    FXMLLoader editLoader = new FXMLLoader(Main.class.getResource("Fxml/AdminUçuşDüzenleSayfasıFXML.fxml"));
                    try {
                        editLoader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    AdminUçuşDüzenleSayfasıFXMLController adminUçuşDüzenleSayfasıFXMLController = editLoader.getController();
                    adminUçuşDüzenleSayfasıFXMLController.idLbl.setText(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(uçuşEditİndex).getId());

                    editStage = new Stage(StageStyle.UNDECORATED);
                    editStage.setScene(new Scene(editLoader.getRoot()));
                    editStage.show();

                    stageEditAçıkMı = true;
                    uyariLbl.setText("");
                }
                else
                {
                    uyariLbl.setText("Bir uçuş seçin!!!");
                }
            }
        });
        
        kaldırBtn.setOnAction(e -> kaldırBtnAction(e));

        yolcularBtn.setOnAction( e -> {

            if (!yolcuStageAçıkMı)
            {
                if (uçuşlarTV.getSelectionModel().getSelectedItem() != null)
                {
                    yolcuUçuşİndex = Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().indexOf(uçuşlarTV.getSelectionModel().getSelectedItem());

                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminUçuşYolcularSayfasıFXML.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    yolcuStage = new Stage(StageStyle.UNDECORATED);
                    yolcuStage.setScene(new Scene(loader.getRoot()));
                    yolcuStage.show();

                    uyariLbl.setText("");
                    yolcuStageAçıkMı = true;
                }
                else
                {
                    uyariLbl.setText("Uçuş seçin!!!");
                }
            }
        });
    }    
    
     private void kaldırBtnAction(ActionEvent e)
    {
        if (uçuşlarTV.getSelectionModel().getSelectedItem()!=null)
        {
            uçuşKaldırİndex = Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().indexOf(uçuşlarTV.getSelectionModel().getSelectedItem());
            Uçuş uçuşKaldır = Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(uçuşKaldırİndex);


            Uçuş seçilenUçuş = (Uçuş) uçuşlarTV.getSelectionModel().getSelectedItem();
            int uçuşlardakiİndex = -1;
            int uçaktakiİndex = -1;
            int biletİndex = -1;

            for (int i=0 ; i<Main.biletler.size() ; i++)
            {
                if (Main.biletler.get(i).getId().equals(seçilenUçuş.getId()))
                {
                    biletİndex = i;
                    break;
                }
            }

            for (int i=0 ; i<Main.uçuşlar.size() ; i++)
            {
                if (Main.uçuşlar.get(i).getId().equals(seçilenUçuş.getId()))
                {
                    uçuşlardakiİndex = i;
                    break;
                }
            }

            for (int i=0 ; i<Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().size() ; i++)
            {
                if (Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(i).getId()
                    .equals(seçilenUçuş.getId()))
                {
                    uçaktakiİndex=i;
                    break;
                }
            }

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminUçuşKaldır(Main.uçuşlar.get(uçuşlardakiİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiUçuşKaldır(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex) , Main.uçuşlar.get(uçuşlardakiİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.çalışanUçuşKaldır(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex ), Main.uçuşlar.get(uçuşlardakiİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }

            for (int i=0 ; i<Main.yolcular.size() ; i++)
            {
                for (int j=0 ; j<Main.yolcular.get(i).getSatilanBiletler().size() ; j++)
                {
                    if (Main.yolcular.get(i).getSatilanBiletler().get(j).getId().equals(seçilenUçuş.getBilet().getId()))
                    {
                        
                        int eskiKredi = Integer.parseInt(Main.yolcular.get(i).getKredi());
                        int ücret = Integer.parseInt(seçilenUçuş.getBilet().getUcret());
                        int yeniKredi = eskiKredi + ücret;
                        String tempKredi = "";
                        tempKredi += yeniKredi;
                        Main.yolcular.get(i).setKredi(tempKredi);

                        Main.yolcular.get(i).getSatilanBiletler().remove(j);
                    }
                }
            }
            
            YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>( Main.yolcular , "Yolcu.txt");
            yolcuYazmaOkumaFile.writeList();

            Main.biletler.remove(biletİndex);
            
            YazmaOkumaFile<Bilet> biletYazmaOkumaFile = new YazmaOkumaFile<>(Main.biletler , "Biletler.txt");
            biletYazmaOkumaFile.writeList();

            
            Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().remove(uçaktakiİndex);
            
            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();

            Main.uçuşlar.remove(uçuşlardakiİndex);
            
            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.getItems().clear();

            for (int i = 0; i< Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().size() ; i++)
            {
                AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.getItems().add(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(i));
            }

            AdminUçuşlarSayfasıFXMLController.sahteUçuşlarTV.refresh();
   
            uyariLbl.setText("");
        }
        else
        {
            uyariLbl.setText("Bir uçuş seçin!!!");
        }
    }
}
