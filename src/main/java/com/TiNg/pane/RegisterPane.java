package com.TiNg.pane;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RegisterPane extends VBox {

    List<RegisterSinglePane> list = new ArrayList<RegisterSinglePane>();
    int registerPaneQuantity = 14;  //寄存器功能数量

    Properties properties = new Properties();
    FileInputStream fileInputStream;
    BufferedReader bufferedReader;

    public RegisterPane() {
        try {
            fileInputStream = new FileInputStream("src/main/resources/address.properties");  //properties文件设置
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            properties.load(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < registerPaneQuantity; i++) {
            list.add(new RegisterSinglePane());
        }
        setSpacing(5);  //设置上下间距
        getChildren().addAll(list);

        for (int i = 0; i < registerPaneQuantity; i++) {
            Label label = (Label) list.get(i).getAnchorPane().lookup("#LabelRegisterName");
            label.setText(properties.getProperty("LabelRegisterName" + (i + 1)));
            list.get(i).setRegisterWriteAddress(Integer.parseInt(properties.getProperty("RegisterWriteAddress" + (i + 1))));
            if ((properties.getProperty("RegisterReadAddress" + (i + 1))) == null) {
                list.get(i).setRegisterReadAddress(Integer.parseInt(properties.getProperty("RegisterWriteAddress" + (i + 1))));
            } else {
                list.get(i).setRegisterReadAddress(Integer.parseInt(properties.getProperty("RegisterReadAddress" + (i + 1))));
            }
        }
    }
}