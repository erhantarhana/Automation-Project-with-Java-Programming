
package com.havaalani.Kullanicilar;

import java.io.Serializable;
import java.util.ArrayList;


public class Uçuş implements Serializable, BilgiGoster{
    private String id;
    private Uçak uçak;
    private Bilet bilet;
    private String bşlNoktasi;
    private String hedef;
    private String tarih;
    private String sure;
    private String satilanBiletSayisi;
    private ArrayList<Yolcu> yolcu;
    private String kalkisZamani;
    private String ucusUcreti;
    private String uçuşCeza;

    public Uçuş(String id, Uçak uçak, Bilet bilet, String bşlNoktasi, String hedef, String tarih, String sure,  String kalkisZamani) {
        this.id = id;
        this.uçak = uçak;
        this.bilet = bilet;
        this.bşlNoktasi = bşlNoktasi;
        this.hedef = hedef;
        this.tarih = tarih;
        this.sure = sure;
        this.satilanBiletSayisi = "0";
        this.yolcu = new ArrayList<>();
        this.kalkisZamani = kalkisZamani;
        this.ucusUcreti = this.bilet.getUcret();
        this.uçuşCeza = this.bilet.getCeza();
        this.uçuşDurumu = Durum.BİTMEDİ;
    }
    

    public String getUcusUcreti() {
        return ucusUcreti;
    }

    public void setUcusUcreti(String ucusUcreti) {
        this.ucusUcreti = ucusUcreti;
    }

    public String getUçuşCeza() {
        return uçuşCeza;
    }

    public void setUçuşCeza(String uçuşCeza) {
        this.uçuşCeza = uçuşCeza;
    }

    @Override
    public String goster() {
        
        String yolcuid="";
        for (int i=0 ; i<yolcu.size() ; i++)
        {
            yolcuid+=yolcu.get(i);
            if (i!=yolcu.size()-1)
            {
                yolcuid+="-";
            }
        }

        String string=id+"- uçak:"+uçak.getId()+"- bilet:"+bilet.getId()+
                "- başlangıç:"+bşlNoktasi+"- hedef:"+hedef+"-"+tarih+"-"+kalkisZamani+
                "- süre:"+sure+"- yolcu:"+yolcuid;

        return string;
    }

    public enum Durum{ BİTTİ, BİTİYOR , BİTMEDİ }
    private Durum uçuşDurumu;

    public Durum getUçuşDurumu() {
        return uçuşDurumu;
    }

    public void setUçuşDurumu(Durum uçuşDurumu) {
        this.uçuşDurumu = uçuşDurumu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uçak getUçak() {
        return uçak;
    }

    public void setUçak(Uçak uçak) {
        this.uçak = uçak;
    }

    public Bilet getBilet() {
        return bilet;
    }

    public void setBilet(Bilet bilet) {
        this.bilet = bilet;
    }

    public String getBşlNoktasi() {
        return bşlNoktasi;
    }

    public void setBşlNoktasi(String bşlNoktasi) {
        this.bşlNoktasi = bşlNoktasi;
    }

    public String getHedef() {
        return hedef;
    }

    public void setHedef(String hedef) {
        this.hedef = hedef;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }

    public String getSatilanBiletSayisi() {
        return satilanBiletSayisi;
    }

    public void setSatilanBiletSayisi(String satilanBiletSayisi) {
        this.satilanBiletSayisi = satilanBiletSayisi;
    }

    public ArrayList<Yolcu> getYolcu() {
        return yolcu;
    }

    public void setYolcu(ArrayList<Yolcu> yolcu) {
        this.yolcu = yolcu;
    }

    public String getKalkisZamani() {
        return kalkisZamani;
    }

    public void setKalkisZamani(String kalkisZamani) {
        this.kalkisZamani = kalkisZamani;
    }
    
    
    
}
