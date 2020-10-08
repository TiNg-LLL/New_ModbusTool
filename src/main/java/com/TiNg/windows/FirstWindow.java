package com.TiNg.windows;

import com.TiNg.pane.COMConnect;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FirstWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    int windowWidth = 800;
    int windowHeight = 500;

    public FirstWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(windowWidth);
        setHeight(windowHeight);
        anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setScene(scene);

        show();

        anchorPane.getChildren().add(new COMConnect());
    }
}
