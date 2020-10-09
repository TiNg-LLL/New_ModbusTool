package com.TiNg.pane;

import com.TiNg.datatreat.COMDate;
import com.TiNg.datatreat.Modbus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class COMConnect extends AnchorPane {

    ComboBox<String> comboBoxCOM = new ComboBox<String>();
    ComboBox<String> comboBoxBaudrate = new ComboBox<String>();
    ComboBox<String> comboBoxDataBits = new ComboBox<String>();
    ComboBox<String> comboBoxStopBits = new ComboBox<String>();
    ComboBox<String> comboBoxEvenODD = new ComboBox<String>();
    Button button = new Button();

    COMDate comDate = new COMDate();
    Modbus modbus = new Modbus();
    List<String> dataBitsList = new ArrayList<String>();
    List<String> stopBitsList = new ArrayList<String>();
    Properties properties = new Properties();
    FileInputStream inputStream;
    BufferedReader bufferedReader;

    public COMConnect(int width) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlLoader.getClassLoader().getResource("views/COMConnect.fxml"));
        HBox hBox = (HBox)fxmlLoader.load();
        getChildren().add(hBox);
    }


 /*   public COMConnect(int width) {
        try {
            inputStream = new FileInputStream("src/main/resources/address.properties");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            properties.load(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setStyle("-fx-background-color:#d2d2d2");
        setPrefWidth(width);
        setPadding(new Insets(3, 6, 3, 6));  //节点到边缘的距离
        setSpacing(5);  //节点之间的间距

        comDate.flashCOMDate(comboBoxCOM);  //COM端口
        comboBoxCOM.setTooltip(new Tooltip("COM端口选择"));
        comboBoxCOM.setVisibleRowCount(4);
        getChildren().add(comboBoxCOM);

        comboBoxCOM.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                comDate.flashCOMDate(comboBoxCOM);
            }
        });

        comboBoxBaudrate.setTooltip(new Tooltip("波特率选择"));  //波特率选择
        comboBoxBaudrate.setItems(FXCollections.observableArrayList("4800", "9600", "14400", "19200", "38400", "56000", "115200"));
        comboBoxBaudrate.setValue(properties.getProperty("BaudrateDefault"));
        comboBoxBaudrate.setVisibleRowCount(4);
        getChildren().add(comboBoxBaudrate);

        comboBoxDataBits.setTooltip(new Tooltip("比特位选择"));  //比特位选择
        for (int i = 0; i < 15; i++) {
            dataBitsList.add(Integer.toString(i));
        }
        comboBoxDataBits.setItems(FXCollections.observableArrayList(dataBitsList));
        comboBoxDataBits.setValue(properties.getProperty("DataBitsDefault"));
        comboBoxDataBits.setVisibleRowCount(4);
        getChildren().add(comboBoxDataBits);

        comboBoxStopBits.setTooltip(new Tooltip("结束位选择"));  //结束位选择
        for (int i = 0; i < 3; i++) {
            stopBitsList.add(Integer.toString(i));
        }
        comboBoxStopBits.setItems(FXCollections.observableArrayList(stopBitsList));
        comboBoxStopBits.setValue(properties.getProperty("StopBitsDefault"));
        comboBoxStopBits.setVisibleRowCount(4);
        getChildren().add(comboBoxStopBits);

        comboBoxEvenODD.setTooltip(new Tooltip("奇偶校验"));  //奇偶校验
        comboBoxEvenODD.setItems(FXCollections.observableArrayList("奇", "偶", "无"));
        comboBoxEvenODD.setValue(properties.getProperty("EvenODDDefault"));
        getChildren().add(comboBoxEvenODD);

        final Separator separator = new Separator(); //分割符
        separator.setOrientation(Orientation.VERTICAL);
        separator.setValignment(VPos.CENTER);
        getChildren().add(separator);

        button.setText("连接");  //连接按钮
        button.setFont(Font.font("微软雅体"));
        button.setPrefSize(80.0, 5.0);
        button.setStyle("-fx-font-weight:bold;" + "-fx-background-color: #8dd249");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (button.getText().equals("连接")) {
                    String string = comboBoxCOM.getValue();
                    int baudrate = Integer.parseInt(comboBoxBaudrate.getValue());
                    int dataBits = Integer.parseInt(comboBoxDataBits.getValue());
                    int stopBits = Integer.parseInt(comboBoxStopBits.getValue());
                    String evenODD = comboBoxEvenODD.getValue();
                    System.out.println(string);
                    System.out.println(baudrate);
                    System.out.println(dataBits);
                    System.out.println(stopBits);
                    System.out.println(evenODD);
                    try {
                        modbus.ModbusConnect(string, baudrate, dataBits, stopBits, evenODD);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (modbus.ModbusisConnected()) {
                        System.out.println("COM端口已连接");
                        comboBoxCOM.setDisable(true);
                        comboBoxBaudrate.setDisable(true);
                        comboBoxDataBits.setDisable(true);
                        comboBoxStopBits.setDisable(true);
                        comboBoxEvenODD.setDisable(true);
                        button.setText("断开");
                        button.setStyle("-fx-font-weight:bold;" + "-fx-background-color: #d2d2d2");
                        //setStyle("-fx-background-color: linear-gradient(to right,#d2d2d2,#07b800);");
                        setStyle("-fx-background-color:#8dd249");
                    }
                } else {
                    System.out.println("COM端口已断开");
                    comboBoxCOM.setDisable(false);
                    comboBoxBaudrate.setDisable(false);
                    comboBoxDataBits.setDisable(false);
                    comboBoxStopBits.setDisable(false);
                    comboBoxEvenODD.setDisable(false);
                    button.setText("连接");
                    button.setStyle("-fx-font-weight:bold;" + "-fx-background-color: #8dd249");
                    setStyle("-fx-background-color:#d2d2d2");
                }
            }
        });
        getChildren().add(button);
    }*/
}
