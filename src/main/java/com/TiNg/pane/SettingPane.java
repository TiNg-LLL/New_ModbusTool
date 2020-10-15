package com.TiNg.pane;

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
    URL url = fxmlLoader.getClassLoader().getResource("views/settingPaneFXML.fxml");
    AnchorPane anchorPane;  //fxml加载

    MenuBar menuBar;
    Menu registerMenu;
    MenuItem registerAddressMenuItem;
    MenuItem registerDatatommMenuItem;

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

        registerAddressMenuItem = registerMenu.getItems().sorted().get(0);  //拿到registerMenu下的第一个按钮

        registerDatatommMenuItem = registerMenu.getItems().sorted().get(1);  //拿到registerMenu下的第二个按钮

        registerAddressMenuItem.setOnAction(new EventHandler<ActionEvent>() {  //第一个按钮动作
            @Override
            public void handle(ActionEvent event) {
                SettingRegisterAddressWindow settingRegisterAddressWindow = new SettingRegisterAddressWindow();
                settingRegisterAddressWindow.show();
            }
        });

        registerDatatommMenuItem.setOnAction(new EventHandler<ActionEvent>() {  //第二个按钮动作
            @Override
            public void handle(ActionEvent event) {
                SettingRegisterDatatommWindow settingRegisterDatatommWindow = new SettingRegisterDatatommWindow();
                settingRegisterDatatommWindow.show();
            }
        });
    }
}
