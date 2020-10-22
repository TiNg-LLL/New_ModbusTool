package com.TiNg.pane.setting;

import com.TiNg.pane.SettingPane;
import com.TiNg.pane.coils.CoilSinglePane;
import com.TiNg.pane.coils.CoilsPane;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SettingCoilsAddressSinglePane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/setting/settingCoilsAddressPane.fxml");
    AnchorPane anchorPane;  //fxml加载

    Label label;
    ChoiceBox choiceBoxWrite;
    TextField textFieldWrite;
    ChoiceBox choiceBoxRead;
    TextField textFieldRead;
    Button buttonWrite;
    Button buttonRead;
    int i;  //序号
    List<CoilSinglePane> list = CoilsPane.list;


    public SettingCoilsAddressSinglePane() {
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        label = (Label) anchorPane.lookup("#SettingLabelCoilName");  //拿到label

        choiceBoxWrite = (ChoiceBox) anchorPane.lookup("#SettingChoiceBoxWriteCoil");

        textFieldWrite = (TextField) anchorPane.lookup("#SettingTextFieldWriteCoil");  //拿到写入地址textField

        choiceBoxRead = (ChoiceBox) anchorPane.lookup("#SettingChoiceBoxReadCoil");

        textFieldRead = (TextField) anchorPane.lookup("#SettingTextFieldReadCoil");  //拿到读取地址textField

        buttonWrite = (Button) anchorPane.lookup("#SettingButtonWriteCoil");  //拿到写入地址button

        buttonRead = (Button) anchorPane.lookup("#SettingButtonReadCoil");  //拿到读取地址button

        choiceBoxWrite.setItems(FXCollections.observableArrayList("M", "X", "Y"));  //拿到写入地址MXY选择choiceBox

        choiceBoxRead.setItems(FXCollections.observableArrayList("M", "X", "Y"));  //拿到读取地址MXY选择choiceBox

        choiceBoxWrite.setOnAction(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                choiceBoxRead.setValue(choiceBoxWrite.getValue().toString());
            }
        });

        buttonWrite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list.get(i).setCoilWriteAddressMXY(choiceBoxWrite.getValue().toString());
                list.get(i).setCoilReadAddressMXY(choiceBoxWrite.getValue().toString());
                list.get(i).setCoilsWriteAddress(Integer.parseInt(textFieldWrite.getText()));
                list.get(i).setCoilsReadAddress(Integer.parseInt(textFieldWrite.getText()));
                textFieldRead.setText(textFieldWrite.getText());
                SettingPane.messageLabel.setText(label.getText() + "-读写地址已设置为" + choiceBoxWrite.getValue() + textFieldWrite.getText());
            }
        });

        buttonRead.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list.get(i).setCoilReadAddressMXY(choiceBoxRead.getValue().toString());
                list.get(i).setCoilsReadAddress(Integer.parseInt(textFieldRead.getText()));
                SettingPane.messageLabel.setText(label.getText() + "-读取地址已设置为" + choiceBoxRead.getValue() + textFieldRead.getText());
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
        textFieldWrite.setText(String.valueOf(list.get(i).getCoilsWriteAddress()));
        textFieldRead.setText(String.valueOf(list.get(i).getCoilsReadAddress()));
        label.setText(list.get(i).getButton().getText());
        choiceBoxWrite.setValue(list.get(i).getCoilWriteAddressMXY());
        choiceBoxRead.setValue(list.get(i).getCoilReadAddressMXY());
    }
}
