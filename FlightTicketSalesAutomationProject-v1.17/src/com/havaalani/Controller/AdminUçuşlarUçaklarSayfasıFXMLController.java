
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Bilet;
import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
import com.havaalani.Kullanicilar.Yolcu;
import com.havaalani.Main;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminUçuşlarUçaklarSayfasıFXMLController implements Initializable {

    @FXML private Button uçakEkleBtn;
    @FXML private TextField koltukSayisiTxtField;
    @FXML private TextField idTxtField;
    @FXML private Button kapatBtn;
    @FXML private TableView uçaklarTV;
    @FXML private TableColumn idTC;
    @FXML private TableColumn koltuklarTC;
    @FXML private Button uçuşlarBtn;
    @FXML private Button kaldırBtn;
    @FXML private Button editBtn;
    @FXML private Label uyariLbl;
    @FXML private TextField editKoltukTxtField;

    static boolean uçuşStageAçıkMı = false;
    public static Stage uçuşStage;

    public static int uçakIndex = -1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        koltuklarTC.setCellValueFactory(new PropertyValueFactory<>("koltuklar"));
        for (int i=0 ; i<Main.uçaklar.size() ; i++)
        {
            uçaklarTV.getItems().add(Main.uçaklar.get(i));
        }
        
        kapatBtn.setOnAction(e -> {

            BölmedenSekmeyiKaldir bölmedenSekmeyiKaldir = new BölmedenSekmeyiKaldir();
            int index = AdminSayfasıFXMLController.sahteAdminSayfasıTP.getTabs().indexOf(AdminSayfasıFXMLController.uçaklarUçuşlarTab);
            bölmedenSekmeyiKaldir.sekmeKaldır(AdminSayfasıFXMLController.sahteAdminSayfasıTP, index);

            if (uçuşStageAçıkMı)
            {
                uçuşStage.close();
                uçuşStageAçıkMı = false;
                if (AdminUçuşlarSayfasıFXMLController.stageEkleAçıkMı )
                {
                    AdminUçuşlarSayfasıFXMLController.stageEkle.close();
                    AdminUçuşlarSayfasıFXMLController.stageEkleAçıkMı = false;


                }
                if (AdminUçuşlarSayfasıFXMLController.stageEditAçıkMı)
                {
                    AdminUçuşlarSayfasıFXMLController.editStage.close();
                    AdminUçuşlarSayfasıFXMLController.stageEditAçıkMı = false;
                }
                if (AdminUçuşlarSayfasıFXMLController.yolcuStageAçıkMı)
                {
                    AdminUçuşlarSayfasıFXMLController.yolcuStage.close();
                    AdminUçuşlarSayfasıFXMLController.yolcuStageAçıkMı = false;
                }
            }

            AdminSayfasıFXMLController.uçaklarUçuşlarTabAçıkMı = false;
        });
        
        uçakEkleBtn.setOnAction(e -> {
            if (GirişSayfasıFXMLController.pozisyon!= GirişSayfasıFXMLController.pozisyon.Çalışan)
            {
                try {
                    uçakEkleBtnAction(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                uyariLbl.setText("");
            }
            else
            {
                uyariLbl.setText("Erişilemez!!!");
            }
        });
        
        editBtn.setOnAction(e ->{
            if (GirişSayfasıFXMLController.pozisyon!= GirişSayfasıFXMLController.pozisyon.Çalışan)
            {
                editBtnAction(e);
            }
            else
            {
                uyariLbl.setText("Erişilemez!!!");
            }
            });

        kaldırBtn.setOnAction(e -> {
            if (GirişSayfasıFXMLController.pozisyon!= GirişSayfasıFXMLController.pozisyon.Çalışan)
            {
                try {
                    kaldirBtnAction(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else
            {
                uyariLbl.setText("Erişilemez!!!");
            }
        });
        
        uçuşlarBtn.setOnAction(e ->{
            
            if (uçaklarTV.getSelectionModel().getSelectedItem()!=null)
            {

                if (!uçuşStageAçıkMı)
                {

                    uçakIndex = Main.uçaklar.indexOf(uçaklarTV.getSelectionModel().getSelectedItem());

                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Fxml/AdminUçuşlarSayfasıFXML.fxml"));

                    try {
                        fxmlLoader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    AdminUçuşlarSayfasıFXMLController adminUçuşlarSayfasıFXMLController = fxmlLoader.getController();
                    adminUçuşlarSayfasıFXMLController.uçakLbl.setText(Main.uçaklar.get(uçakIndex).getId());

                    Stage uçuşlarStage =new Stage(StageStyle.UNDECORATED);
                    uçuşlarStage.setScene(new Scene(fxmlLoader.getRoot()));
                    uçuşlarStage.show();

                    uçuşStage = uçuşlarStage;

                    uyariLbl.setText("");

                    uçuşStageAçıkMı = true;
                }
            }
            else
            {
                uyariLbl.setText("Bir uçak seçin!!!");
            }
        });
        
    }    
    
    private void kaldirBtnAction(ActionEvent e) throws IOException {

        if (uçaklarTV.getSelectionModel().getSelectedItem() == null)
        {
            uyariLbl.setText("Bir uçak seçin!!!");
        }
        else {

            Uçak uçakKaldır = (Uçak)uçaklarTV.getSelectionModel().getSelectedItem();
            int index = -1;
            
            for (int i=0 ; i<Main.uçaklar.size() ; i++)
            {
                if (Main.uçaklar.get(i).getId().equals(uçakKaldır.getId()))
                {
                    index = i;
                    break;
                }
            }



            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
            {
               
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminUçakKaldır(uçakKaldır);
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiUçakKaldır(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex) , uçakKaldır);
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }

        String uçuşlarBilgi = "";
        for (int i=0 ; i<Main.uçaklar.get(index).getUçuşlar().size() ; i++)
        {
            if (i==Main.uçaklar.get(index).getUçuşlar().size()-1)
            {
                uçuşlarBilgi+=Main.uçaklar.get(index).getUçuşlar().get(i).getId();
            }
            else
            {
                uçuşlarBilgi+=Main.uçaklar.get(index).getUçuşlar().get(i).getId() + ";";
            }

        }

        String eskiSatır = Main.uçaklar.get(index).getId()+" "+Main.uçaklar.get(index).getKoltuklar()+" "+uçuşlarBilgi;

        DosyadaSatırKaldırma dosyadaSatırKaldırma = new DosyadaSatırKaldırma();
        dosyadaSatırKaldırma.satırKaldır("Uçaklar.txt" , eskiSatır);


            

            for (int i=0 ; i<Main.uçaklar.get(index).getUçuşlar().size() ; i++)
            {
                for (int j = 0; j < Main.yolcular.size(); j++) {
                    for (int l=0 ; l<Main.yolcular.get(j).getSatilanBiletler().size() ; l++)
                    {
                        if (Main.yolcular.get(j).getSatilanBiletler().get(l).getId().equals(Main.uçaklar.get(index).getUçuşlar().get(i).getBilet().getId()))
                        {
                            
                            int eskiKredi = Integer.parseInt(Main.yolcular.get(j).getKredi());
                            int ücret = Integer.parseInt(Main.uçaklar.get(index).getUçuşlar().get(i).getBilet().getUcret());
                            int yeniKredi = eskiKredi+ücret;
                            String tempKredi = "";
                            tempKredi += yeniKredi;
                            Main.yolcular.get(j).setKredi(tempKredi);

                            Main.yolcular.get(j).getSatilanBiletler().remove(l);
                        }
                    }
                }
            }

            YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>( Main.yolcular , "Yolcu.txt");
            yolcuYazmaOkumaFile.writeList();

            
            for (int i=0 ; i<Main.uçaklar.get(index).getUçuşlar().size() ; i++)
            {
                for (int j = 0; j < Main.biletler.size(); j++) {
                    if (Main.uçaklar.get(index).getUçuşlar().get(i).getBilet().getId().equals(Main.biletler.get(j).getId()))
                    {
                        Main.biletler.remove(j);
                    }
                }
            }

            YazmaOkumaFile<Bilet> biletYazmaOkumaFile = new YazmaOkumaFile<>(Main.biletler , "Biletler.txt");
            biletYazmaOkumaFile.writeList();

            
            for (int i = 0; i < Main.uçaklar.get(index).getUçuşlar().size(); i++) {
                for (int j = 0; j < Main.uçuşlar.size(); j++) {
                    if (Main.uçuşlar.get(j).getId().equals(Main.uçaklar.get(index).getUçuşlar().get(i).getId())) {
                        Main.uçuşlar.remove(j);
                        break;
                    }
                }
            }
            
            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            Main.uçaklar.remove(index);

            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();

            uçaklarTV.getItems().clear();

            for (int i = 0; i < Main.uçaklar.size(); i++) {
                uçaklarTV.getItems().add(Main.uçaklar.get(i));
            }

            uçaklarTV.refresh();

        }
    }
    
    private void editBtnAction(ActionEvent e)
    {
        if (uçaklarTV.getSelectionModel().getSelectedItem()==null)
        {
            uyariLbl.setText("Uçak seçin!!!");
        }
        else
        {
            if (editKoltukTxtField.getText().isEmpty())
            {
                uyariLbl.setText("Tüm alanı doldurun!!!");
            }
            else if (!editKoltukTxtField.getText().matches("[0-9]+"))
            {
                uyariLbl.setText("Geçersiz format!!!");
            }
            else if (editKoltukTxtField.getText().matches("[0]+"))
            {
                uyariLbl.setText("Koltuk sayısı sıfır olamaz!!!");
            }
            else if (editKoltukTxtField.getText().substring(0,1).equals("0"))
            {
                uyariLbl.setText("Numaranızın başındaki herhangi bir sıfırı kaldırın!!!");
            }
            else
            {

                int index = Main.uçaklar.indexOf(uçaklarTV.getSelectionModel().getSelectedItem());

                Main.uçaklar.get(index).setKoltuklar(editKoltukTxtField.getText());

                YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar , "Uçaklar.txt");
                uçakYazmaOkumaFile.writeList();

                uyariLbl.setText("");
                editKoltukTxtField.setText("");

                uçaklarTV.getItems().clear();
         
                for (int i=0 ; i<Main.uçaklar.size() ; i++)
                {
                    uçaklarTV.getItems().add(Main.uçaklar.get(i));
                }
                uçaklarTV.refresh();
            }
        }
    }
    
    private void uçakEkleBtnAction(ActionEvent e) throws IOException {
        if (idTxtField.getText().isEmpty() || koltukSayisiTxtField.getText().isEmpty())
        {
            uyariLbl.setText("Her iki alanı da doldurun!!!");
        }
        else if (!idTxtField.getText().matches("[0-9]+") || !koltukSayisiTxtField.getText().matches("[0-9]+"))
        {
            uyariLbl.setText("Geçersiz format!!!");
        }
        else if (koltukSayisiTxtField.getText().matches("[0]+"))
        {
            uyariLbl.setText("Koltuk sayısı sıfır olamaz!!!");
        }
        else if (koltukSayisiTxtField.getText().substring(0,1).equals("0"))
        {
            uyariLbl.setText("Numaranızın başındaki herhangi bir sıfırı kaldırın!!!");
        }
        else if(ayniHesapKontrol())
        {
            uyariLbl.setText("Bu kimliğe sahip bir uçak zaten var!!!");
        }
        else
        {

            Uçak yeniUçak = new Uçak(idTxtField.getText() , koltukSayisiTxtField.getText());
            Main.uçaklar.add(yeniUçak);

            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar , "Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Uçaklar.txt" , true));
            String kayıtEdilen = yeniUçak.getId()+" "+yeniUçak.getKoltuklar();
            bufferedWriter.write(kayıtEdilen);
            bufferedWriter.newLine();
            bufferedWriter.close();

            uyariLbl.setText("");
            idTxtField.setText("");
            koltukSayisiTxtField.setText("");

            uçaklarTV.getItems().add(Main.uçaklar.get(Main.uçaklar.size()-1));

            uçaklarTV.refresh();

             if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Admin)
             {
                 
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminUçakEkle(yeniUçak);
                 
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
             }
             else
             {
                 
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiUçakEkle(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex) , yeniUçak);
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
             }
        }
    }
    
     private boolean ayniHesapKontrol(){
        for (int i=0 ; i<Main.uçaklar.size() ; i++)
        {
            if (idTxtField.getText().equals(Main.uçaklar.get(i).getId()))
            {
                return true;
            }
        }
        return false;
    }
}
