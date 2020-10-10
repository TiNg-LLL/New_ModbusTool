package com.TiNg.pane;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.datatreat.RegisterReadThread;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;

public class RegisterSinglePane extends AnchorPane{

    Modbus modbus = new Modbus();
    DataTreat dataTreat = new DataTreat();

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

        RegisterReadThread registerReadThread = new RegisterReadThread(registerReadAddress, label);
        //registerReadThread.start();
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
}