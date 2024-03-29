package com.TiNg.pane.coils;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.pane.COMConnect;
import com.TiNg.windows.FirstWindow;
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

    public static List<CoilSinglePane> list = new ArrayList<CoilSinglePane>();  //线圈功能pane集合
    public static int coilsPaneQuantity;  //线圈功能数量

    Timeline timeline = new Timeline();
    List<boolean[]> listBoolean = new ArrayList<boolean[]>();
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = FirstWindow.dataTreat;
    Button button;

    Properties properties = DataTreat.properties;
    Properties propertiesAuto = DataTreat.propertiesAuto;

    public CoilsPane() {
        coilsPaneQuantity = Integer.parseInt(properties.getProperty("CoilsPaneQuantity"));

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
            button = (Button) list.get(i).getAnchorPane().lookup("#ButtonCoil");  //拿到线圈名称label
            button.setText(properties.getProperty("ButtonCoilName" + (i + 1)));  //设置线圈名称

            list.get(i).setCoilsWriteAddress(Integer.parseInt(propertiesAuto.getProperty("CoilWriteAddress" + (i + 1))));  //设置线圈写入地址
            list.get(i).setCoilWriteAddressMXY(propertiesAuto.getProperty("CoilWriteAddressMXY" + (i + 1)));//设置线圈写入MXY

            if (Integer.parseInt(propertiesAuto.getProperty("CoilReadAddress" + (i + 1))) == 0) {  //设置线圈读取地址
                list.get(i).setCoilsReadAddress(Integer.parseInt(propertiesAuto.getProperty("CoilWriteAddress" + (i + 1))));
                list.get(i).setCoilReadAddressMXY(propertiesAuto.getProperty("CoilWriteAddressMXY" + (i + 1)));
            } else {
                list.get(i).setCoilsReadAddress(Integer.parseInt(propertiesAuto.getProperty("CoilReadAddress" + (i + 1))));
                list.get(i).setCoilReadAddressMXY(propertiesAuto.getProperty("CoilReadAddressMXY" + (i + 1)));
            }

            list.get(i).setBooleanCoilModeTransform(Boolean.valueOf(propertiesAuto.getProperty("CoilModeTransform" + (i + 1))));  //设置线圈模式
        }

        list.get(0).getAnchorPane().setStyle("-fx-background-color: #29bf12;" + "-fx-background-radius:5");
        list.get(1).getAnchorPane().setStyle("-fx-background-color: #e72f17;" + "-fx-background-radius:5");
        list.get(2).getAnchorPane().setStyle("-fx-background-color: #ffbe00;" + "-fx-background-radius:5");

        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), "keyFrame", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (modbus.ModbusisConnected()) {
                    for (int i = 0; i < coilsPaneQuantity; i++) {
                        try {
                            if (listBoolean.get(i)[0]) {
                                list.get(i).getButton().setId("coilButtonTure");
                            } else {
                                if (!list.get(i).getButton().isDisabled()) {
                                    list.get(i).getButton().setId("coilButtonFalse");
                                } else {
                                    list.get(i).getButton().setId("coilButtonDisable");
                                }
                            }
                        } catch (Exception e) {
                            if (!list.get(i).getButton().isDisabled()) {
                                list.get(i).getButton().setId("coilButtonFalse");
                            } else {
                                list.get(i).getButton().setId("coilButtonDisable");
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < coilsPaneQuantity; i++) {
                        if (!list.get(i).getButton().isDisabled()) {
                            list.get(i).getButton().setId("coilButtonFalse");
                        } else {
                            list.get(i).getButton().setId("coilButtonDisable");
                        }
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

    public Button getButton() {
        return button;
    }
}