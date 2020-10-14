package com.TiNg.pane.registers;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.pane.COMConnect;
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

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/registerPaneFXML.fxml");
    AnchorPane anchorPane;  //fxml加载

    int registerWriteAddress;
    int registerReadAddress;
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = RegistersPane.dataTreat;
    Label label;
    TextField textField;
    Button button;
    int i;  //序号
    Boolean booleanRegisterDataToMM;  //是否转换为mm单位

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
                if (booleanRegisterDataToMM) {
                    float f = Float.parseFloat(textField.getText());
                    float f1 = (float) ((f / 0.5 * 12 * 1000) / 10);
                    int a = (int) f1;
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
                } else {
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
            }
        });
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

    public Label getLabel() {
        return label;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Boolean getBooleanRegisterDataToMM() {
        return booleanRegisterDataToMM;
    }

    public void setBooleanRegisterDataToMM(Boolean booleanRegisterDataToMM) {
        this.booleanRegisterDataToMM = booleanRegisterDataToMM;
    }
}