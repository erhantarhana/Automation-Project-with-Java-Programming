
package com.havaalani;

import com.havaalani.Controller.YazmaOkumaFile;
import com.havaalani.Kullanicilar.Bilet;
import com.havaalani.Kullanicilar.Kayıt;
import com.havaalani.Kullanicilar.Mesaj;
import com.havaalani.Kullanicilar.Uçak;
import com.havaalani.Kullanicilar.Uçuş;
import com.havaalani.Kullanicilar.Yolcu;
import com.havaalani.Kullanicilar.Yönetici;
import com.havaalani.Kullanicilar.Çalışan;
import com.havaalani.Kullanicilar.*;
import com.havaalani.Controller .*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    
    public static Yönetici Admin;
    public static ArrayList<Yönetici> yöneticiler = new ArrayList<>();
    public static ArrayList<Çalışan> çalışanlar = new ArrayList<>();
    public static ArrayList<Yolcu> yolcular = new ArrayList<>();
    public static ArrayList<Uçak> uçaklar = new ArrayList<>();
    public static ArrayList<Uçuş> uçuşlar = new ArrayList<>();
    public static ArrayList<Bilet> biletler = new ArrayList<>();
    public static ArrayList<Mesaj> mesajlar = new ArrayList<>();
    public static ArrayList<Kayıt> kayıtlar = new ArrayList<>();
    public static Stage loginPageStage;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        
        System.out.println(LocalDateTime.now());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        System.out.println(dtf.format(now));
        
       // Admin = new Yönetici("8000", "İstanbul", true, "100", "Erhan", "Tarhana", "erhantar", "tarhana37", "05312070632", "erhan@gmail.com");
        YazmaOkumaFile<Yönetici> yöneticiYazmaOkumaFile = new YazmaOkumaFile<>(Admin,"Admin.txt");
       // yöneticiYazmaOkumaFile.yaz();

        Admin=yöneticiYazmaOkumaFile.oku();
        
        YazmaOkumaFile<Yönetici> yöneticiYazmaOkumaFile1 = new YazmaOkumaFile<>(yöneticiler , "Yöneticiler.txt");
        yöneticiler = yöneticiYazmaOkumaFile1.readList();
        
        YazmaOkumaFile<Çalışan> çalışanYazmaOkumaFile = new YazmaOkumaFile<>(çalışanlar , "Çalışanlar.txt");
        çalışanlar = çalışanYazmaOkumaFile.readList();
        
        YazmaOkumaFile<Yolcu> yolcuYazmaOkumaFile = new YazmaOkumaFile<>(yolcular , "Yolcu.txt");
        yolcular = yolcuYazmaOkumaFile.readList();
        
        YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(uçaklar , "Uçaklar.txt");
        uçaklar = uçakYazmaOkumaFile.readList();
        
        YazmaOkumaFile<Bilet> biletYazmaOkumaFile = new YazmaOkumaFile<>(biletler , "Biletler.txt");
        biletler = biletYazmaOkumaFile.readList();
        
        YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(uçuşlar , "Uçuşlar.txt");
        uçuşlar = uçuşYazmaOkumaFile.readList();
        
        YazmaOkumaFile<Mesaj> mesajYazmaOkumaFile = new YazmaOkumaFile<>(mesajlar , "Mesajlar.txt");
        mesajlar = mesajYazmaOkumaFile.readList();
        
        YazmaOkumaFile<Kayıt> kayıtYazmaOkumaFile = new YazmaOkumaFile<>(kayıtlar , "Kayıtlar.txt");
        kayıtlar = kayıtYazmaOkumaFile.readList();
        
        uçuşDurumuAta();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader girişSayfasıLoader = new FXMLLoader(Main.class.getResource("Fxml/GirişSayfasıFXML.fxml"));

        try {
            girişSayfasıLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        loginPageStage = new Stage();
        loginPageStage.setTitle("Giriş Yap");
        loginPageStage.setScene(new Scene(girişSayfasıLoader.getRoot()));
        loginPageStage.show();
    }
    
     private static void uçuşDurumuAta()
    {
        for (int i=0 ; i < uçuşlar.size() ; i++)
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            
            String şuankiString = dtf.format(now);
            String[] tarihŞuankiSaat = şuankiString.split(" ");

            String[] şuankiTarih = tarihŞuankiSaat[0].split("/");
            String[] şuankiSaatArray = tarihŞuankiSaat[1].split(":");
            
            int şuankiYıl = Integer.parseInt(şuankiTarih[2])%1000;
            int şuankiAy = Integer.parseInt(şuankiTarih[1]);
            int şuankiGün = Integer.parseInt(şuankiTarih[0]);

            int şuankiSaat = Integer.parseInt(şuankiSaatArray[0]);
            int şuankiDakika = Integer.parseInt(şuankiSaatArray[1]);


            String[] tarih = uçuşlar.get(i).getTarih().split("/");
            String[] saatArray = uçuşlar.get(i).getKalkisZamani().split(":");
            
            int yıl = Integer.parseInt(tarih[2]);
            int ay = Integer.parseInt(tarih[1]);
            int gün = Integer.parseInt(tarih[0]);

            int saat = Integer.parseInt(saatArray[0]);
            int dakika = Integer.parseInt(saatArray[1]);

            
            if (şuankiYıl>yıl)
            {
                uçuşlar.get(i).setUçuşDurumu(Uçuş.Durum.BİTTİ);
            }else if (yıl==şuankiYıl)
            {
                if (şuankiAy>ay)
                {
                    uçuşlar.get(i).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                }else if (şuankiAy==ay)
                {
                    if (gün<şuankiGün)
                    {
                        uçuşlar.get(i).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                    }else if(saat<şuankiSaat)
                    {
                        uçuşlar.get(i).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                    }else if (saat==şuankiSaat)
                    {
                        if (dakika<=şuankiDakika)
                        {
                            uçuşlar.get(i).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                        }
                    }
                }
            }
        }
        
        YazmaOkumaFile<Uçuş> uçuşYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçuşlar,"Uçuşlar.txt");
        uçuşYazmaOkumaFile.writeList();

        for (int i=0 ; i<uçaklar.size() ; i++)
        {
            for (int j=0 ; j<uçaklar.get(i).getUçuşlar().size() ; j++)
            {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                
                String şuankiString = dtf.format(now);
                String[] tarihŞuankiSaat = şuankiString.split(" ");

                String[] şuankiTarih = tarihŞuankiSaat[0].split("/");
                String[] şuankiSaatArray = tarihŞuankiSaat[1].split(":");
                
                int şuankiYıl = Integer.parseInt(şuankiTarih[2])%1000;
                int şuankiAy = Integer.parseInt(şuankiTarih[1]);
                int şuankiGün = Integer.parseInt(şuankiTarih[0]);

                int şuankiSaat = Integer.parseInt(şuankiSaatArray[0]);
                int şuankiDakika = Integer.parseInt(şuankiSaatArray[1]);

                

                String[] tarih = uçaklar.get(i).getUçuşlar().get(j).getTarih().split("/");
                String[] saatArray = uçaklar.get(i).getUçuşlar().get(j).getKalkisZamani().split(":");
                
                int yıl = Integer.parseInt(tarih[2]);
                int ay = Integer.parseInt(tarih[1]);
                int gün = Integer.parseInt(tarih[0]);

                int saat = Integer.parseInt(saatArray[0]);
                int dakika = Integer.parseInt(saatArray[1]);

                
                if (şuankiYıl>yıl)
                {
                    uçaklar.get(i).getUçuşlar().get(j).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                }else if (yıl==şuankiYıl)
                {
                    if (şuankiAy>ay)
                    {
                        uçaklar.get(i).getUçuşlar().get(j).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                    }else if (şuankiAy==ay)
                    {
                        if (gün<şuankiGün)
                        {
                            uçaklar.get(i).getUçuşlar().get(j).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                        }else if (gün==şuankiGün)
                        {
                            if(saat<şuankiSaat)
                            {
                                uçaklar.get(i).getUçuşlar().get(j).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                            }else if (saat==şuankiSaat)
                            {
                                if (dakika<=şuankiDakika)
                                {
                                    uçaklar.get(i).getUçuşlar().get(j).setUçuşDurumu(Uçuş.Durum.BİTTİ);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        YazmaOkumaFile<Uçak> uçakYazmaOkumaFile = new YazmaOkumaFile<>(Main.uçaklar,"Uçaklar.txt");
        uçakYazmaOkumaFile.writeList();
    }
}
