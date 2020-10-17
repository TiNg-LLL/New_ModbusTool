package com.TiNg.windows;

import com.TiNg.pane.setting.SettingCoilsAddressPane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingCoilsAddressWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);
    SettingCoilsAddressPane settingCoilsAddressPane = new SettingCoilsAddressPane();

    public SettingCoilsAddressWindow() {
        setTitle("线圈地址设置(第一列为写入地址，第二列为读取地址)");
//        anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setResizable(false);
        setAlwaysOnTop(true);
        setScene(scene);

        anchorPane.getChildren().add(settingCoilsAddressPane);
    }

    public SettingCoilsAddressPane getSettingCoilsAddressPane() {
        return settingCoilsAddressPane;
    }
}
