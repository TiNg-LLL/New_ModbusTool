package com.TiNg.pane;

import com.TiNg.datatreat.COMDate;
import com.TiNg.datatreat.DataTreat;
import com.TiNg.datatreat.Modbus;
import com.TiNg.mainLauncher.MainLauncher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class COMConnect extends HBox {

    ComboBox<String> comboBoxCOM = new ComboBox<String>();
    ComboBox<String> comboBoxBaudrate = new ComboBox<String>();
    ComboBox<String> comboBoxDataBits = new ComboBox<String>();
    ComboBox<String> comboBoxStopBits = new ComboBox<String>();
    ComboBox<String> comboBoxEvenODD = new ComboBox<String>();
    Button button = new Button();

    COMDate comDate = new COMDate();
    public static Modbus modbus = new Modbus();
    List<String> dataBitsList = new ArrayList<String>();
    List<String> stopBitsList = new ArrayList<String>();
    Properties properties = DataTreat.properties;
    SimpleDateFormat df = MainLauncher.df;

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String nowTime = date.format(formatter);
    String endTime = new String("13-12-2020");
    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
    Boolean b5;


    public COMConnect(int width) {

        setStyle("-fx-background-color:#b3b3b3");
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
        separator.setStyle("-fx-blend-mode: multiply");
        getChildren().add(separator);

        try {
            Date sd1 = df1.parse(endTime);
            Date sd2 = df1.parse(nowTime);
            b5 = sd1.after(sd2);
        } catch (
                ParseException parseException) {
            parseException.printStackTrace();
        }

        button.setText("连接");  //连接按钮
        button.setFont(Font.font("宋体"));
        URL cssURL = this.getClass().getClassLoader().getResource("styles/ButtonStyles.css");  //加载css文件
        this.getStylesheets().add(cssURL.toExternalForm());
        button.setId("connectBtn");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (b5) {
                    if (button.getText().equals("连接")) {
                        String string = comboBoxCOM.getValue();
                        int baudrate = Integer.parseInt(comboBoxBaudrate.getValue());
                        int dataBits = Integer.parseInt(comboBoxDataBits.getValue());
                        int stopBits = Integer.parseInt(comboBoxStopBits.getValue());
                        String evenODD = comboBoxEvenODD.getValue();
                        System.out.println(df.format(new Date()) + " " + "尝试连接");
                        System.out.println("端口：" + string);
                        System.out.println("波特率：" + baudrate);
                        System.out.println("数据位：" + dataBits);
                        System.out.println("结束位：" + stopBits);
                        System.out.println("奇偶校验：" + evenODD);
                        try {
                            modbus.ModbusConnect(string, baudrate, dataBits, stopBits, evenODD);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (modbus.ModbusisConnected()) {
                            System.out.println(df.format(new Date()) + " " + "COM端口已连接");
                            SettingPane.messageLabel.setText("COM端口已连接");
                            comboBoxCOM.setDisable(true);
//                            comboBoxBaudrate.setDisable(true);
//                            comboBoxDataBits.setDisable(true);
//                            comboBoxStopBits.setDisable(true);
//                            comboBoxEvenODD.setDisable(true);
                            button.setText("断开");
                            button.setId("connectBtn1");
                            setStyle("-fx-background-color:#8dd249");
                        }
                    } else {
                        modbus.ModbusDisconnect();
                        System.out.println(df.format(new Date()) + " " + "COM端口已断开");
                        SettingPane.messageLabel.setText("COM端口已断开");
                        comboBoxCOM.setDisable(false);
//                        comboBoxBaudrate.setDisable(false);
//                        comboBoxDataBits.setDisable(false);
//                        comboBoxStopBits.setDisable(false);
//                        comboBoxEvenODD.setDisable(false);
                        button.setText("连接");
                        button.setId("connectBtn");
                        setStyle("-fx-background-color:#b3b3b3");
                    }
                } else {
                    System.out.println(df.format(new Date()) + " " + "已过期");
                    SettingPane.messageLabel.setText("已过期");
                }
            }
        });
        getChildren().add(button);
    }

    public ComboBox<String> getComboBoxBaudrate() {
        return comboBoxBaudrate;
    }

    public ComboBox<String> getComboBoxDataBits() {
        return comboBoxDataBits;
    }

    public ComboBox<String> getComboBoxStopBits() {
        return comboBoxStopBits;
    }

    public ComboBox<String> getComboBoxEvenODD() {
        return comboBoxEvenODD;
    }
}
