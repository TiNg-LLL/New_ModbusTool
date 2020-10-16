package com.TiNg.pane.setting;

import com.TiNg.pane.registers.RegistersPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SettingRegisterAddressPane extends VBox {

    List<SettingRegisterAddressSinglePane> list = new ArrayList<SettingRegisterAddressSinglePane>();  //寄存器读写地址修改功能pane集合
    int i = RegistersPane.registerPaneQuantity;
    SettingRegisterAddressSinglePane settingRegisterAddressSinglePane;

    public SettingRegisterAddressPane() {
        for (int j = 0; j < i; j++) {
            list.add(new SettingRegisterAddressSinglePane());
            list.get(j).setI(j);
            list.get(j).textFieldFlash();
        }

        setSpacing(5);  //设置上下间距
        //setStyle("-fx-background-color: #878787;"+ "-fx-background-radius: 5");

        settingRegisterAddressSinglePane = new SettingRegisterAddressSinglePane(RegistersPane.registerLabelPane);  //寄存器只读地址修改功能pane
        getChildren().addAll(list);
        getChildren().add(settingRegisterAddressSinglePane);
    }

    public void textFieldFlash() {
        for (int j = 0; j < i; j++) {
            list.get(j).textFieldFlash();
        }
    }
}
