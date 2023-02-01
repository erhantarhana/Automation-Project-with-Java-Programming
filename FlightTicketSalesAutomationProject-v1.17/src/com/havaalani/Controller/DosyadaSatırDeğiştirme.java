
package com.havaalani.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;


public class DosyadaSatırDeğiştirme {
    
    public void dosyadaSatırDeğiştir(String dosyaAdi, String yeniSatır, int satırNo){
        String içerik = new String();
        String düzenlenmişİçerik = new String();
        içerik = dosyaOku(dosyaAdi);
        düzenlenmişİçerik = icerikDuzenle(içerik, yeniSatır, satırNo);
        dosyaYaz(dosyaAdi, düzenlenmişİçerik);
    }
    
    private static String[] dosyayiSatirDizisineDonustur(String içerik, int satırlar) {
        String[] array = new String[satırlar];
        int index = 0;
        int tempInt = 0;
        int baslangicİndex = 0;
        int sonİndex = içerik.length() - 1;

        while (true) {
            if (içerik.charAt(index) == '\n') {
                tempInt++;

                String temp2 = new String();
                for (int i = 0; i < index - baslangicİndex; i++) {
                    temp2 += içerik.charAt(baslangicİndex + i);
                }
                baslangicİndex = index;
                array[tempInt - 1] = temp2;
            }

            if (index == sonİndex) {

                tempInt++;

                String temp2 = new String();
                for (int i = 0; i < index - baslangicİndex + 1; i++) {
                    temp2 += içerik.charAt(baslangicİndex + i);
                }
                array[tempInt - 1] = temp2;
                break;
            }
            index++;
        }
        return array;
    }
    
    private static int dosyadakiSatırSayısı(String içerik) {
        int satırSayısı = 0;
        int index = 0;
        int sonİndex = 0;

        sonİndex = içerik.length() - 1;

        while (true) {

            if (içerik.charAt(index) == '\n') {
                satırSayısı++;

            }

            if (index == sonİndex) {
                satırSayısı = satırSayısı + 1;
                break;
            }
            index++;
        }
        return satırSayısı;
    }
    
    private static String icerikDuzenle(String içerik, String yeniSatir, int satir) {

        int satırNumarası = 0;
        satırNumarası = dosyadakiSatırSayısı(içerik);

        String[] satirlar = new String[satırNumarası];
        satirlar = dosyayiSatirDizisineDonustur(içerik, satırNumarası);

        if (satir != 1) {
            satirlar[satir - 1] = "\n" + yeniSatir;
        } else {
            satirlar[satir - 1] = yeniSatir;
        }
        içerik = new String();

        for (int i = 0; i < satırNumarası; i++) {
            içerik += satirlar[i];
        }
        return içerik;
    }
    
    private static void dosyaYaz(String dosya, String içerik) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dosya), "utf-8"))) {
            writer.write(içerik);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String dosyaOku(String dosyaAdi) {
        String içerik = null;
        File file = new File(dosyaAdi);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            içerik = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return içerik;
    }
}
