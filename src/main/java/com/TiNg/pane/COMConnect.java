package com.TiNg.pane;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class COMConnect extends HBox {
    Button button = new Button("这是一个按钮");

    public COMConnect() {
        setStyle("-fx-background-color:#5bffe3");
        setPrefWidth(800);
        setPrefHeight(100);
        getChildren().add(button);
    }
}
