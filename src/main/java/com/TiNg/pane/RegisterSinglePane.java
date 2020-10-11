package com.TiNg.pane;

import com.TiNg.datatreat.RegisterReadThread;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;

public class RegisterSinglePane extends AnchorPane {
    RegisterReadThread registerReadThread;

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/registerPaneFXML.fxml");
    AnchorPane anchorPane;
    int registerWriteAddress;
    int registerReadAddress;
    Button button;
    Label label;

    public RegisterSinglePane() {
        fxmlLoader.setLocation(url);  //加载fxml文件
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        button = (Button) anchorPane.lookup("#ButtonRegister");  //拿到button

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("123");
            }
        });

        label = (Label) anchorPane.lookup("#LabelRegisterValue");  //拿到label

//        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), "keyFrame", new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    label.setText(Integer.toString(dataTreat.readtenToBinary(modbus.ModbusreadHoldingRegisters(1, registerReadAddress, 2))));
//                }catch (Exception e){
//                    label.setText("null");
//                    e.printStackTrace();
//                }
//                System.out.println(registerReadAddress);
//                //System.out.println("123123");
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//
//        timeline.getKeyFrames().addAll(keyFrame);
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();

/*        try {
            if (modbus.ModbusisConnected()) {
                int[] i1 = modbus.ModbusreadHoldingRegisters(1, registerReadAddress, 2);
                label.setText(Integer.toString(dataTreat.readtenToBinary(i1)));
            } else {
                label.setText("123");
            }
        } catch (Exception e) {
            label.setText("null");
        }*/

        registerReadThread = new RegisterReadThread();
        registerReadThread.start();
    }


    //get、set方法
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public int getRegisterWriteAddress() {
        return registerWriteAddress;
    }

    public int getRegisterReadAddress() {
        return registerReadAddress;
    }

    public void setRegisterWriteAddress(int registerWriteAddress) {
        this.registerWriteAddress = registerWriteAddress;
    }

    public void setRegisterReadAddress(int registerReadAddress) {
        this.registerReadAddress = registerReadAddress;
    }

    public RegisterReadThread getRegisterReadThread() {
        return registerReadThread;
    }
    public Label getLabel() {
        return label;
    }
}