package com.TiNg.pane.coils;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.pane.COMConnect;
import com.TiNg.pane.registers.RegistersPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CoilsPane extends FlowPane {

    List<CoilSinglePane> list = new ArrayList<CoilSinglePane>();
    int coilsPaneQuantity = 12;  //寄存器功能数量

    Timeline timeline = new Timeline();
    List<boolean[]> listBoolean = new ArrayList<boolean[]>();
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = RegistersPane.dataTreat;

    Properties properties = RegistersPane.properties;

    public CoilsPane() {

        for (int i = 0; i < coilsPaneQuantity; i++) {
            list.add(new CoilSinglePane());
            list.get(i).setI(i);
            listBoolean.add(new boolean[0]);
        }

        getChildren().addAll(list);
        setHgap(5);  //设置左右间距
        setVgap(5);  //设置上下间距
        setPadding(new Insets(0));  //设置外边距
        setPrefWrapLength(460);
        //setStyle("-fx-background-color: #ff7a7a;" + "-fx-background-radius: 5");

        for (int i = 0; i < coilsPaneQuantity; i++) {  //初始设置
            Button button = (Button) list.get(i).getAnchorPane().lookup("#ButtonCoil");  //拿到名称label
            button.setText(properties.getProperty("ButtonCoilName" + (i + 1)));  //设置名称

            list.get(i).setCoilsWriteAddress(dataTreat.coilAddressTransform(properties.getProperty("CoilWriteAddressMXY" + (i + 1)), Integer.parseInt(properties.getProperty("CoilWriteAddress" + (i + 1)))));  //设置寄存器写入地址

            if (Integer.parseInt(properties.getProperty("CoilReadAddress" + (i + 1))) == 0) {  //设置寄存器读取地址
                list.get(i).setCoilsReadAddress(dataTreat.coilAddressTransform(properties.getProperty("CoilWriteAddressMXY" + (i + 1)), Integer.parseInt(properties.getProperty("CoilWriteAddress" + (i + 1)))));
            } else {
                list.get(i).setCoilsReadAddress(dataTreat.coilAddressTransform(properties.getProperty("CoilReadAddressMXY" + (i + 1)), Integer.parseInt(properties.getProperty("CoilReadAddress" + (i + 1)))));
            }
        }

        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), "keyFrame", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (modbus.ModbusisConnected()) {
                    for (int i = 0; i < coilsPaneQuantity; i++) {
                        try {
                            if (listBoolean.get(i)[0]) {
                                list.get(i).getButton().setId("coilButtonTure");
                            } else {
                                list.get(i).getButton().setId("coilButtonFalse");
                            }
                        } catch (Exception e) {
                            list.get(i).getButton().setId("coilButtonFalse");
                        }
                    }
                } else {
                    for (int i = 0; i < coilsPaneQuantity; i++) {
                        list.get(i).getButton().setId("coilButtonFalse");
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