package com.TiNg.pane;

import com.TiNg.datatreat.COMDate;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class COMConnect extends HBox {
    Button button = new Button("端口刷新");
    ChoiceBox<String> choiceBox = new ChoiceBox<String>();
    COMDate comDate = new COMDate();

    public COMConnect(int width) {

        setStyle("-fx-background-color:#7aff4d");
        setPrefWidth(width);
        setPadding(new Insets(6, 12, 6, 12));  //节点到边缘的距离
        setSpacing(10);  //节点之间的间距

        comDate.flashCOMDate(choiceBox);

        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                comDate.flashCOMDate(choiceBox);
            }
        });
        getChildren().add(choiceBox);

        button.setFont(Font.font("宋体"));
        getChildren().add(button);
    }
}
