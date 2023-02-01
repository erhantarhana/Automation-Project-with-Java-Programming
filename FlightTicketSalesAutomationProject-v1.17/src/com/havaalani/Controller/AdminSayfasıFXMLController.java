
package com.havaalani.Controller;

import com.havaalani.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminSayfasıFXMLController implements Initializable {

    @FXML private TabPane adminTP;
    @FXML private Button finansBirimBtn;
    @FXML  Label bilgiLbl;
    @FXML private Button mesajBtn;
    @FXML private Button kayıtlarBtn;
    @FXML private Button uçakUçuşBtn;
    @FXML private Button çıkışBtn;
    @FXML private Button YöneticiBtn;
    @FXML private Button profilBtn;
    @FXML private Button çalışanBtn;
    @FXML  Label pozisyonLbl;
    @FXML private Button yolcuBtn;
    @FXML private Label erişimLbl;
    
    static Tab uçaklarUçuşlarTab;
    static Tab profilTab;
    static Tab yöneticiKayitTab;
    static Tab çalışanKayitTab;
    
    static boolean uçaklarUçuşlarTabAçıkMı = false;
    static boolean profilTabAçıkMı = false;
    static boolean yolcuSayfasıAçıkMı = false;
    static boolean yöneticiKayıtTabAçıkMı = false;
    static boolean çalışanKayıtTabAçıkMı = false;
    static boolean mesajSohbetStageAçıkMı = false;
    static boolean mesajSayfasıAçıkMı = false;
    static boolean kayıtSayfasıAçıkMı = false;
    
    static Stage yolcuStage;
    static Stage mesajSohbetStage;
    static Stage mesajSayfasıStage;
    static Stage kayıtSayfasıStage;

    public static TabPane sahteAdminSayfasıTP;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sahteAdminSayfasıTP = adminTP;
        
        çıkışBtn.setOnAction(e -> {
            profilTabAçıkMı = false;
            
            if (uçaklarUçuşlarTabAçıkMı)
            {
                if (AdminUçuşlarUçaklarSayfasıFXMLController.uçuşStageAçıkMı) {
                    AdminUçuşlarUçaklarSayfasıFXMLController.uçuşStage.close();
                    AdminUçuşlarUçaklarSayfasıFXMLController.uçuşStageAçıkMı = false;
                }
                if (AdminUçuşlarSayfasıFXMLController.stageEditAçıkMı)
                {
                    AdminUçuşlarSayfasıFXMLController.stageEditAçıkMı = false;
                    AdminUçuşlarSayfasıFXMLController.editStage.close();
                }
                if (AdminUçuşlarSayfasıFXMLController.stageEkleAçıkMı)
                {
                    AdminUçuşlarSayfasıFXMLController.stageEkleAçıkMı=false;
                    AdminUçuşlarSayfasıFXMLController.stageEkle.close();
                }
                if (AdminUçuşlarSayfasıFXMLController.yolcuStageAçıkMı)
                {
                    AdminUçuşlarSayfasıFXMLController.yolcuStage.close();
                    AdminUçuşlarSayfasıFXMLController.yolcuStageAçıkMı = false;
                }
                uçaklarUçuşlarTabAçıkMı = false;
                yöneticiKayıtTabAçıkMı = false;
            }
            
            if (yolcuSayfasıAçıkMı)
            {
                yolcuSayfasıAçıkMı = false;
                yolcuStage.close();

                if (AdminYolcularSayfasıFXMLController.editSayfasıAçıkMı)
                {
                    AdminYolcularSayfasıFXMLController.editSayfasıAçıkMı = false;
                    AdminYolcularSayfasıFXMLController.editStage.close();
                }
            }

            if (AdminYöneticiKayıtSayfasıFXMLController.yöneticiListeSayfasıAçıkMı)
            {
                AdminYöneticiKayıtSayfasıFXMLController.yöneticiListeStage.close();
                AdminYöneticiKayıtSayfasıFXMLController.yöneticiListeSayfasıAçıkMı = false;

                if (AdminYöneticiListeSayfasıFXMLController.editYöneticiSayfasıAçıkMı)
                {
                    AdminYöneticiListeSayfasıFXMLController.editYöneticiStage.close();
                    AdminYöneticiListeSayfasıFXMLController.editYöneticiSayfasıAçıkMı = false;
                }
            }

            if (AdminÇalışanKayıtSayfasıFXMLController.çalışanListeAçıkMı)
            {
                AdminÇalışanKayıtSayfasıFXMLController.çalışanListeAçıkMı = false;
                AdminÇalışanKayıtSayfasıFXMLController.çalışanListeStage.close();

                if (AdminÇalışanListeSayfasıFXMLController.editÇalışanSayfasıAçıkMı)
                {
                    AdminÇalışanListeSayfasıFXMLController.editÇalışanSayfasıAçıkMı = false;
                    AdminÇalışanListeSayfasıFXMLController.editÇalışanStage.close();
                }
            }

            if (mesajSohbetStageAçıkMı)
            {
                mesajSohbetStage.close();
                mesajSohbetStageAçıkMı = false;
            }

            if (mesajSayfasıAçıkMı)
            {
                mesajSohbetStageAçıkMı = false;
                mesajSayfasıStage.close();
            }

            ((Stage)çıkışBtn.getScene().getWindow()).close();
            Main.loginPageStage.show();

            if (kayıtSayfasıAçıkMı)
            {
                kayıtSayfasıAçıkMı = false;
                kayıtSayfasıStage.close();
            }
        });
        
        uçakUçuşBtn.setOnAction(e -> {

            if (!uçaklarUçuşlarTabAçıkMı)
            {
                FXMLLoader uçakUçuşLoader = new FXMLLoader(Main.class.getResource("Fxml/AdminUçuşlarUçaklarSayfasıFXML.fxml"));

                try {
                    uçakUçuşLoader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                uçaklarUçuşlarTab = new Tab("Uçaklar");
                uçaklarUçuşlarTab.setStyle("-fx-background-color : EE82EE");
                uçaklarUçuşlarTab.setContent(uçakUçuşLoader.getRoot());
                adminTP.getTabs().add(uçaklarUçuşlarTab);
                erişimLbl.setText("");
                uçaklarUçuşlarTabAçıkMı = true;
            }
        });
        
        YöneticiBtn.setOnAction(e -> {

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminYöneticiKayıtSayfasıFXML.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                yöneticiKayitTab = new Tab("Yönetici Kayıt");
                yöneticiKayitTab.setStyle("-fx-background-color : EE82EE");
                yöneticiKayitTab.setContent(loader.getRoot());
                adminTP.getTabs().add(yöneticiKayitTab);

                erişimLbl.setText("");
                yöneticiKayıtTabAçıkMı = true;
                erişimLbl.setText("");
            }else
            {
                erişimLbl.setText("Erişilemez!!!");
            }
        });
        
        çalışanBtn.setOnAction( e -> {
            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin ||
                    GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminÇalışanKayıtSayfasıFXML.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                çalışanKayitTab=new Tab("Çalışan Kayıt");
                çalışanKayitTab.setStyle("-fx-background-color : EE82EE");
                çalışanKayitTab.setContent(loader.getRoot());
                adminTP.getTabs().add(çalışanKayitTab);

                erişimLbl.setText("");
                çalışanKayıtTabAçıkMı = true;
            }
            else
            {
                erişimLbl.setText("Erişilemez!!!");
            }
        });
        
        profilBtn.setOnAction(e -> {

            {
                if (!profilTabAçıkMı)
                {
                    FXMLLoader saEditProfLoader = new FXMLLoader(Main.class.getResource("Fxml/AdminProfilDüzenleSayfasıFXML.fxml"));
                    try {
                        saEditProfLoader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    profilTab=new Tab("Profil");
                    profilTab.setStyle("-fx-background-color: EE82EE");
                    profilTab.setContent(saEditProfLoader.getRoot());
                    adminTP.getTabs().add(profilTab);

                    erişimLbl.setText("");
                    profilTabAçıkMı = true;
                }
            }
        });
 
        kayıtlarBtn.setOnAction(e -> {
            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
                if (!kayıtSayfasıAçıkMı)
                {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminKayıtlarSayfasıFXML.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    kayıtSayfasıStage = new Stage(StageStyle.UNDECORATED);
                    kayıtSayfasıStage.setScene(new Scene(loader.getRoot()));
                    kayıtSayfasıStage.show();

                    kayıtSayfasıAçıkMı = true;
                }
                erişimLbl.setText("");
            }
            else
            {
                erişimLbl.setText("Erişilemez!!!");
            }
        });
        
        mesajBtn.setOnAction(e -> {

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin ||
                    GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
 
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/MesajSohbetSayfasıFXML.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                mesajSohbetStage = new Stage(StageStyle.UNDECORATED);
                mesajSohbetStage.setScene(new Scene(loader.getRoot()));
                mesajSohbetStage.show();

                erişimLbl.setText("");
                mesajSohbetStageAçıkMı = true;
            }
            else
            {
                if (!mesajSayfasıAçıkMı)
                {
                    FXMLLoader messageLoader = new FXMLLoader(Main.class.getResource("Fxml/MesajSayfasıFXML.fxml"));

                    try {
                        messageLoader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    MesajSayfasıFXMLController mesajSayfasıFXMLController = messageLoader.getController();
                    mesajSayfasıFXMLController.gönderen = "Çalışan";

                    mesajSayfasıStage = new Stage(StageStyle.UNDECORATED);
                    mesajSayfasıStage.setScene(new Scene(messageLoader.getRoot()));
                    mesajSayfasıStage.show();

                    mesajSayfasıAçıkMı = true;
                    erişimLbl.setText("");
                }
            }
        });
        
        yolcuBtn.setOnAction(e -> {

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin ||
                    GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                if (!yolcuSayfasıAçıkMı)
                {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/AdminYolcularSayfasıFXML.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    yolcuStage = new Stage(StageStyle.UNDECORATED);
                    yolcuStage.setScene(new Scene(loader.getRoot()));
                    yolcuStage.show();

                    erişimLbl.setText("");
                    yolcuSayfasıAçıkMı = true;
                }
            }

            else
            {
                erişimLbl.setText("Erişilemez!!!");
            }
        });
    }    
}
