package com.TiNg.pane.setting;

import com.TiNg.pane.SettingPane;
import com.TiNg.windows.FirstWindow;
import com.TiNg.windows.SettingAdminPasswordWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SettingAdminPasswordPane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/setting/settingAdminPasswordPane.fxml");
    AnchorPane anchorPane;  //fxml加载

    PasswordField passwordField;
    Button button;

    SettingPane settingPane = FirstWindow.settingPane;

    public SettingAdminPasswordPane() {
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);


        passwordField = (PasswordField) anchorPane.lookup("#SettingAdminPasswordField");  //拿到密码输入框

        button = (Button) anchorPane.lookup("#SettingButtonAdminPassword");  //拿到密码登入按钮

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingAdminPasswordWindow settingAdminPasswordWindow = SettingPane.settingAdminPasswordWindow;
                if (String.valueOf(passwordField.getText()).equals("123456")) {
                    settingPane.getRegisterMenu().setDisable(false);
                    settingPane.getCoilsMenu().setDisable(false);
                    settingPane.getAddressSaveMenu().setDisable(false);
                    settingAdminPasswordWindow.close();
                }
            }
        });
    }

    public void adminOut() {
        settingPane.getRegisterMenu().setDisable(true);
        settingPane.getCoilsMenu().setDisable(true);
        settingPane.getAddressSaveMenu().setDisable(true);
    }
}
