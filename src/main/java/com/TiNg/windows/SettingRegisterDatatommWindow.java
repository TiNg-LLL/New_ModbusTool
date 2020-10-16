package com.TiNg.windows;

import com.TiNg.pane.setting.SettingRegisterAddressPane;
import com.TiNg.pane.setting.SettingRegisterDatatommPane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingRegisterDatatommWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);
    SettingRegisterDatatommPane settingRegisterDatatommPane = new SettingRegisterDatatommPane();

    public SettingRegisterDatatommWindow() {
        setTitle("寄存器数据单位设置");
        setResizable(false);
        setAlwaysOnTop(true);
        //anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setScene(scene);

        anchorPane.getChildren().add(settingRegisterDatatommPane);
    }

    public SettingRegisterDatatommPane getSettingRegisterDatatommPane() {
        return settingRegisterDatatommPane;
    }
}
