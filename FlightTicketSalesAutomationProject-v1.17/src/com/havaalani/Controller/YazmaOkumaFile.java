
package com.havaalani.Controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class YazmaOkumaFile <K>{
    private ArrayList<K> objects;
    private K singleObject;
    private String dosyaAdi;

    public YazmaOkumaFile(K singleObject, String dosyaAdi) {
        this.singleObject = singleObject;
        this.dosyaAdi = dosyaAdi;
    }
    
    public YazmaOkumaFile(ArrayList<K> objects, String dosyaAdi) {
        this.objects = objects;
        this.dosyaAdi = dosyaAdi;
    }
    
    public void writeList(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dosyaAdi);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeInt(objects.size());
            
            for (K object : objects){
                objectOutputStream.writeObject(object);
            }
            
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    
    public ArrayList<K> readList(){
        
        try {
            FileInputStream fileInputStream = new FileInputStream(dosyaAdi);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            
            int size=objectInputStream.readInt();
            
            for (int i=0 ; i<size ; i++){
                K object = (K) objectInputStream.readObject();
                objects.add(object);
            }
            
            fileInputStream.close();
            objectInputStream.close();

            return objects;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());  
        }

        return objects;
    }
    
    public void yaz() throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(dosyaAdi);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(singleObject);

        fileOutputStream.close();
        objectOutputStream.close();
    }
    
    public K oku() throws IOException, ClassNotFoundException{
         try
        {
            FileInputStream fileInputStream=new FileInputStream(dosyaAdi);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            K object=(K) objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();

            return object;
        }catch (EOFException e)
        {
            System.out.println(e.getMessage());
        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
