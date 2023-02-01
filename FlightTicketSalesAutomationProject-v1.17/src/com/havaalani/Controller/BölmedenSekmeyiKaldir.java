
package com.havaalani.Controller;

import javafx.scene.control.TabPane;


public class BölmedenSekmeyiKaldir {
    public void sekmeKaldır(TabPane tabPane, int sekmeİndex){
        tabPane.getTabs().remove(sekmeİndex);
    }
}
