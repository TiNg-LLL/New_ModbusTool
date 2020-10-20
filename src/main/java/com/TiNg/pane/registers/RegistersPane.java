package com.TiNg.pane.registers;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.pane.COMConnect;
import com.TiNg.windows.FirstWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RegistersPane extends VBox {

    public static List<RegisterSinglePane> list = new ArrayList<RegisterSinglePane>();  //寄存器读写功能pane集合
    public static int registerPaneQuantity = 8;  //寄存器读写功能数量

    public static RegisterLabelPane registerLabelPane = new RegisterLabelPane();  //寄存器只读取功能pane

    Timeline timeline = new Timeline();
    List<int[]> listInt = new ArrayList<int[]>();
    Label label;
    Modbus modbus = COMConnect.modbus;

    Properties properties = DataTreat.properties;
    Properties propertiesAuto = DataTreat.propertiesAuto;

    DataTreat dataTreat = FirstWindow.dataTreat;

    public RegistersPane() {


        for (int i = 0; i < registerPaneQuantity; i++) {
            list.add(new RegisterSinglePane());
            list.get(i).setI(i);
            listInt.add(new int[2]);
        }
        setSpacing(5);  //设置上下间距
        //setStyle("-fx-background-color: #878787;"+ "-fx-background-radius: 5");
        getChildren().addAll(list);
        getChildren().add(registerLabelPane);  //只读取功能pane

        label = (Label) registerLabelPane.getAnchorPane().lookup("#LabelRegisterName");  //寄存器只读取功能pane设置
        label.setText(properties.getProperty("RegisterJustReadName1"));
        registerLabelPane.setRegisterReadAddress(Integer.parseInt(propertiesAuto.getProperty("RegisterJustReadAddress1")));
        registerLabelPane.setBooleanRegisterDataToMM(Boolean.valueOf(propertiesAuto.getProperty("RegisterJustReadDataToMM1")));

        for (int i = 0; i < registerPaneQuantity; i++) {  //读写功能初始设置
            label = (Label) list.get(i).getAnchorPane().lookup("#LabelRegisterName");  //拿到名称label
            label.setText(properties.getProperty("LabelRegisterName" + (i + 1)));  //设置名称

            list.get(i).setRegisterWriteAddress(Integer.parseInt(propertiesAuto.getProperty("RegisterWriteAddress" + (i + 1))));  //设置寄存器写入地址

            if (Integer.parseInt(propertiesAuto.getProperty("RegisterReadAddress" + (i + 1))) == 0) {  //设置寄存器读取地址
                list.get(i).setRegisterReadAddress(Integer.parseInt(propertiesAuto.getProperty("RegisterWriteAddress" + (i + 1))));
            } else {
                list.get(i).setRegisterReadAddress(Integer.parseInt(propertiesAuto.getProperty("RegisterReadAddress" + (i + 1))));
            }
            list.get(i).setBooleanRegisterDataToMM(Boolean.valueOf(propertiesAuto.getProperty("RegisterDataToMM" + (i + 1))));  //设置是否转换为mm单位
        }

        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), "keyFrame", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (modbus.ModbusisConnected()) {
                    for (int i = 0; i < registerPaneQuantity; i++) {
                        try {
                            if (list.get(i).getBooleanRegisterDataToMM()) {
                                list.get(i).getLabel().setText(String.valueOf(dataTreat.registerDataToMM(listInt.get(i))));
                            } else {
                                list.get(i).getLabel().setText(Integer.toString(dataTreat.readTenToBinary(listInt.get(i))));
                            }
                        } catch (Exception e) {
                            list.get(i).getLabel().setText("null1");
                        }
                        try {
                            if (registerLabelPane.getBooleanRegisterDataToMM()) {
                                registerLabelPane.getLabel().setText(String.valueOf(dataTreat.registerDataToMM(registerLabelPane.getI())));
                            } else {
                                registerLabelPane.getLabel().setText(String.valueOf(dataTreat.readTenToBinary(registerLabelPane.getI())));
                            }
                        } catch (Exception e) {
                            registerLabelPane.getLabel().setText("null1");
                        }
                    }
                } else {
                    for (int i = 0; i < registerPaneQuantity; i++) {
                        list.get(i).getLabel().setText("null");
                    }
                    registerLabelPane.getLabel().setText("null");
                }
            }
        });

        timeline.getKeyFrames().addAll(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public List<int[]> getListInt() {
        return listInt;
    }

    public List<RegisterSinglePane> getList() {
        return list;
    }

    public int getRegisterPaneQuantity() {
        return registerPaneQuantity;
    }

    public RegisterLabelPane getRegisterLabelPane() {
        return registerLabelPane;
    }
}