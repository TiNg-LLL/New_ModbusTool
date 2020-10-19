package com.TiNg.windows;

import com.TiNg.pane.setting.SettingCoilsModeTransformPane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingCoilsModeTransformWindow extends Stage {
    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane);
    SettingCoilsModeTransformPane settingCoilsModeTransformPane = new SettingCoilsModeTransformPane();

    public SettingCoilsModeTransformWindow() {
        setTitle("线圈功能设置");
//        anchorPane.setStyle("-fx-background-color:#FFB6C1");
        setResizable(false);
        setAlwaysOnTop(true);
        getIcons().add(new Image("/icon/gear-icon.png"));
        setScene(scene);

        anchorPane.getChildren().add(settingCoilsModeTransformPane);
    }

    public SettingCoilsModeTransformPane getSettingCoilsModeTransformPane() {
        return settingCoilsModeTransformPane;
    }
}
