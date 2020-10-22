package com.TiNg.pane;

import com.TiNg.pane.coils.CoilSinglePane;
import com.TiNg.pane.coils.CoilsPane;
import com.TiNg.pane.registers.RegisterLabelPane;
import com.TiNg.pane.registers.RegisterSinglePane;
import com.TiNg.pane.registers.RegistersPane;
import com.TiNg.windows.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class SettingPane extends AnchorPane {
    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/setting/settingPane.fxml");
    AnchorPane anchorPane;  //fxml加载

    List<RegisterSinglePane> registerList = RegistersPane.list;  //寄存器读写功能pane集合
    RegisterLabelPane registerLabelPane = RegistersPane.registerLabelPane;  //寄存器只读取功能pane
    List<CoilSinglePane> coilsList = CoilsPane.list;  //线圈功能pane集合

    MenuBar menuBar;
    Menu registerMenu;
    Menu coilsMenu;
    Menu addressSaveMenu;
    Menu adminMenu;
    MenuItem registerAddressMenuItem;
    MenuItem registerDatatommMenuItem;
    MenuItem coilsAddressMenuItem;
    MenuItem coilsModeTransformMenuItem;
    MenuItem addressSaveMenuItem;
    MenuItem addressReadMenuItem;
    MenuItem adminInMenuItem;
    MenuItem adminOutMenuItem;
    public static Label messageLabel;

    public static SettingRegisterAddressWindow settingRegisterAddressWindow = new SettingRegisterAddressWindow();
    public static SettingRegisterDatatommWindow settingRegisterDatatommWindow = new SettingRegisterDatatommWindow();

    public static SettingCoilsAddressWindow settingCoilsAddressWindow = new SettingCoilsAddressWindow();
    public static SettingCoilsModeTransformWindow settingCoilsModeTransformWindow = new SettingCoilsModeTransformWindow();

    public static SettingAdminPasswordWindow settingAdminPasswordWindow = new SettingAdminPasswordWindow();

    public SettingPane(int width) {
        setPrefWidth(width);
        setStyle("-fx-background-color:#b3b3b3");

        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getChildren().add(anchorPane);

        messageLabel = (Label) anchorPane.lookup("#MessageLabel");  //拿到信息label

        menuBar = (MenuBar) anchorPane.lookup("#SettingMenuBar");  //拿到设置menuBar

        registerMenu = menuBar.getMenus().get(0);  //拿到menuBar下的第一个按钮 registerMenu

        registerMenu.setDisable(true);

        registerAddressMenuItem = registerMenu.getItems().get(0);  //拿到registerMenu下的第一个按钮

        registerDatatommMenuItem = registerMenu.getItems().get(1);  //拿到registerMenu下的第二个按钮

        registerAddressMenuItem.setOnAction(new EventHandler<ActionEvent>() {  //第一个按钮动作
            @Override
            public void handle(ActionEvent event) {
                settingRegisterAddressWindow.getSettingRegisterAddressPane().textFieldFlash();
                settingRegisterAddressWindow.show();
            }
        });

        registerDatatommMenuItem.setOnAction(new EventHandler<ActionEvent>() {  //第二个按钮动作
            @Override
            public void handle(ActionEvent event) {
                settingRegisterDatatommWindow.getSettingRegisterDatatommPane().dataFlash();
                settingRegisterDatatommWindow.show();
            }
        });

        coilsMenu = menuBar.getMenus().get(1);  //拿到menuBar下的第二个按钮 coilsMenu

        coilsMenu.setDisable(true);

        coilsAddressMenuItem = coilsMenu.getItems().get(0);  //拿到coilsMenu下的第一个按钮

        coilsModeTransformMenuItem = coilsMenu.getItems().get(1);  //拿到coilsMenu下的第二个按钮

        coilsAddressMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settingCoilsAddressWindow.getSettingCoilsAddressPane().textFieldFlash();
                settingCoilsAddressWindow.show();
            }
        });

        coilsModeTransformMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settingCoilsModeTransformWindow.getSettingCoilsModeTransformPane().textFieldFlash();
                settingCoilsModeTransformWindow.show();
            }
        });

        addressSaveMenu = menuBar.getMenus().get(2);  //拿到menuBar下的第三个按钮 coilsMenu

        addressSaveMenu.setDisable(true);

        addressSaveMenuItem = addressSaveMenu.getItems().get(0);  //拿到addressSaveMenu下的第一个按钮

        addressReadMenuItem = addressSaveMenu.getItems().get(1);  //拿到addressSaveMenu下的第二个按钮

        addressSaveMenuItem.setOnAction(new EventHandler<ActionEvent>() {  //地址数据保存至本地按钮
            @Override
            public void handle(ActionEvent event) {
                Properties autoProperties = new Properties();

                for (int i = 0; i < registerList.size(); i++) {  //寄存器数据写入
                    autoProperties.setProperty("RegisterWriteAddress" + (i + 1), String.valueOf(registerList.get(i).getRegisterWriteAddress()));
                }
                for (int i = 0; i < registerList.size(); i++) {
                    autoProperties.setProperty("RegisterReadAddress" + (i + 1), String.valueOf(registerList.get(i).getRegisterReadAddress()));
                }
                for (int i = 0; i < registerList.size(); i++) {
                    autoProperties.setProperty("RegisterDataToMM" + (i + 1), String.valueOf(registerList.get(i).getBooleanRegisterDataToMM()));
                }
                autoProperties.setProperty("RegisterJustReadAddress1", String.valueOf(registerLabelPane.getRegisterReadAddress()));
                autoProperties.setProperty("RegisterJustReadDataToMM1", String.valueOf(registerLabelPane.getBooleanRegisterDataToMM()));

                for (int i = 0; i < coilsList.size(); i++) {  //线圈数据写入
                    autoProperties.setProperty("CoilWriteAddressMXY" + (i + 1), coilsList.get(i).getCoilWriteAddressMXY());
                }
                for (int i = 0; i < coilsList.size(); i++) {
                    autoProperties.setProperty("CoilWriteAddress" + (i + 1), String.valueOf(coilsList.get(i).getCoilsWriteAddress()));
                }
                for (int i = 0; i < coilsList.size(); i++) {
                    autoProperties.setProperty("CoilReadAddressMXY" + (i + 1), coilsList.get(i).getCoilReadAddressMXY());
                }
                for (int i = 0; i < coilsList.size(); i++) {
                    autoProperties.setProperty("CoilReadAddress" + (i + 1), String.valueOf(coilsList.get(i).getCoilsReadAddress()));
                }
                for (int i = 0; i < coilsList.size(); i++) {
                    autoProperties.setProperty("CoilModeTransform" + (i + 1), String.valueOf(coilsList.get(i).getBooleanCoilModeTransform()));
                }

                try {  //写入流
                    FileOutputStream outputStream = new FileOutputStream("src/main/resources/Autoaddress.properties");
                    autoProperties.store(outputStream, null);
                } catch (Exception e) {

                }
                SettingPane.messageLabel.setText("数据已保存至本地");
            }
        });

        adminMenu = menuBar.getMenus().get(3);  //拿到menuBar下的第三个按钮 coilsMenu

        adminInMenuItem = adminMenu.getItems().get(0);  //拿到addressSaveMenu下的第一个按钮

        adminOutMenuItem = adminMenu.getItems().get(1);  //拿到addressSaveMenu下的第二个按钮

        adminInMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settingAdminPasswordWindow = new SettingAdminPasswordWindow();
                settingAdminPasswordWindow.show();
            }
        });

        adminOutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settingAdminPasswordWindow.getSettingAdminPasswordPane().adminOut();
            }
        });
    }

    public Menu getRegisterMenu() {
        return registerMenu;
    }

    public Menu getCoilsMenu() {
        return coilsMenu;
    }

    public Menu getAddressSaveMenu() {
        return addressSaveMenu;
    }
}
