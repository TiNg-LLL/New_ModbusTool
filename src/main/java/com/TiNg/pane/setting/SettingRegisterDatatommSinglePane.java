package com.TiNg.pane.setting;

import com.TiNg.datatreat.DataTreat;
import com.TiNg.pane.registers.RegisterLabelPane;
import com.TiNg.pane.registers.RegisterSinglePane;
import com.TiNg.pane.registers.RegistersPane;
import com.TiNg.windows.FirstWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SettingRegisterDatatommSinglePane extends AnchorPane {

    FXMLLoader fxmlLoader = new FXMLLoader();
    URL url = fxmlLoader.getClassLoader().getResource("views/setting/settingRegisterDatatommPane.fxml");
    AnchorPane anchorPane;  //fxml加载

    DataTreat dataTreat = FirstWindow.dataTreat;
    Label label;
    CheckBox checkBoxDataRaw;
    CheckBox checkBoxDatatomm;
    int i;  //序号
    List<RegisterSinglePane> list = RegistersPane.list;
    RegisterLabelPane registerLabelPane = RegistersPane.registerLabelPane;  //寄存器只读取功能pane


    public SettingRegisterDatatommSinglePane() {
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        label = (Label) anchorPane.lookup("#SettingLabelRegisterName");  //拿到名字label

        checkBoxDataRaw = (CheckBox) anchorPane.lookup("#CheckBoxDataRaw");  //拿到原始数据的控件

        checkBoxDatatomm = (CheckBox) anchorPane.lookup("#CheckBoxDatatomm");  //拿到转换mm的控件

        checkBoxDataRaw.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                list.get(i).setBooleanRegisterDataToMM(false);
                if (oldValue && !checkBoxDatatomm.isSelected()) {
                    checkBoxDataRaw.setSelected(true);
                }
                if (checkBoxDatatomm.isSelected()) {
                    checkBoxDatatomm.setSelected(false);
                }
            }
        });

        checkBoxDatatomm.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                list.get(i).setBooleanRegisterDataToMM(true);
                if (oldValue && !checkBoxDataRaw.isSelected()) {
                    checkBoxDatatomm.setSelected(true);
                }
                if (checkBoxDataRaw.isSelected()) {
                    checkBoxDataRaw.setSelected(false);
                }
            }
        });
    }

    public SettingRegisterDatatommSinglePane(RegisterLabelPane registerLabelPane) {
        try {
            fxmlLoader.setLocation(url);  //加载fxml文件
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(anchorPane);

        label = (Label) anchorPane.lookup("#SettingLabelRegisterName");  //拿到名字label

        label.setText(registerLabelPane.getLabelName().getText());

        checkBoxDataRaw = (CheckBox) anchorPane.lookup("#CheckBoxDataRaw");  //拿到原始数据的控件

        checkBoxDatatomm = (CheckBox) anchorPane.lookup("#CheckBoxDatatomm");  //拿到转换mm的控件

        if (registerLabelPane.getBooleanRegisterDataToMM()) {
            checkBoxDatatomm.setSelected(true);
        } else {
            checkBoxDataRaw.setSelected(true);
        }

        checkBoxDataRaw.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                registerLabelPane.setBooleanRegisterDataToMM(false);
                if (oldValue && !checkBoxDatatomm.isSelected()) {
                    checkBoxDataRaw.setSelected(true);
                }
                if (checkBoxDatatomm.isSelected()) {
                    checkBoxDatatomm.setSelected(false);
                }
            }
        });

        checkBoxDatatomm.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                registerLabelPane.setBooleanRegisterDataToMM(true);
                if (oldValue && !checkBoxDataRaw.isSelected()) {
                    checkBoxDatatomm.setSelected(true);
                }
                if (checkBoxDataRaw.isSelected()) {
                    checkBoxDataRaw.setSelected(false);
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

    public void dataFlash() {
        label.setText(list.get(i).getLabelName().getText());

        if (list.get(i).getBooleanRegisterDataToMM()) {
            checkBoxDatatomm.setSelected(true);
        } else {
            checkBoxDataRaw.setSelected(true);
        }
    }

    public Label getLabel() {
        return label;
    }
}
