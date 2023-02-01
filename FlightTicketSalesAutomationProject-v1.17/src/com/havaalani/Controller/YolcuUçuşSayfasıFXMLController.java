
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Bilet;
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


public class YolcuUçuşSayfasıFXMLController implements Initializable {

    @FXML private Button çıkışBtn;
    @FXML private TableView uçuşlarTV;
    @FXML private TableColumn baslangicCLM;
    @FXML private TableColumn hedefCLM;
    @FXML private TableColumn tarihCLM;
    @FXML private TableColumn zamanCLM;
    @FXML private TableColumn durumCLM;
    @FXML private TableColumn ucretCLM;
    @FXML private TableColumn cezaCLM;
    @FXML private Button iptalBtn;
    @FXML private Label uyariLbl;
    
    static TableView sahteUçuşTV;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sahteUçuşTV = uçuşlarTV;
        
        baslangicCLM.setCellValueFactory(new PropertyValueFactory<>("bşlNoktasi"));
        hedefCLM.setCellValueFactory(new PropertyValueFactory<>("hedef"));
        tarihCLM.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        zamanCLM.setCellValueFactory(new PropertyValueFactory<>("kalkisZamani"));
        durumCLM.setCellValueFactory(new PropertyValueFactory<>("uçuşDurumu"));
        ucretCLM.setCellValueFactory(new PropertyValueFactory<>("ucusUcreti"));
        cezaCLM.setCellValueFactory(new PropertyValueFactory<>("uçuşCeza"));
        
        for (int i=0 ; i< Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().size() ; i++)
        {
            for (int j=0 ; j<Main.uçuşlar.size() ; j++)
            {
                if (Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().get(i).getId().equals(Main.uçuşlar.get(j).getId()))
                {
                    uçuşlarTV.getItems().add(Main.uçuşlar.get(j));
                }
            }
        }
        
        çıkışBtn.setOnAction(e -> {
            
            BölmedenSekmeyiKaldir sekmeKaldır = new BölmedenSekmeyiKaldir();
            int index = YolcuSayfasıFXMLController.sahteYolcuPageTP.getTabs().indexOf(YolcuSayfasıFXMLController.uçuşlarTab);
            sekmeKaldır.sekmeKaldır(YolcuSayfasıFXMLController.sahteYolcuPageTP, index);

            YolcuSayfasıFXMLController.uçuşlarTabAçıkMı = false;
        });

        iptalBtn.setOnAction(e -> iptaBtnAction(e));
    }    
    
    private void iptaBtnAction(ActionEvent e)
    {
        if (uçuşlarTV.getSelectionModel().getSelectedItem() != null)
        {
            Uçuş uçuşKaldır = (Uçuş) uçuşlarTV.getSelectionModel().getSelectedItem();

            if (uçuşKaldır.getUçuşDurumu()== Uçuş.Durum.BİTMEDİ)
            {
                int uçuşİndex = -1;

                Bilet biletKaldır = uçuşKaldır.getBilet();
                int biletİndex = -1;

                Uçak uçakKaldır = uçuşKaldır.getUçak();
                int uçakİndex = -1;


                for (int i=0 ; i<Main.uçuşlar.size() ; i++)
                {
                    if (Main.uçuşlar.get(i).getId().equals(uçuşKaldır.getId()))
                    {
                        uçuşİndex = i;
                        break;
                    }
                }

                for (int i=0 ; i<Main.uçaklar.size() ; i++)
                {
                    if (Main.uçaklar.get(i).getId().equals(uçakKaldır.getId()))
                    {
                        uçakİndex = i;
                        break;
                    }
                }

                for (int i=0 ; i<Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().size() ; i++)
                {
                    if (Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().get(i).getId().equals(biletKaldır.getId()))
                    {
                        Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().remove(i);
                        break;
                    }
                }

//                float ceza = Integer.parseInt(biletKaldır.getUcret().substring(0,biletKaldır.getUcret().length()-1));
//                float kasr = (100-ceza)/100;
//                int ücret = (int) (Integer.parseInt(biletKaldır.getUcret())*(kasr));
                int ücret = -2000;
                int eskiKredi = Integer.parseInt(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
                int yeniKredi = ücret+eskiKredi;
                String tempKredi = "";
                tempKredi += yeniKredi;
                Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).setKredi(tempKredi);
                
                YolcuSayfasıFXMLController.sahteKrediLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());

                
                YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>(Main.yolcular , "Yolcu.txt");
                yolcuYazmaOkumaFile.writeList();


                for (int i=0 ; i < Main.uçuşlar.get(uçuşİndex).getYolcu().size() ; i++)
                {
                    if (Main.uçuşlar.get(uçuşİndex).getYolcu().get(i).getId().equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()))
                    {
                        Main.uçuşlar.get(uçuşİndex).getYolcu().remove(i);
                        break;
                    }
                }
                
                String tempNo = "";
                tempNo += (Integer.parseInt(Main.uçuşlar.get(uçuşİndex).getSatilanBiletSayisi())-1);
                Main.uçuşlar.get(uçuşİndex).setSatilanBiletSayisi(tempNo);
                
                YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
                uçuşYazmaOkumaFile.writeList();


                for (int i=0 ; i<Main.uçaklar.get(uçakİndex).getUçuşlar().size() ; i++)
                {
                    if (Main.uçaklar.get(uçakİndex).getUçuşlar().get(i).getId().equals(uçuşKaldır.getId()
                    ))
                    {
                        for (int j=0 ; j<Main.uçaklar.get(uçakİndex).getUçuşlar().get(i).getYolcu().size() ; j++)
                        {
                            if (Main.uçaklar.get(uçakİndex).getUçuşlar().get(i).getYolcu().get(j).getId().equals( Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()
                            ))
                            {
                                Main.uçaklar.get(uçakİndex).getUçuşlar().get(i).getYolcu().remove(j);
                                break;
                            }
                        }
                        break;
                    }
                }
                
                YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
                uçakYazmaOkumaFile.writeList();
          
                uçuşlarTV.getItems().clear();

                for (int i=0 ; i< Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().size() ; i++)
                {
                    for (int j=0 ; j<Main.uçuşlar.size() ; j++)
                    {
                        if (Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().get(i).getId().equals(Main.uçuşlar.get(j).getId()))
                        {
                            uçuşlarTV.getItems().add(Main.uçuşlar.get(j));
                        }
                    }
                }

                uçuşlarTV.refresh();

                
                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yolcuBiletİptal(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex) , Main.uçuşlar.get(uçuşİndex).getBilet());
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();

                uyariLbl.setText("");
            }
            else
            {
                uyariLbl.setText("Bu uçuş zaten bitti!!!");
            }

        }
        else
        {
            uyariLbl.setText("Uçuş seçin!!!");
        }
    }   
}
