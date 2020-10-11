package com.TiNg.windows;

import com.TiNg.pane.COMConnect;
import com.TiNg.pane.RegisterPane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class FirstWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    int windowWidth = 1200;
    int windowHeight = 700;

    public FirstWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(windowWidth);
        setHeight(windowHeight);
        anchorPane.setStyle("-fx-background-color:#848484");

//        URL cssURL = this.getClass().getClassLoader().getResource("styles/ButtonStyles.css");
//        scene.getStylesheets().add(cssURL.toExternalForm());
        setScene(scene);

        COMConnect comConnect = new COMConnect(windowWidth);  //COM连接pane
        anchorPane.getChildren().add(comConnect);
        AnchorPane.setBottomAnchor(comConnect, 0.0);

        RegisterPane registerPane = new RegisterPane();  //寄存器读取pane
        anchorPane.getChildren().add(registerPane);
        AnchorPane.setTopAnchor(registerPane, 50.0);
        AnchorPane.setLeftAnchor(registerPane, 10.0);

        show();
    }
}