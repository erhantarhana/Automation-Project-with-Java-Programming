
package com.havaalani.Controller;

import com.havaalani.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


public class MesajŞekilFXMLController implements Initializable {

    @FXML private AnchorPane mesajŞekilRoot;
    @FXML private Label zamanLbl;
    @FXML private Label gönderenLbl;
    @FXML private TextArea mesajTxtArea;

    public int index;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void doldur(){
        gönderenLbl.setText(Main.mesajlar.get(index).getGonderen());
        zamanLbl.setText(Main.mesajlar.get(index).getZaman());
        mesajTxtArea.setText(Main.mesajlar.get(index).getYazi());
    }
    
}
