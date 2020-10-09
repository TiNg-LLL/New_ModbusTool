package com.TiNg.windows;

import com.TiNg.pane.COMConnect;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    int windowWidth = 1200;
    int windowHeight = 700;

    public FirstWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(windowWidth);
        setHeight(windowHeight);
        setResizable(false);
        anchorPane.setStyle("-fx-background-color:#a5a3a3");
        setScene(scene);

/*        COMConnect comConnect = new COMConnect(windowWidth);
        anchorPane.getChildren().add(comConnect);
        AnchorPane.setBottomAnchor(comConnect,0.0);*/

        try {
            COMConnect comConnect1 = new COMConnect(windowWidth);
            anchorPane.getChildren().add(comConnect1);
            AnchorPane.setBottomAnchor(comConnect1,0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        show();
    }
}
