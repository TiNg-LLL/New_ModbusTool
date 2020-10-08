package com.TiNg.windows;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AttachWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    public AttachWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(800);
        setHeight(500);
        anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setScene(scene);
    }
}
