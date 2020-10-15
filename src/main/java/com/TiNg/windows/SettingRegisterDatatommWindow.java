package com.TiNg.windows;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingRegisterDatatommWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    public SettingRegisterDatatommWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(600);
        setHeight(400);
        anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setScene(scene);
    }
}
