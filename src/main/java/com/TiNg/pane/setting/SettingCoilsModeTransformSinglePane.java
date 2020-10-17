package com.TiNg.pane.setting;

import com.TiNg.pane.coils.CoilSinglePane;
import com.TiNg.pane.coils.CoilsPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SettingCoilsModeTransformSinglePane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/setting/settingCoilsModeTransformPane.fxml");
    AnchorPane anchorPane;  //fxml加载

    Label label;
    RadioButton radioButtonLeft;
    RadioButton radioButtonRight;
    int i;  //序号
    List<CoilSinglePane> list = CoilsPane.list;


    public SettingCoilsModeTransformSinglePane() {
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        label = (Label) anchorPane.lookup("#SettingLabelCoilName");  //拿到label

        radioButtonLeft = (RadioButton) anchorPane.lookup("#SettingRadioButtonLeftCoil");

        radioButtonRight = (RadioButton) anchorPane.lookup("#SettingRadioButtonRrightCoil");

        radioButtonLeft.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                list.get(i).setBooleanCoilModeTransform(false);
                if (oldValue && !radioButtonRight.isSelected()) {
                    radioButtonLeft.setSelected(true);
                }
                if (radioButtonRight.isSelected()) {
                    radioButtonRight.setSelected(false);
                }
            }
        });

        radioButtonRight.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                list.get(i).setBooleanCoilModeTransform(true);
                if (oldValue && !radioButtonLeft.isSelected()) {
                    radioButtonRight.setSelected(true);
                }
                if (radioButtonLeft.isSelected()) {
                    radioButtonLeft.setSelected(false);
                }
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
        label.setText(list.get(i).getButton().getText());
        if (list.get(i).getBooleanCoilModeTransform()) {
            radioButtonRight.setSelected(true);
        } else {
            radioButtonLeft.setSelected(true);
        }
    }
}
