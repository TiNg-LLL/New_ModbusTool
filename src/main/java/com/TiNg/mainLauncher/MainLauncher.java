package com.TiNg.mainLauncher;

import com.TiNg.pane.SettingPane;
import com.TiNg.windows.FirstWindow;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainLauncher extends Application {
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static FirstWindow firstWindow;


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        firstWindow = new FirstWindow();
        SettingPane.settingRegisterAddressWindow.initOwner(MainLauncher.firstWindow);
        SettingPane.settingRegisterAddressWindow.initModality(Modality.WINDOW_MODAL);
        SettingPane.settingRegisterDatatommWindow.initOwner(MainLauncher.firstWindow);
        SettingPane.settingRegisterDatatommWindow.initModality(Modality.WINDOW_MODAL);
        SettingPane.settingCoilsAddressWindow.initOwner(MainLauncher.firstWindow);
        SettingPane.settingCoilsAddressWindow.initModality(Modality.WINDOW_MODAL);
        SettingPane.settingCoilsModeTransformWindow.initOwner(MainLauncher.firstWindow);
        SettingPane.settingCoilsModeTransformWindow.initModality(Modality.WINDOW_MODAL);
        System.out.println(df.format(new Date()) + " " + "主窗口已生成");
    }

    @Override
    public void stop() throws Exception {
        System.out.println(df.format(new Date()) + " " + "主窗口已退出");
        System.exit(0);
    }
}
