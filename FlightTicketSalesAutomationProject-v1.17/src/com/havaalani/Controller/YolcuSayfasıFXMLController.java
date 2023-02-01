
package com.havaalani.Controller;

import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
import com.havaalani.Kullanicilar.Yolcu;
import com.havaalani.Main;
import java.io.BufferedReader;
import java.io.FileReader;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class YolcuSayfasıFXMLController implements Initializable {

    @FXML private TabPane passangerPageTP;
    @FXML private Button mesajBtn;
    @FXML private Label bilgiLbl;
    @FXML private Button profilBtn;
    @FXML private Button çıkışBtn;
    @FXML private TableView uçuşlarTV;
    @FXML private TableColumn baslangıcCLM;
    @FXML private TableColumn hedefCLM;
    @FXML private TableColumn tarihCLM;
    @FXML private TableColumn zamanCLM;
    @FXML private TableColumn ucretCLM;
    @FXML private Button satinAlBtn;
    @FXML private Button biletlerimBtn;
    @FXML private Label krediLbl;
    @FXML private Label uyariLbl;
    @FXML private TextField krediArttırTxtField;
    @FXML private Button krediArttırBtn;

    static boolean profilTabAçıkMı = false;
    static boolean mesajPageAçıkMı = false;
    static boolean uçuşlarTabAçıkMı = false;
    
    static Tab profilTab;
    static Tab uçuşlarTab;
    
    public static TabPane sahteYolcuPageTP;

    public static Label sahteKrediLbl;

    Stage mesajStage;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sahteYolcuPageTP = passangerPageTP;

        sahteKrediLbl = krediLbl;

        bilgiLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim()+ " "+Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim());
        krediLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
        
        baslangıcCLM.setCellValueFactory(new PropertyValueFactory<>("bşlNoktasi"));
        hedefCLM.setCellValueFactory(new PropertyValueFactory<>("hedef"));
        tarihCLM.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        zamanCLM.setCellValueFactory(new PropertyValueFactory<>("kalkisZamani"));
        ucretCLM.setCellValueFactory(new PropertyValueFactory<>("ucusUcreti"));
        
        System.out.println("size:"+Main.uçuşlar.size());
        for (int i=0 ; i<Main.uçuşlar.size() ; i++)
        {
            if (Main.uçuşlar.get(i).getUçuşDurumu() == Uçuş.Durum.BİTMEDİ)
            {
                uçuşlarTV.getItems().add(Main.uçuşlar.get(i));
            }
        }
        
        çıkışBtn.setOnAction(e -> {

            if (mesajPageAçıkMı)
            {
                mesajStage.close();
            }

            profilTabAçıkMı = false;
            mesajPageAçıkMı = false;
            ((Stage)çıkışBtn.getScene().getWindow()).close();
            Main.loginPageStage.show();
        });
        
        krediArttırBtn.setOnAction(e -> {
            try {
                krediArttırBtnAction(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        profilBtn.setOnAction(e -> {
            if (!profilTabAçıkMı)
            {
                FXMLLoader profilEditLoader = new FXMLLoader(Main.class.getResource("Fxml/YolcuProfilDüzenleSayfasıFXML.fxml"));

                try {
                    profilEditLoader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                profilTab = new Tab("Profil");
                profilTab.setStyle("-fx-background-color: slategray");
                profilTab.setContent(profilEditLoader.getRoot());
                passangerPageTP.getTabs().add(profilTab);

                profilTabAçıkMı = true;
            }
        });
        
        biletlerimBtn.setOnAction(e -> {

            if (!uçuşlarTabAçıkMı)
            {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fxml/YolcuUçuşSayfasıFXML.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                uçuşlarTab = new Tab("Uçuşlar");
                uçuşlarTab.setStyle("-fx-background-color: slategray");
                uçuşlarTab.setContent(loader.getRoot());
                passangerPageTP.getTabs().add(uçuşlarTab);

                uçuşlarTabAçıkMı = true;
            }
        });
        
        mesajBtn.setOnAction(e ->{

            if (!mesajPageAçıkMı)
            {
                FXMLLoader mesajLoader = new FXMLLoader(Main.class.getResource("Fxml/MesajSayfasıFXML.fxml"));

                try {
                    mesajLoader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                MesajSayfasıFXMLController mesajSayfasıFXMLController = mesajLoader.getController();
                mesajSayfasıFXMLController.gönderen = "Yolcu";

                mesajStage = new Stage(StageStyle.UNDECORATED);
                mesajStage.setScene(new Scene(mesajLoader.getRoot()));
                mesajStage.show();

                mesajPageAçıkMı = true;
            }
        });
        
        satinAlBtn.setOnAction(e ->satinAlBtnAction(e));
    }    
    
    private void satinAlBtnAction(ActionEvent e){
        if ( uçuşlarTV.getSelectionModel().getSelectedItem() != null)
        {
            Uçuş uçuş=(Uçuş) uçuşlarTV.getSelectionModel().getSelectedItem();
            int uçuştakiİndex = -1;
            int uçaktakiİndex = -1;
            int uçakİndexi = -1;

            for (int i=0 ; i<Main.uçaklar.size() ; i++)
            {
                if (Main.uçaklar.get(i).getId().equals(uçuş.getUçak().getId()))
                {
                    uçakİndexi=i;
                    break;
                }
            }

            for (int i=0 ; i<Main.uçaklar.get(uçakİndexi).getUçuşlar().size() ; i++)
            {
                if (Main.uçaklar.get(uçakİndexi).getUçuşlar().get(i).getId().equals(uçuş.getId()))
                {
                    uçaktakiİndex=i;
                    break;
                }
            }

            for (int i=0 ; i<Main.uçuşlar.size() ; i++)
            {
                if (Main.uçuşlar.get(i).getId().equals(uçuş.getId()))
                {
                    uçuştakiİndex=i;
                    break;
                }
            }
            int maxDeğer = Integer.parseInt(uçuş.getUçak().getKoltuklar())-Integer.parseInt(uçuş.getSatilanBiletSayisi());
            if (uçuş.getUçuşDurumu() == Uçuş.Durum.BİTTİ)
            {
                uyariLbl.setText("Bu uçuş zaten bitti!!!");
            }
            else if (maxDeğer == 0)
            {
                
                uyariLbl.setText("Maalesef tüm koltuklar dolu!!!");
            }
            else if (cakısmaKontrol((Uçuş) uçuşlarTV.getSelectionModel().getSelectedItem()))
            {
                uyariLbl.setText("Bu uçuş diğer uçuşlarınızla çakışıyor!!!");
            }
            else if (Integer.parseInt(uçuş.getBilet().getUcret()) >
                    Integer.parseInt(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi()))
            {
                
                uyariLbl.setText("Krediniz yeterli değil!!!");
            }
            else
            {

                int eskiKredi = Integer.parseInt(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
                int uçuşÜcreti = Integer.parseInt(uçuş.getBilet().getUcret());
                int yeniKredi = eskiKredi - uçuşÜcreti;
                
                String tempKredi="";
                tempKredi += yeniKredi;
                Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).setKredi(tempKredi);


                Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().add(uçuş.getBilet());
                
                YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>( Main.yolcular , "Yolcu.txt");
                yolcuYazmaOkumaFile.writeList();

                for (int i=0 ; i<Main.uçaklar.get(uçakİndexi).getUçuşlar().get(uçaktakiİndex).getYolcu().size() ; i++)
                {
                    System.out.println("Uçuşa eklemeden önce: " +
                            Main.uçaklar.get(uçakİndexi).getUçuşlar().get(uçaktakiİndex).getYolcu().get(i).getIsim());
                }


                Main.uçuşlar.get(uçuştakiİndex).getYolcu().add(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex));

                String tempSatılan="";
                int tempInt = Integer.parseInt(Main.uçuşlar.get(uçuştakiİndex).getSatilanBiletSayisi())+1;
                tempSatılan += tempInt;
                Main.uçuşlar.get(uçuştakiİndex).setSatilanBiletSayisi(tempSatılan);
                
                YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar, "Uçuşlar.txt");
                uçuşYazmaOkumaFile.writeList();


                for (int i=0 ; i<Main.uçaklar.get(uçakİndexi).getUçuşlar().get(uçaktakiİndex).getYolcu().size() ; i++)
                {
                    System.out.println("önce: " +
                            Main.uçaklar.get(uçakİndexi).getUçuşlar().get(uçaktakiİndex).getYolcu().get(i).getIsim());
                }


                if (Main.uçuşlar.get(uçuştakiİndex) != Main.uçaklar.get(uçakİndexi).getUçuşlar().get(uçaktakiİndex))
                {
                    Main.uçaklar.get(uçakİndexi).getUçuşlar().get(uçaktakiİndex).getYolcu().add(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex));

                    Main.uçaklar.get(uçakİndexi).getUçuşlar().get(uçaktakiİndex).setSatilanBiletSayisi(tempSatılan);
                }

                
                YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar, "Uçaklar.txt");
                uçakYazmaOkumaFile.writeList();

                krediLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());

                uyariLbl.setText("Tamamlandı!!!");

                
                if (uçuşlarTabAçıkMı)
                {
                    YolcuUçuşSayfasıFXMLController.sahteUçuşTV.getItems().clear();

                    YolcuUçuşSayfasıFXMLController.sahteUçuşTV.getItems().clear();

                    for (int i=0 ; i< Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().size() ; i++)
                    {
                        for (int j=0 ; j<Main.uçuşlar.size() ; j++)
                        {
                            if (Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().get(i).getId().equals(Main.uçuşlar.get(j).getId()))
                            {
                                YolcuUçuşSayfasıFXMLController.sahteUçuşTV.getItems().add(Main.uçuşlar.get(j));
                                YolcuUçuşSayfasıFXMLController.sahteUçuşTV.getItems().add(Main.uçuşlar.get(j).getBilet());
                            }
                        }
                    }

                    YolcuUçuşSayfasıFXMLController.sahteUçuşTV.refresh();
                    YolcuUçuşSayfasıFXMLController.sahteUçuşTV.refresh();



                    Kayıt kayıt = new Kayıt();
                    Main.kayıtlar.add(kayıt);
                    kayıt.yolcuBiletAl(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex),Main.uçuşlar.get(uçuştakiİndex).getBilet());
                    
                    YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayitlar.txt");
                    kayıtYazmaOkumaFile.writeList();
                }
            }
        }
        else
        {
            uyariLbl.setText("Uçuş seçin!!!");
        }
    }
    
     private void krediArttırBtnAction(ActionEvent e) throws IOException {
        if (krediArttırTxtField.getText().isEmpty())
        {
            uyariLbl.setText("Kredinize ne kadar nakit eklemek istediğinizi yazın!!!");
        }
        else
        {
            if (!(krediArttırTxtField.getText().matches("[0-9]+")))
            {
                uyariLbl.setText("Geçersi format: Sadece rakam kullanın!!!");
            }
            else
            {
                int nakit = Integer.parseInt(krediArttırTxtField.getText());
                nakit += Integer.parseInt((Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi()));
                String ekle = "";
                ekle += nakit;

                
//                String eskiSatır = (Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKullaniciAdi()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSifre()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail()+" "+
//                        Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
//
//                String yeniSatır = (Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getId()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getIsim()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSoyİsim()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKullaniciAdi()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSifre()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getTelefonNo()+" "
//                        +Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getMail()+" "
//                        +ekle);
//
//                
//                int düzenlenenSatir = 0;
//                FileReader fileReader = new FileReader("Yolcu.txt");
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//                String satır;
//                while ((satır = bufferedReader.readLine()) != null)
//                {
//                    düzenlenenSatir++;
//                    if (satır == eskiSatır)
//                    {
//                        break;
//                    }
//                }
//
//                String file = "Yolcu.txt";
//                DosyadaSatırDeğiştirme dosyaDegistir = new DosyadaSatırDeğiştirme();
//                dosyaDegistir.dosyadaSatırDeğiştir(file,yeniSatır,düzenlenenSatir);

                
                Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).setKredi(ekle);
                krediLbl.setText(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getKredi());
                uyariLbl.setText("");
                krediArttırTxtField.setText("");

                
                YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>(Main.yolcular , "Yolcu.txt");
                yolcuYazmaOkumaFile.writeList();


                Kayıt kayıt = new Kayıt();
                Main.kayıtlar.add(kayıt);
                kayıt.yolcuKrediArttır(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex));
                
                YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(Main.kayıtlar , "Kayıtlar.txt");
                kayıtYazmaOkumaFile.writeList();
            }
        }
    }
     
    
    private boolean cakısmaKontrol(Uçuş secilenUçuş){
         for (int i=0 ; i<Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().size() ; i++)
        {
            
            int uçuşİndex = -1;
            for (int j=0 ; j<Main.uçuşlar.size() ; j++)
            {
                if (Main.uçuşlar.get(j).getId().equals(Main.yolcular.get(GirişSayfasıFXMLController.kullaniciİndex).getSatilanBiletler().get(i).getId()))
                {
                    uçuşİndex = j;
                }
            }
            System.out.println("uçuşindex: "+uçuşİndex);


            if (Main.uçuşlar.get(uçuşİndex).getId().equals(secilenUçuş.getId()))
            {
                return false;
            }
            else
            {
                if (!Main.uçuşlar.get(uçuşİndex).getTarih().equals(secilenUçuş.getTarih()))
                {
                    return false;
                }
                else
                {
                    if (!Main.uçuşlar.get(uçuşİndex).getKalkisZamani().equals(secilenUçuş.getKalkisZamani()))
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
