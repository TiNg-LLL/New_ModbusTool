package com.TiNg.mainLauncher;

import com.TiNg.windows.FirstWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainLauncher extends Application {
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void start(Stage primaryStage) throws Exception {
        FirstWindow firstWindow = new FirstWindow();
        System.out.println(df.format(new Date()) + " " + "主窗口已生成");
    }

    @Override
    public void stop() throws Exception {
        System.out.println(df.format(new Date()) + " " + "主窗口已退出");
        System.exit(0);
    }
}
