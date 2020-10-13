package com.TiNg.windows;

import com.TiNg.datatreat.CoilDataFlashThread;
import com.TiNg.datatreat.RegisterDataFlashThread;
import com.TiNg.pane.COMConnect;
import com.TiNg.pane.CoilsPane;
import com.TiNg.pane.RegisterPane;
import com.TiNg.pane.RegisterSinglePane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FirstWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    int windowWidth = 1000;
    int windowHeight = 700;

    public static RegisterPane registerPane;
    public static CoilsPane coilsPane;

    public FirstWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(windowWidth);
        setHeight(windowHeight);
        setResizable(false);
        anchorPane.setStyle("-fx-background-color:#ffffff");

        setScene(scene);

        COMConnect comConnect = new COMConnect(windowWidth);  //COM连接pane
        anchorPane.getChildren().add(comConnect);
        AnchorPane.setBottomAnchor(comConnect, 0.0);

        registerPane = new RegisterPane();  //寄存器功能pane
        anchorPane.getChildren().add(registerPane);
        AnchorPane.setTopAnchor(registerPane, 50.0);
        AnchorPane.setLeftAnchor(registerPane, 25.0);

        coilsPane = new CoilsPane();  //线圈功能pane
        anchorPane.getChildren().add(coilsPane);
        AnchorPane.setTopAnchor(coilsPane, 50.0);
        AnchorPane.setLeftAnchor(coilsPane, 500.0);

        for (int i = 0; i < registerPane.getRegisterPaneQuantity(); i++) {
            RegisterDataFlashThread registerDataFlashThread = new RegisterDataFlashThread(i);
            registerDataFlashThread.start();
        }

        for (int i = 0; i < coilsPane.getCoilsPaneQuantity(); i++) {
            CoilDataFlashThread coilDataFlashThread = new CoilDataFlashThread(i);
            coilDataFlashThread.start();
        }

        show();
    }
}