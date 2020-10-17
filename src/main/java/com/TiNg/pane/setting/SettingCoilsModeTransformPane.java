package com.TiNg.pane.setting;

import com.TiNg.pane.coils.CoilsPane;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SettingCoilsModeTransformPane extends VBox {

    List<SettingCoilsModeTransformSinglePane> list = new ArrayList<SettingCoilsModeTransformSinglePane>();  //寄存器读写地址修改功能pane集合
    int i = CoilsPane.coilsPaneQuantity;

    public SettingCoilsModeTransformPane() {
        for (int j = 0; j < i; j++) {
            list.add(new SettingCoilsModeTransformSinglePane());
            list.get(j).setI(j);
            list.get(j).textFieldFlash();
        }

        setSpacing(5);  //设置上下间距
        setPadding(new Insets(10,20,10,20));
        //setStyle("-fx-background-color: #878787;"+ "-fx-background-radius: 5");

        getChildren().addAll(list);
    }

    public void textFieldFlash() {
        for (int j = 0; j < i; j++) {
            list.get(j).textFieldFlash();
        }
    }
}
