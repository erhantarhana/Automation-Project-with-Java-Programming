
package com.havaalani.Kullanicilar;

import java.io.Serializable;


public abstract class Kişi implements Serializable, BilgiGoster{
    private String id;
    private String isim;
    private String soyİsim;
    private String kullaniciAdi;
    private String sifre;
    private String telefonNo;
    private String mail;

    public Kişi(String id, String isim, String soyİsim, String kullaniciAdi, String sifre, String telefonNo, String mail) {
        this.id = id;
        this.isim = isim;
        this.soyİsim = soyİsim;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.telefonNo = telefonNo;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyİsim() {
        return soyİsim;
    }

    public void setSoyİsim(String soyİsim) {
        this.soyİsim = soyİsim;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTelefonNo() {
        return telefonNo;
    }

    public void setTelefonNo(String telefonNo) {
        this.telefonNo = telefonNo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
       
}
