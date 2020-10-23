package com.TiNg.pane.setting;

import com.TiNg.mainLauncher.MainLauncher;
import com.TiNg.pane.COMConnect;
import com.TiNg.pane.SettingPane;
import com.TiNg.pane.coils.CoilSinglePane;
import com.TiNg.pane.coils.CoilsPane;
import com.TiNg.pane.registers.RegisterSinglePane;
import com.TiNg.pane.registers.RegistersPane;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SettingAdminPasswordPane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/setting/settingAdminPasswordPane.fxml");
    AnchorPane anchorPane;  //fxml加载

    PasswordField passwordField;
    Button button;

    COMConnect comConnect = FirstWindow.comConnect;
    SettingPane settingPane = FirstWindow.settingPane;
    List<RegisterSinglePane> registersList = RegistersPane.list;
    List<CoilSinglePane> coilsList = CoilsPane.list;
    SimpleDateFormat df = MainLauncher.df;

    int[] i = {0, 1, 2, 3, 4};  //寄存器pane集合中需要权限的下标
    int[] i1 = {8, 9, 10, 11};  //线圈pane集合中需要权限的下标

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

        comConnect.getComboBoxBaudrate().setDisable(true);
        comConnect.getComboBoxDataBits().setDisable(true);
        comConnect.getComboBoxStopBits().setDisable(true);
        comConnect.getComboBoxEvenODD().setDisable(true);

        for (int j = 0; j < registersList.size(); j++) {  //初始设置寄存器权限
            if (Arrays.binarySearch(i, registersList.get(j).getI()) >= 0) {
                registersList.get(j).getButton().setDisable(true);
            }
        }

        for (int j = 0; j < coilsList.size(); j++) {  //初始设置线圈权限
            if (Arrays.binarySearch(i1, coilsList.get(j).getI()) >= 0) {
                coilsList.get(j).getButton().setDisable(true);
            }
        }

        passwordField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingAdminPasswordPane.this.adminIn();
            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingAdminPasswordPane.this.adminIn();
            }
        });
    }

    public void adminIn() {
        SettingAdminPasswordWindow settingAdminPasswordWindow = SettingPane.settingAdminPasswordWindow;
        if (String.valueOf(passwordField.getText()).equals("0123")) {
            System.out.println(df.format(new Date()) + " " + "使用正确密码登入");
            comConnect.getComboBoxBaudrate().setDisable(false);
            comConnect.getComboBoxDataBits().setDisable(false);
            comConnect.getComboBoxStopBits().setDisable(false);
            comConnect.getComboBoxEvenODD().setDisable(false);

            settingPane.getRegisterMenu().setDisable(false);
            settingPane.getCoilsMenu().setDisable(false);
            settingPane.getAddressSaveMenu().setDisable(false);


            for (int j = 0; j < registersList.size(); j++) {
                if (Arrays.binarySearch(i, registersList.get(j).getI()) >= 0) {
                    registersList.get(j).getButton().setDisable(false);
                }
            }
            for (int j = 0; j < coilsList.size(); j++) {
                if (Arrays.binarySearch(i1, coilsList.get(j).getI()) >= 0) {
                    coilsList.get(j).getButton().setDisable(false);
                }
            }
            settingAdminPasswordWindow.close();
        } else {
            System.out.println(df.format(new Date()) + " " + "使用" + passwordField.getText() + "错误密码尝试登入");
        }
        if (String.valueOf(passwordField.getText()).equals("1")) {
            System.out.println(df.format(new Date()) + " " + "使用正确密码登入");
            comConnect.getComboBoxBaudrate().setDisable(false);
            comConnect.getComboBoxDataBits().setDisable(false);
            comConnect.getComboBoxStopBits().setDisable(false);
            comConnect.getComboBoxEvenODD().setDisable(false);
            settingAdminPasswordWindow.close();
        }
    }

    public void adminOut() {
        System.out.println(df.format(new Date()) + " " + "登出");
        comConnect.getComboBoxBaudrate().setDisable(true);
        comConnect.getComboBoxDataBits().setDisable(true);
        comConnect.getComboBoxStopBits().setDisable(true);
        comConnect.getComboBoxEvenODD().setDisable(true);

        settingPane.getRegisterMenu().setDisable(true);
        settingPane.getCoilsMenu().setDisable(true);
        settingPane.getAddressSaveMenu().setDisable(true);

        for (int j = 0; j < registersList.size(); j++) {
            if (Arrays.binarySearch(i, registersList.get(j).getI()) >= 0) {
                registersList.get(j).getButton().setDisable(true);
            }
        }

        for (int j = 0; j < coilsList.size(); j++) {
            if (Arrays.binarySearch(i1, coilsList.get(j).getI()) >= 0) {
                coilsList.get(j).getButton().setDisable(true);
            }
        }
    }
}
