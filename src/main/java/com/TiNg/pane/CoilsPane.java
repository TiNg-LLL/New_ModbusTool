package com.TiNg.pane;

import com.TiNg.datatreat.CoilDataFlashThread;
import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CoilsPane extends FlowPane {

    List<CoilSinglePane> list = new ArrayList<CoilSinglePane>();
    int coilsPaneQuantity = 10;  //寄存器功能数量

    Timeline timeline = new Timeline();
    List<boolean[]> listBoolean = new ArrayList<boolean[]>();
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = RegisterPane.dataTreat;

    Properties properties = new Properties();
    FileInputStream fileInputStream;
    BufferedReader bufferedReader;

    public CoilsPane() {
        try {
            fileInputStream = new FileInputStream("src/main/resources/address.properties");  //properties文件设置
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            properties.load(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < coilsPaneQuantity; i++) {
            list.add(new CoilSinglePane());
            listBoolean.add(list.get(i).getCoilReadThread().getB1());
        }

        getChildren().addAll(list);
        setHgap(5);  //设置左右间距
        setVgap(5);  //设置上下间距
        setPadding(new Insets(0));  //设置外边距
        setPrefWrapLength(305);
        //setStyle("-fx-background-color: #ff7a7a;" + "-fx-background-radius: 5");

        for (int i = 0; i < coilsPaneQuantity; i++) {  //初始设置
            Button button = (Button) list.get(i).getAnchorPane().lookup("#ButtonCoil");  //拿到名称label
            button.setText(properties.getProperty("ButtonCoilName" + (i + 1)));  //设置名称
            list.get(i).setCoilsWriteAddress(Integer.parseInt(properties.getProperty("CoilWriteAddress" + (i + 1))));  //设置寄存器写入地址
            if (Integer.parseInt(properties.getProperty("CoilReadAddress" + (i + 1))) == 0) {  //设置寄存器读取地址
                list.get(i).getCoilReadThread().setCoilReadAddress(dataTreat.coilAddressTransform(properties.getProperty("CoilWriteAddressMXY" + (i + 1)), Integer.parseInt(properties.getProperty("CoilWriteAddress" + (i + 1)))));
            } else {
                list.get(i).getCoilReadThread().setCoilReadAddress(dataTreat.coilAddressTransform(properties.getProperty("CoilReadAddressMXY" + (i + 1)), Integer.parseInt(properties.getProperty("CoilReadAddress" + (i + 1)))));
            }
        }

        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), "keyFrame", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (modbus.ModbusisConnected()) {
                    for (int i = 0; i < coilsPaneQuantity; i++) {
                        try {
                            if (listBoolean.get(i)[0]) {
                                list.get(i).getButton().setStyle("-fx-background-color: #26ff00");
                            } else {
                                list.get(i).getButton().setStyle("-fx-background-color: #878787");
                            }
                        } catch (Exception e) {
                            list.get(i).getButton().setStyle("-fx-background-color: #878787");
                        }
                    }
                } else {
                    for (int i = 0; i < coilsPaneQuantity; i++) {
                        list.get(i).getButton().setStyle("-fx-background-color: #878787");
                    }
                }
            }
        });

        timeline.getKeyFrames().addAll(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public List<boolean[]> getListBoolean() {
        return listBoolean;
    }

    public List<CoilSinglePane> getList() {
        return list;
    }

    public int getCoilsPaneQuantity() {
        return coilsPaneQuantity;
    }
}