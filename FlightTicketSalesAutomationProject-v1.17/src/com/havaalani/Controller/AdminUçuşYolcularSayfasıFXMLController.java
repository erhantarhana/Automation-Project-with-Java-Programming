
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
import com.havaalani.Kullanicilar.Yolcu;
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


public class AdminUçuşYolcularSayfasıFXMLController implements Initializable {

    @FXML private Label uyariLbl;
    @FXML private Button kaldırBtn;
    @FXML private Label uçuşIdLbl;
    @FXML private TableView yolcularTV;
    @FXML private TableColumn isimCLM;
    @FXML private TableColumn soyİsimCLM;
    @FXML private TableColumn idCLM;
    @FXML private TableColumn kullaniciAdiCLM;
    @FXML private TableColumn telefonCLM;
    @FXML private TableColumn emailCLM;
    @FXML private Button kapatBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uçuşIdLbl.setText(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar()
                .get(AdminUçuşlarSayfasıFXMLController.yolcuUçuşİndex).getId());

        idCLM.setCellValueFactory(new PropertyValueFactory<>("id"));
        isimCLM.setCellValueFactory(new PropertyValueFactory<>("isim"));
        soyİsimCLM.setCellValueFactory(new PropertyValueFactory<>("soyİsim"));
        telefonCLM.setCellValueFactory(new PropertyValueFactory<>("telefonNo"));
        emailCLM.setCellValueFactory(new PropertyValueFactory<>("mail"));
        kullaniciAdiCLM.setCellValueFactory(new PropertyValueFactory<>("kullaniciAdi"));

        for (int i=0 ; i<Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex)
                .getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.yolcuUçuşİndex).getYolcu().size() ; i++)
        {
            yolcularTV.getItems().add(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex)
                    .getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.yolcuUçuşİndex).getYolcu().get(i));
        }
        System.out.println("jadval: "+Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex)
                .getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.yolcuUçuşİndex).getYolcu().size());

        kapatBtn.setOnAction(e -> {
            ((Stage) kapatBtn.getScene().getWindow()).close();

            AdminUçuşlarSayfasıFXMLController.yolcuStageAçıkMı = false;
        });

        kaldırBtn.setOnAction(e ->kaldırBtnAction(e));
    }    
    
     private void kaldırBtnAction(ActionEvent e)
    {
        if (yolcularTV.getSelectionModel().getSelectedItem() != null)
        {
            Yolcu yolcuKaldır =(Yolcu) yolcularTV.getSelectionModel().getSelectedItem();
            int index = -1;
            for (int i=0 ; i<Main.yolcular.size() ; i++)
            {
                if (Main.yolcular.get(i).getId().equals(yolcuKaldır.getId()))
                {
                    index = i;
                    break;
                }
            }

            System.out.println(index + "yolcu index");

            for (int k=0 ; k<Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).getYolcu().size() ; k++)
            {
                if (Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar()
                        .get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).getYolcu().get(k).getId().equals(Main.yolcular.get(index).getId()))
                {
                    Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar()
                            .get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).getYolcu().remove(k);

                    String temp = "";
                    temp += (Integer.parseInt(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar()
                            .get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).getSatilanBiletSayisi())-1);
                    Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar()
                            .get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).setSatilanBiletSayisi(temp);
                }
            }
            
            YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
            uçakYazmaOkumaFile.writeList();

            for (int j=0 ; j<Main.uçuşlar.get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).getYolcu().size() ; j++)
            {
                if (Main.uçuşlar.get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).getYolcu().get(j).getId().equals(Main.yolcular.get(index).getId()))
                {
                    Main.uçuşlar.get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).getYolcu().remove(j);
                    String temp = "";
                    temp += (Integer.parseInt((Main.uçuşlar.get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).getSatilanBiletSayisi()))-1);
                    Main.uçuşlar.get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex).setSatilanBiletSayisi(temp);
                }
            }
            
            YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
            uçuşYazmaOkumaFile.writeList();

            for (int i=0 ; i<Main.yolcular.get(index).getSatilanBiletler().size() ; i++)
            {
                if (Main.yolcular.get(index).getSatilanBiletler().get(i).getId().equals(
                        Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.yolcuUçuşİndex).getBilet().getId()
                ))
                {
                    
                    Main.yolcular.get(index).getSatilanBiletler().remove(i);

                    int eskiKredi = Integer.parseInt(yolcuKaldır.getKredi());
                    System.out.println(eskiKredi+"*old");
                    int ücretDöndür = Integer.parseInt(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex).getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.yolcuUçuşİndex).getBilet().getUcret());
                    System.out.println("price" + ücretDöndür);
                    int yeniKredi = eskiKredi+ücretDöndür;
                    System.out.println(yeniKredi+"new" );
                    String tempKredi = "";
                    tempKredi += yeniKredi;
                    Main.yolcular.get(index).setKredi(tempKredi);
                    System.out.println("yolcukredi "+Main.yolcular.get(index).getKredi());
                }
            }
            
            YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>( Main.yolcular , "Yolcu.txt");
            yolcuYazmaOkumaFile.writeList();

            yolcularTV.getItems().clear();

            for (int i=0 ; i<Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex)
                    .getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.yolcuUçuşİndex).getYolcu().size() ; i++)
            {
                yolcularTV.getItems().add(Main.uçaklar.get(AdminUçuşlarUçaklarSayfasıFXMLController.uçakIndex)
                        .getUçuşlar().get(AdminUçuşlarSayfasıFXMLController.yolcuUçuşİndex).getYolcu().get(i));
            }

            if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Çalışan)
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.çalışanUçuştakiYolcuKaldır(Main.çalışanlar.get(GirişSayfasıFXMLController.kullaniciİndex) , yolcuKaldır ,Main.uçuşlar.get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else if (GirişSayfasıFXMLController.pozisyon == GirişSayfasıFXMLController.pozisyon.Yönetici)
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yöneticiUçuştakiYolcuKaldır(Main.yöneticiler.get(GirişSayfasıFXMLController.kullaniciİndex),yolcuKaldır ,
                        Main.uçuşlar.get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            else
            {
                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.adminUçuştakiYolcuKaldır(yolcuKaldır ,
                        Main.uçuşlar.get(AdminUçuşlarSayfasıFXMLController.uçuşKaldırİndex));  
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
            uyariLbl.setText("");
        }
        else
        {
            uyariLbl.setText("Bir yolcu seçin!!!");
        }
    }   
}
