package com.TiNg.pane.coils;

import com.TiNg.datatreat.Modbus;
import com.TiNg.pane.COMConnect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class CoilSinglePane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/coilsPaneFXML.fxml");
    AnchorPane anchorPane;  //fxml加载

    int coilsWriteAddress;
    int coilsReadAddress;
    Modbus modbus = COMConnect.modbus;
    Button button;
    int i;


    public CoilSinglePane() {
        fxmlLoader.setLocation(url);  //加载fxml文件

        URL cssURL = this.getClass().getClassLoader().getResource("styles/ButtonStyles.css");  //加载css文件
        this.getStylesheets().add(cssURL.toExternalForm());

        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        button = (Button) anchorPane.lookup("#ButtonCoil");  //拿到button

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    boolean[] b = modbus.ModbusreadCoils(1, coilsWriteAddress, 1);
                    if (b[0]) {
                        modbus.ModbuswritefalseMultipleCoils(1, coilsWriteAddress);
                    } else {
                        modbus.ModbuswritetrueMultipleCoils(1, coilsWriteAddress);
                    }
                } catch (Exception e1) {

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
}