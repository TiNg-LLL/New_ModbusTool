package com.TiNg.windows;

import com.TiNg.pane.COMConnect;
import com.TiNg.pane.RegisterPane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FirstWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    int windowWidth = 1200;
    int windowHeight = 700;

    public FirstWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(windowWidth);
        setHeight(windowHeight);
        anchorPane.setStyle("-fx-background-color:#a5a3a3");
        setScene(scene);

        COMConnect comConnect = new COMConnect(windowWidth);  //COM连接pane
        anchorPane.getChildren().add(comConnect);
        AnchorPane.setBottomAnchor(comConnect, 0.0);

        RegisterPane registerPane = new RegisterPane();  //寄存器读取pane
        anchorPane.getChildren().add(registerPane);
        AnchorPane.setTopAnchor(registerPane, 60.0);
        AnchorPane.setLeftAnchor(registerPane, 20.0);

        show();
    }
}