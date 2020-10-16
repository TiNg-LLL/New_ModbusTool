package com.TiNg.windows;

import com.TiNg.pane.setting.SettingRegisterAddressPane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingRegisterAddressWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);
    SettingRegisterAddressPane settingRegisterAddressPane = new SettingRegisterAddressPane();

    public SettingRegisterAddressWindow() {
        setTitle("寄存器地址设置(第一列为写入地址，第二列为读取地址)");
//        anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setResizable(false);
        setAlwaysOnTop(true);
        setScene(scene);

        anchorPane.getChildren().add(settingRegisterAddressPane);
    }

    public SettingRegisterAddressPane getSettingRegisterAddressPane() {
        return settingRegisterAddressPane;
    }
}
