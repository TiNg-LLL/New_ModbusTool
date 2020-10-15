package com.TiNg.windows;

import com.TiNg.datatreat.ReadThread;
import com.TiNg.pane.COMConnect;
import com.TiNg.pane.SettingPane;
import com.TiNg.pane.coils.CoilsPane;
import com.TiNg.pane.registers.RegisterLabelPane;
import com.TiNg.pane.registers.RegistersPane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FirstWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);

    int windowWidth = 860;
    int windowHeight = 500;

    public static RegistersPane registersPane;
    public static CoilsPane coilsPane;
    public static SettingPane settingPane;

    public FirstWindow() {
        setTitle("New_ModbusTool -ver0.0.0");
        setWidth(windowWidth);
        setHeight(windowHeight);
        setResizable(false);
        anchorPane.setStyle("-fx-background-color:#d7d7d7");

        setScene(scene);

        COMConnect comConnect = new COMConnect(windowWidth);  //COM连接pane
        anchorPane.getChildren().add(comConnect);
        AnchorPane.setBottomAnchor(comConnect, 0.0);

        registersPane = new RegistersPane();  //寄存器读写功能pane
        anchorPane.getChildren().add(registersPane);
        AnchorPane.setTopAnchor(registersPane, 50.0);
        AnchorPane.setLeftAnchor(registersPane, 25.0);

        coilsPane = new CoilsPane();  //线圈功能pane
        anchorPane.getChildren().add(coilsPane);
        AnchorPane.setTopAnchor(coilsPane, 50.0);
        AnchorPane.setLeftAnchor(coilsPane, 450.0);

        settingPane = new SettingPane(windowWidth);//设置功能pane
        anchorPane.getChildren().add(settingPane);

        ReadThread readThread = new ReadThread();  //modbus读取单线程
        readThread.start();

        show();
    }
}