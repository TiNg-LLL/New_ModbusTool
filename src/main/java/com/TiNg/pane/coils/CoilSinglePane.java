package com.TiNg.pane.coils;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.pane.COMConnect;
import com.TiNg.pane.registers.RegistersPane;
import com.TiNg.windows.FirstWindow;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class CoilSinglePane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/coilsPaneFXML.fxml");
    AnchorPane anchorPane;  //fxml加载

    int coilsWriteAddress;
    int coilsReadAddress;
    String coilWriteAddressMXY;
    String coilReadAddressMXY;
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = FirstWindow.dataTreat;
    Button button;
    int i;  //序号
    Boolean booleanCoilModeTransform;
    boolean[] b;  //临时读取寄存


    public CoilSinglePane() {
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL cssURL = this.getClass().getClassLoader().getResource("styles/ButtonStyles.css");  //加载css文件
        this.getStylesheets().add(cssURL.toExternalForm());

        getChildren().add(anchorPane);

        button = (Button) anchorPane.lookup("#ButtonCoil");  //拿到button

        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {

                    if (!booleanCoilModeTransform) {
                        b = modbus.ModbusreadCoils(1, dataTreat.coilAddressTransform(coilWriteAddressMXY,coilsWriteAddress), 1);
                        if (b[0]) {
                            modbus.ModbuswritefalseMultipleCoils(1, dataTreat.coilAddressTransform(coilWriteAddressMXY,coilsWriteAddress));
                        } else {
                            modbus.ModbuswritetrueMultipleCoils(1, dataTreat.coilAddressTransform(coilWriteAddressMXY,coilsWriteAddress));
                        }
                    }
                } catch (Exception e) {

                }
            }
        });

        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if (!booleanCoilModeTransform) {
                        if (b[0]) {
                            modbus.ModbuswritetrueMultipleCoils(1, dataTreat.coilAddressTransform(coilWriteAddressMXY,coilsWriteAddress));
                        } else {
                            modbus.ModbuswritefalseMultipleCoils(1, dataTreat.coilAddressTransform(coilWriteAddressMXY,coilsWriteAddress));
                        }
                    } else {
                        b = modbus.ModbusreadCoils(1, dataTreat.coilAddressTransform(coilWriteAddressMXY,coilsWriteAddress), 1);
                        if (b[0]) {
                            modbus.ModbuswritefalseMultipleCoils(1, dataTreat.coilAddressTransform(coilWriteAddressMXY,coilsWriteAddress));
                        } else {
                            modbus.ModbuswritetrueMultipleCoils(1, dataTreat.coilAddressTransform(coilWriteAddressMXY,coilsWriteAddress));
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
    }


    //get、set方法
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public int getCoilsWriteAddress() {
        return coilsWriteAddress;
    }

    public int getCoilsReadAddress() {
        return coilsReadAddress;
    }

    public void setCoilsWriteAddress(int coilsWriteAddress) {
        this.coilsWriteAddress = coilsWriteAddress;
    }

    public void setCoilsReadAddress(int coilsReadAddress) {
        this.coilsReadAddress = coilsReadAddress;
    }

    public Button getButton() {
        return button;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Boolean getBooleanCoilModeTransform() {
        return booleanCoilModeTransform;
    }

    public void setBooleanCoilModeTransform(Boolean booleanCoilModeTransform) {
        this.booleanCoilModeTransform = booleanCoilModeTransform;
    }

    public String getCoilWriteAddressMXY() {
        return coilWriteAddressMXY;
    }

    public String getCoilReadAddressMXY() {
        return coilReadAddressMXY;
    }

    public void setCoilWriteAddressMXY(String coilWriteAddressMXY) {
        this.coilWriteAddressMXY = coilWriteAddressMXY;
    }

    public void setCoilReadAddressMXY(String coilReadAddressMXY) {
        this.coilReadAddressMXY = coilReadAddressMXY;
    }
}