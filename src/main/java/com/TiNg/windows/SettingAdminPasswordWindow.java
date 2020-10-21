package com.TiNg.windows;

import com.TiNg.pane.setting.SettingAdminPasswordPane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingAdminPasswordWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);
    SettingAdminPasswordPane settingAdminPasswordPane = new SettingAdminPasswordPane();

    public SettingAdminPasswordWindow() {
        setTitle("获取权限");
//        anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setResizable(false);
        setAlwaysOnTop(true);
        getIcons().add(new Image("/icon/key-icon.png"));
        setScene(scene);

        anchorPane.getChildren().add(settingAdminPasswordPane);
    }


    public SettingAdminPasswordPane getSettingAdminPasswordPane() {
        return settingAdminPasswordPane;
    }
}
