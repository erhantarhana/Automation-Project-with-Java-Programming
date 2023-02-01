
package com.havaalani.Kullanicilar;

import java.io.Serializable;


public class Bilet implements Serializable, BilgiGoster{
    private String id;
    private String ucret;
    private String ceza;

    public Bilet(String id, String ucret, String ceza) {
        this.id = id;
        this.ucret = ucret;
        this.ceza = ceza;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUcret() {
        return ucret;
    }

    public void setUcret(String ucret) {
        this.ucret = ucret;
    }

    public String getCeza() {
        return ceza;
    }

    public void setCeza(String ceza) {
        this.ceza = ceza;
    }

    @Override
    public String goster() {
        return id+"- ücret:"+ucret+"-"+ceza;
    }

}
