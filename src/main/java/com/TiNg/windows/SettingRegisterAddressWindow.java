package com.TiNg.windows;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingRegisterAddressWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    public SettingRegisterAddressWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(600);
        setHeight(400);
        anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setScene(scene);
    }
}
