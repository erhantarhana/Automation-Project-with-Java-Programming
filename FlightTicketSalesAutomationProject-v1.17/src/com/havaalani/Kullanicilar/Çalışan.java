
package com.havaalani.Kullanicilar;

import java.io.Serializable;


public class Çalışan extends Kişi implements Serializable{
    private String maas;
    private String adres;

    public Çalışan(String maas, String adres, String id, String isim, String soyİsim, String kullaniciAdi, String sifre, String telefonNo, String mail) {
        super(id, isim, soyİsim, kullaniciAdi, sifre, telefonNo, mail);
        this.maas = maas;
        this.adres = adres;
    }

    public String getMaas() {
        return maas;
    }

    public void setMaas(String maas) {
        this.maas = maas;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String goster() {
        String string="";
        string+=getIsim()+"-"+getSoyİsim()+"-"+getId()+"-"+getKullaniciAdi()+"-"+getSifre()+"-"+getTelefonNo()+"-"+
                getMail()+"-"+getAdres()+"-"+getMaas();
        return string;
    }
}
