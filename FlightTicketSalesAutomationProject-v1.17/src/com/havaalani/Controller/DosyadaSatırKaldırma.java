
package com.havaalani.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class DosyadaSatırKaldırma {
    public void satırKaldır(String dosyaAdi , String eskiSatır) throws IOException {

        eskiSatır = eskiSatır.trim();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(dosyaAdi));

        
        ArrayList<String> tempData = new ArrayList<>();

        String satır;
        for (int i=0; (satır =bufferedReader.readLine()) !=null ; i++)
        {
            satır=satır.trim();
            if (!eskiSatır.equals(satır))
            {
                tempData.add(satır);
            }

        }

        BufferedWriter badBufferedWriter = new BufferedWriter(new FileWriter(dosyaAdi));
        if (tempData.size()>0)
        {
            badBufferedWriter.write(tempData.get(0));
            badBufferedWriter.newLine();
            badBufferedWriter.close();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dosyaAdi, true));

        for (int i=1 ; i<tempData.size() ; i++)
        {
            bufferedWriter.write(tempData.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
