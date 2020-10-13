package com.TiNg.pane;

import com.TiNg.datatreat.CoilReadThread;
import com.TiNg.datatreat.Modbus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class CoilSinglePane extends AnchorPane {
    CoilReadThread coilReadThread;  //读取线程

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/coilsPaneFXML.fxml");
    AnchorPane anchorPane;  //fxml加载

    int coilsWriteAddress;
    int coilsReadAddress;
    Modbus modbus = COMConnect.modbus;
    Button button;

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
                    boolean[] b = modbus.ModbusreadCoils(1, coilReadThread.getCoilReadAddress(), 1);
                    if (b[0]) {
                        modbus.ModbuswritefalseMultipleCoils(1, coilReadThread.getCoilReadAddress());
                    } else {
                        modbus.ModbuswritetrueMultipleCoils(1, coilReadThread.getCoilReadAddress());
                    }
                } catch (Exception e1) {

                }
            }
        });

        coilReadThread = new CoilReadThread();
        coilReadThread.start();
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

    public CoilReadThread getCoilReadThread() {
        return coilReadThread;
    }
}