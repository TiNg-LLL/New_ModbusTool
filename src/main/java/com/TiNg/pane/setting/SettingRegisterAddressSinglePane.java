package com.TiNg.pane.setting;

import com.TiNg.mainLauncher.MainLauncher;
import com.TiNg.pane.SettingPane;
import com.TiNg.pane.registers.RegisterLabelPane;
import com.TiNg.pane.registers.RegisterSinglePane;
import com.TiNg.pane.registers.RegistersPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SettingRegisterAddressSinglePane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/setting/settingRegisterAddressPane.fxml");
    AnchorPane anchorPane;  //fxml加载

    Label label;
    TextField textFieldWrite;
    TextField textFieldRead;
    Button buttonWrite;
    Button buttonRead;
    int i;  //序号
    List<RegisterSinglePane> list = RegistersPane.list;
    SimpleDateFormat df = MainLauncher.df;


    public SettingRegisterAddressSinglePane() {
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        label = (Label) anchorPane.lookup("#SettingLabelRegisterName");  //拿到label

        textFieldWrite = (TextField) anchorPane.lookup("#SettingTextFieldWriteRegister");  //拿到写入地址textField

        textFieldRead = (TextField) anchorPane.lookup("#SettingTextFieldReadRegister");  //拿到读取地址textField

        buttonWrite = (Button) anchorPane.lookup("#SettingButtonWriteRegister");  //拿到写入地址button

        buttonRead = (Button) anchorPane.lookup("#SettingButtonReadRegister");  //拿到读取地址button

        buttonWrite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list.get(i).setRegisterWriteAddress(Integer.parseInt(textFieldWrite.getText()));
                list.get(i).setRegisterReadAddress(Integer.parseInt(textFieldWrite.getText()));
                textFieldRead.setText(textFieldWrite.getText());
                SettingPane.messageLabel.setText(label.getText() + "-读写地址已设置为" + textFieldWrite.getText());
                System.out.println(df.format(new Date()) + " " + label.getText() + "-读写地址已设置为" + textFieldWrite.getText());
            }
        });

        buttonRead.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list.get(i).setRegisterReadAddress(Integer.parseInt(textFieldRead.getText()));
                SettingPane.messageLabel.setText(label.getText() + "-读取地址已设置为" + textFieldRead.getText());
                System.out.println(df.format(new Date()) + " " + label.getText() + "-读取地址已设置为" + textFieldRead.getText());
            }
        });
    }

    public SettingRegisterAddressSinglePane(RegisterLabelPane registerLabelPane) {  //寄存器只读地址设置构造方法
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        label = (Label) anchorPane.lookup("#SettingLabelRegisterName");  //拿到label

        label.setText(String.valueOf(registerLabelPane.getLabelName().getText()));

        textFieldWrite = (TextField) anchorPane.lookup("#SettingTextFieldWriteRegister");  //拿到写入地址textField

        textFieldRead = (TextField) anchorPane.lookup("#SettingTextFieldReadRegister");  //拿到读取地址textField

        textFieldRead.setText(String.valueOf(registerLabelPane.getRegisterReadAddress()));

        buttonWrite = (Button) anchorPane.lookup("#SettingButtonWriteRegister");  //拿到写入地址button

        buttonRead = (Button) anchorPane.lookup("#SettingButtonReadRegister");  //拿到读取地址button

        buttonWrite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        buttonRead.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registerLabelPane.setRegisterReadAddress(Integer.valueOf(textFieldRead.getText()));
                SettingPane.messageLabel.setText(registerLabelPane.getLabelName().getText() + "-读取地址已设置为" + textFieldRead.getText());
                System.out.println(df.format(new Date()) + " " + registerLabelPane.getLabelName().getText() + "-读取地址已设置为" + textFieldRead.getText());
            }
        });
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public Label getLabel() {
        return label;
    }

    public void textFieldFlash() {
        textFieldWrite.setText(String.valueOf(list.get(i).getRegisterWriteAddress()));
        textFieldRead.setText(String.valueOf(list.get(i).getRegisterReadAddress()));
        label.setText(list.get(i).getLabelName().getText());
    }
}
