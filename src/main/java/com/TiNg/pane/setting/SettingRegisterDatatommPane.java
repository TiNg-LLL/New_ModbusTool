package com.TiNg.pane.setting;

import com.TiNg.pane.registers.RegistersPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SettingRegisterDatatommPane extends VBox {

    List<SettingRegisterDatatommSinglePane> list = new ArrayList<SettingRegisterDatatommSinglePane>();  //寄存器数据单位修改功能pane集合
    int i = RegistersPane.registerPaneQuantity;

    SettingRegisterDatatommSinglePane settingRegisterDatatommSinglePane;

    public SettingRegisterDatatommPane() {
        for (int j = 0; j < i; j++) {
            list.add(new SettingRegisterDatatommSinglePane());
            list.get(j).setI(j);
            list.get(j).dataFlash();
        }

        settingRegisterDatatommSinglePane = new SettingRegisterDatatommSinglePane(RegistersPane.registerLabelPane);

        setSpacing(5);  //设置上下间距
        //setStyle("-fx-background-color: #878787;"+ "-fx-background-radius: 5");

        getChildren().addAll(list);
        getChildren().add(settingRegisterDatatommSinglePane);
    }

    public void dataFlash() {
        for (int j = 0; j < i; j++) {
            list.get(j).dataFlash();
        }
    }
}
