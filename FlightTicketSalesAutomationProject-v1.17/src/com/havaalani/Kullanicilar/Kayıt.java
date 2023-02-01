
package com.havaalani.Kullanicilar;

import com.havaalani.Controller.AdminKayıtlarSayfasıFXMLController;
import com.havaalani.Main;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Kayıt implements Serializable, BilgiGoster{
    private String yazi;
    private String zaman;
    
    public Kayıt(){
        this.yazi = new String();
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime=LocalDateTime.now();
        this.zaman = dateTimeFormatter.format(localDateTime);
    }

    public String getYazi() {
        return yazi;
    }

    public void setYazi(String yazi) {
        this.yazi = yazi;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }
    
    public void yolcuBiletAl(Yolcu yolcu, Bilet bilet){
        this.yazi="Yolcu:"+yolcu.getId()+"bu bileti aldı:"+bilet.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yolcuBiletİptal(Yolcu yolcu, Bilet bilet){
        this.yazi="Yolcu:"+yolcu.getId()+"bu bilet iptal edildi:"+bilet.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yolcuProfilDüzenle(Yolcu yolcu){
        this.yazi="Yolcu:"+yolcu.getId()+" profili düzenlendi";
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yolcuKrediArttır(Yolcu yolcu){
        this.yazi="Yolcu:"+yolcu.getId()+" kredisi arttırıldı "+yolcu.getKredi();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void çalışanProfilDüzenle(Çalışan çalışan){
        this.yazi="Çalışan:"+çalışan.getId()+" profili düzenlendi";
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void çalışanUçuştakiYolcuKaldır(Çalışan çalışan, Yolcu yolcu, Uçuş uçuş){
        this.yazi="Çalışan:"+çalışan.getId()+" bu yolcu kaldırıldı:"+yolcu.getId() +" bu uçuştan:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void çalışanUçuşaYolcuEkle(Çalışan çalışan, Yolcu yolcu, Uçuş uçuş){
        this.yazi="Çalışan:"+çalışan.getId()+" bu yolcu eklendi:"+yolcu.getId() +" bu uçuşa:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void çalışanUçuşKaldır(Çalışan çalışan, Uçuş uçuş){
        this.yazi="Çalışan:"+çalışan.getId()+" uçuş kaldırıldı:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void çalışanUçuşEkle(Çalışan çalışan, Uçuş uçuş){
        this.yazi="Çalışan:"+çalışan.getId()+" uçuş eklendi:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiProfilDüzenle(Yönetici yönetici){
        this.yazi="Yönetici:"+yönetici.getId()+" profili düzenlendi";
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiUçuştakiYolcuKaldır(Yönetici yönetici, Yolcu yolcu, Uçuş uçuş){
        this.yazi="Yönetici:"+yönetici.getId()+" bu yolcu kaldırıldı:"+yolcu.getId() +" bu uçuştan:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiUçuşaYolcuEkle(Yönetici yönetici, Yolcu yolcu, Uçuş uçuş){
        this.yazi="Yönetici:"+yönetici.getId()+" bu yolcu eklendi:"+yolcu.getId() +" bu uçuşa:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiUçuşKaldır(Yönetici yönetici, Uçuş uçuş){
        this.yazi="Yönetici:"+yönetici.getId()+" uçuş kaldırıldı:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiUçuşEkle(Yönetici yönetici, Uçuş uçuş){
        this.yazi="Yönetici:"+yönetici.getId()+" uçuş eklendi:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiYolcuEkle(Yönetici yönetici, Yolcu yolcu){
        this.yazi="Yönetici:"+yönetici.getId()+" bu yolcu eklendi:"+yolcu.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiYolcuKaldır(Yönetici yönetici, Yolcu yolcu){
        this.yazi="Yönetici:"+yönetici.getId()+" bu yolcu kaldırıldı:"+yolcu.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiUçakEkle(Yönetici yönetici, Uçak uçak){
        this.yazi="Yönetici:"+yönetici.getId()+" bu uçak eklendi:"+uçak.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void yöneticiUçakKaldır(Yönetici yönetici, Uçak uçak){
        this.yazi="Yönetici:"+yönetici.getId()+" bu uçak kaldırıldı:"+uçak.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminProfilDüzenle(){
        this.yazi="Admin:"+Main.Admin.getId()+" profili düzenlendi";
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminUçuştakiYolcuKaldır(Yolcu yolcu, Uçuş uçuş){
        this.yazi="Admin:"+Main.Admin.getId()+" bu yolcu kaldırıldı:"+yolcu.getId() +" bu uçuştan:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminUçuşaYolcuEkle(Yolcu yolcu, Uçuş uçuş){
        this.yazi="Admin:"+Main.Admin.getId()+" bu yolcu eklendi:"+yolcu.getId() +" bu uçuşa:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminUçuşKaldır(Uçuş uçuş){
        this.yazi="Admin:"+Main.Admin.getId()+" uçuş kaldırıldı:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminUçuşEkle(Uçuş uçuş){
        this.yazi="Admin:"+Main.Admin.getId()+" uçuş eklendi:"+uçuş.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminYolcuKaldır(Yolcu yolcu){
        this.yazi="Admin:"+Main.Admin.getId()+" bu yolcu kaldırıldı:"+yolcu.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminYolcuEkle(Yolcu yolcu){
        this.yazi="Admin:"+Main.Admin.getId()+" bu yolcu eklendi:"+yolcu.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminUçakKaldır(Uçak uçak){
        this.yazi="Admin:"+Main.Admin.getId()+" bu uçak kaldırıldı:"+uçak.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    public void adminUçakEkle(Uçak uçak){
        this.yazi="Admin:"+Main.Admin.getId()+" bu uçak eklendi:"+uçak.getId();
        AdminKayıtlarSayfasıFXMLController.yenileKayıtlarTV();
    }
    
    @Override
    public String goster(){
        return zaman+"-"+yazi;
    }
}
