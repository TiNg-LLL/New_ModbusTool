package com.TiNg.pane.registers;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.pane.COMConnect;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class RegisterLabelPane extends AnchorPane {
    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/registerLabelFXML.fxml");
    AnchorPane anchorPane;  //fxml加载

    int registerReadAddress;
    Label label;
    int[] i;  //读取的值
    Boolean booleanRegisterDataToMM;  //是否转换为mm单位

    public RegisterLabelPane() {
        fxmlLoader.setLocation(url);  //加载fxml文件

        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        label = (Label) anchorPane.lookup("#LabelRegisterValue");  //拿到label
    }


    //get、set方法
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public int getRegisterReadAddress() {
        return registerReadAddress;
    }

    public void setRegisterReadAddress(int registerReadAddress) {
        this.registerReadAddress = registerReadAddress;
    }

    public Label getLabel() {
        return label;
    }

    public int[] getI() {
        return i;
    }

    public void setI(int[] i) {
        this.i = i;
    }

    public Boolean getBooleanRegisterDataToMM() {
        return booleanRegisterDataToMM;
    }

    public void setBooleanRegisterDataToMM(Boolean booleanRegisterDataToMM) {
        this.booleanRegisterDataToMM = booleanRegisterDataToMM;
    }
}
