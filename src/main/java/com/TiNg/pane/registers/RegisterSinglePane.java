package com.TiNg.pane.registers;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.mainLauncher.MainLauncher;
import com.TiNg.pane.COMConnect;
import com.TiNg.pane.SettingPane;
import com.TiNg.windows.FirstWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterSinglePane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/registerPaneFXML.fxml");
    AnchorPane anchorPane;  //fxml加载

    int registerWriteAddress;
    int registerReadAddress;
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = FirstWindow.dataTreat;
    SimpleDateFormat df = MainLauncher.df;
    Label labelName;
    Label label;
    TextField textField;
    Button button;
    int i;  //序号
    Boolean booleanRegisterDataToMM;  //是否转换为mm单位

    public RegisterSinglePane() {
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL cssURL = this.getClass().getClassLoader().getResource("styles/ButtonStyles.css");  //加载css文件
        this.getStylesheets().add(cssURL.toExternalForm());

        getChildren().add(anchorPane);

        labelName = (Label) anchorPane.lookup("#LabelRegisterName");  //拿到名称label

        label = (Label) anchorPane.lookup("#LabelRegisterValue");  //拿到读取值label

        textField = (TextField) anchorPane.lookup("#TextFieldRegister");  //拿到textField

        button = (Button) anchorPane.lookup("#ButtonRegister");  //拿到button

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (booleanRegisterDataToMM) {
                        Double f = Double.parseDouble(textField.getText());
                        Double f1 = (((f / ((DataTreat.luoju) / 10)) * DataTreat.wulisubi * DataTreat.bujinxifen) / 10);
                        int a = new Double(f1).intValue();
                        if (a < 32768) {
                            modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress), a);
                            modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress) + 1, 0);
                        } else {
                            if (a < 65536) {
                                modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress), a);
                                modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress) + 1, 0);
                            } else {
                                modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress), dataTreat.tenToBinary(a)[0]);
                                modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress) + 1, dataTreat.tenToBinary(a)[1]);
                            }
                        }
                    } else {
                        int a = Integer.valueOf(textField.getText());
                        if (a < 32768) {
                            modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress), a);
                            modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress) + 1, 0);
                        } else {
                            if (a < 65536) {
                                modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress), a);
                                modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress) + 1, 0);
                            } else {
                                modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress), dataTreat.tenToBinary(a)[0]);
                                modbus.ModbuswriteSingleRegister(1, dataTreat.registerAddressTransform(registerWriteAddress) + 1, dataTreat.tenToBinary(a)[1]);
                            }
                        }
                    }
                    SettingPane.messageLabel.setText(labelName.getText() + " : " + "已设置为" + textField.getText());
                    System.out.println(df.format(new Date()) + " " + labelName.getText() + " : " + "已设置为" + textField.getText());
                } catch (Exception e) {
                    SettingPane.messageLabel.setText(labelName.getText() + " : " + "错误");
                    System.out.println(df.format(new Date()) + " " + labelName.getText() + " : " + "错误");
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

    public Label getLabelName() {
        return labelName;
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

    public Button getButton() {
        return button;
    }
}