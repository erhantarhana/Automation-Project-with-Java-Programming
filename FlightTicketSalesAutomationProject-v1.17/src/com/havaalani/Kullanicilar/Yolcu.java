
package com.havaalani.Kullanicilar;

import java.io.Serializable;
import java.util.ArrayList;


public class Yolcu extends Kişi implements Serializable{

    private String kredi;
    private ArrayList<Bilet> satilanBiletler = new ArrayList<>();

    public Yolcu(String kredi, String id, String isim, String soyİsim, String kullaniciAdi, String sifre, String telefonNo, String mail) {
        super(id, isim, soyİsim, kullaniciAdi, sifre, telefonNo, mail);
        this.kredi = kredi;
        System.out.println(this.satilanBiletler.size());
    }
    

    public String getKredi() {
        return kredi;
    }

    public void setKredi(String kredi) {
        this.kredi = kredi;
    }

    public ArrayList<Bilet> getSatilanBiletler() {
        return satilanBiletler;
    }

    public void setSatilanBiletler(ArrayList<Bilet> satilanBiletler) {
        this.satilanBiletler = satilanBiletler;
    }
    
    @Override
    public String goster() {
        String string="";
        string+=getIsim()+"-"+getSoyİsim()+"-"+getId()+"-"+getKullaniciAdi()+"-"+getSifre()+"-"+getTelefonNo()+"-"+ getMail()+"-"+getKredi();
       return string;
    }
}
