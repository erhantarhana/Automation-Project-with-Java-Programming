
package com.havaalani.Kullanicilar;

import java.io.Serializable;
import java.util.ArrayList;


public class Uçak implements Serializable, BilgiGoster{
    private String id;
    private String koltuklar;
    private ArrayList<Uçuş> uçuşlar;

    public Uçak(String id, String koltuklar) {
        this.id = id;
        this.koltuklar = koltuklar;
        this.uçuşlar = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKoltuklar() {
        return koltuklar;
    }

    public void setKoltuklar(String koltuklar) {
        this.koltuklar = koltuklar;
    }

    public ArrayList<Uçuş> getUçuşlar() {
        return uçuşlar;
    }

    public void setUçuşlar(ArrayList<Uçuş> uçuşlar) {
        this.uçuşlar = uçuşlar;
    }

    @Override
    public String goster() {
         String uçuşid="";
        for (int i=0 ; i<uçuşlar.size() ; i++)
        {
            uçuşid+=uçuşlar.get(i);
            if (i!=uçuşlar.size()-1)
            {
                uçuşid+="-";
            }
        }

        String string="";
        string+=id+"- koltuklar:"+koltuklar+"- uçuş:"+uçuşid;

        return string;
    }
   
}
