package com.TiNg.pane;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.datatreat.RegisterReadThread;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;

public class RegisterSinglePane extends AnchorPane {
    RegisterReadThread registerReadThread;  //读取线程

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/registerPaneFXML.fxml");
    AnchorPane anchorPane;  //fxml加载

    int registerWriteAddress;
    int registerReadAddress;
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = RegisterPane.dataTreat;
    Label label;
    TextField textField;
    Button button;

    public RegisterSinglePane() {
        fxmlLoader.setLocation(url);  //加载fxml文件

        URL cssURL = this.getClass().getClassLoader().getResource("styles/ButtonStyles.css");  //加载css文件
        this.getStylesheets().add(cssURL.toExternalForm());

        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        label = (Label) anchorPane.lookup("#LabelRegisterValue");  //拿到label

        textField = (TextField) anchorPane.lookup("#TextFieldRegister");  //拿到textField

        button = (Button) anchorPane.lookup("#ButtonRegister");  //拿到button

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int a = Integer.valueOf(textField.getText());

                if (a < 32768) {
                    modbus.ModbuswriteSingleRegister(1, registerWriteAddress, a);
                    modbus.ModbuswriteSingleRegister(1, registerWriteAddress + 1, 0);
                } else {
                    if (a < 65536) {
                        modbus.ModbuswriteSingleRegister(1, registerWriteAddress, a);
                        modbus.ModbuswriteSingleRegister(1, registerWriteAddress + 1, 0);
                    } else {
                        modbus.ModbuswriteSingleRegister(1, registerWriteAddress, dataTreat.tenToBinary(a)[0]);
                        modbus.ModbuswriteSingleRegister(1, registerWriteAddress + 1, dataTreat.tenToBinary(a)[1]);
                    }
                }
            }
        });


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