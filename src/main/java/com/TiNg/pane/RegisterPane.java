package com.TiNg.pane;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RegisterPane extends VBox {

    List<RegisterSinglePane> list = new ArrayList<RegisterSinglePane>();
    int registerPaneQuantity = 15;  //寄存器功能数量

    Timeline timeline = new Timeline();
    List<int[]> listInt = new ArrayList<int[]>();
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = new DataTreat();

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
            listInt.add(list.get(i).getRegisterReadThread().getI1());
        }
        setSpacing(2);  //设置上下间距
        setStyle("-fx-background-color: #adadad;"+ "-fx-background-radius: 5");
        getChildren().addAll(list);

        for (int i = 0; i < registerPaneQuantity; i++) {  //初始设置
            Label label = (Label) list.get(i).getAnchorPane().lookup("#LabelRegisterName");  //拿到名称label
            label.setText(properties.getProperty("LabelRegisterName" + (i + 1)));  //设置名称
            list.get(i).setRegisterWriteAddress(Integer.parseInt(properties.getProperty("RegisterWriteAddress" + (i + 1))));  //设置寄存器写入地址
            if (Integer.parseInt(properties.getProperty("RegisterReadAddress" + (i + 1))) == 0) {  //设置寄存器读取地址
                list.get(i).getRegisterReadThread().setRegisterReadAddress(Integer.parseInt(properties.getProperty("RegisterWriteAddress" + (i + 1))));
            } else {
                list.get(i).getRegisterReadThread().setRegisterReadAddress(Integer.parseInt(properties.getProperty("RegisterReadAddress" + (i + 1))));
            }
        }

        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), "keyFrame", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (modbus.ModbusisConnected()) {
                    for (int i = 0; i < registerPaneQuantity; i++) {
                        try {
                            listInt.set(i, list.get(i).getRegisterReadThread().getI1());
                            list.get(i).getLabel().setText(Integer.toString(dataTreat.readtenToBinary(listInt.get(i))));
                        } catch (Exception e) {
                            list.get(i).getLabel().setText("null");
                        }

                    }
                } else {
                    for (int i = 0; i < registerPaneQuantity; i++) {
                        list.get(i).getLabel().setText("null");
                    }
                }
            }
        });

        timeline.getKeyFrames().addAll(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}