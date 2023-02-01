
package com.havaalani.Kullanicilar;

import java.io.Serializable;


public class Yönetici extends Kişi implements Serializable{
    private String maas;
    private String adres;
    private boolean admin;

    public Yönetici(String maas, String adres, boolean admin, String id, String isim, String soyİsim, String kullaniciAdi, String sifre, String telefonNo, String mail) {
        super(id, isim, soyİsim, kullaniciAdi, sifre, telefonNo, mail);
        this.maas = maas;
        this.adres = adres;
        this.admin = admin;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
    @Override
    public String goster() {
        String string="";
        string+=getIsim()+"-"+getSoyİsim()+"-"+getId()+"-"+getKullaniciAdi()+"-"+getSifre()+"-"+getTelefonNo()+"-"+
                getMail()+"-"+getAdres()+"-"+getMaas();
        return string;
    }
}
