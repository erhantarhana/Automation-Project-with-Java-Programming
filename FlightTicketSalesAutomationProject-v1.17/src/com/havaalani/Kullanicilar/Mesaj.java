
package com.havaalani.Kullanicilar;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Mesaj implements Serializable, BilgiGoster{
    private String zaman;
    private String yazi;
    private String gonderen;

    public Mesaj(String yazi, String gonderen) {
        this.yazi = yazi;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        this.zaman = dateTimeFormatter.format(localDateTime);
        this.gonderen = gonderen;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }

    public String getYazi() {
        return yazi;
    }

    public void setYazi(String yazi) {
        this.yazi = yazi;
    }

    public String getGonderen() {
        return gonderen;
    }

    public void setGonderen(String gonderen) {
        this.gonderen = gonderen;
    }

    @Override
    public String goster() {
        return gonderen+"-"+zaman+"-"+yazi;
    }
    
    
}
