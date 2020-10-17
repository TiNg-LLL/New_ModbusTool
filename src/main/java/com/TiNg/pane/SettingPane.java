package com.TiNg.pane;

import com.TiNg.windows.SettingCoilsAddressWindow;
import com.TiNg.windows.SettingCoilsModeTransformWindow;
import com.TiNg.windows.SettingRegisterAddressWindow;
import com.TiNg.windows.SettingRegisterDatatommWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SettingPane extends AnchorPane {
    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/setting/settingPane.fxml");
    AnchorPane anchorPane;  //fxml加载

    MenuBar menuBar;
    Menu registerMenu;
    Menu coilsMenu;
    MenuItem registerAddressMenuItem;
    MenuItem registerDatatommMenuItem;
    MenuItem coilsAddressMenuItem;
    MenuItem coilsModeTransformMenuItem;

    public static SettingRegisterAddressWindow settingRegisterAddressWindow = new SettingRegisterAddressWindow();
    public static SettingRegisterDatatommWindow settingRegisterDatatommWindow = new SettingRegisterDatatommWindow();

    public static SettingCoilsAddressWindow settingCoilsAddressWindow = new SettingCoilsAddressWindow();
    public static SettingCoilsModeTransformWindow settingCoilsModeTransformWindow = new SettingCoilsModeTransformWindow();

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

        menuBar = (MenuBar) anchorPane.lookup("#SettingMenuBar");  //拿到设置menuBar

        registerMenu = menuBar.getMenus().get(0);  //拿到menuBar下的第一个按钮 registerMenu

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

        coilsMenu = menuBar.getMenus().get(1);  //拿到menuBar下的第一个按钮 coilsMenu

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
    }
}
