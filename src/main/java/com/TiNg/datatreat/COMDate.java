package com.TiNg.datatreat;

import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class COMDate {
    List<String> list = new ArrayList<String>();
    Properties properties = new Properties();
    FileInputStream inputStream;

    public COMDate() {
        try {
            inputStream = new FileInputStream("src/main/resources/address.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flashCOMDate(ComboBox<String> choiceBox) {
        String string = choiceBox.getValue();
        list.clear();
        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++) {
            list.add(portNames[i].getSystemPortName());
        }
        Collections.sort(list);
        choiceBox.setItems(FXCollections.observableArrayList(list));

        if (string == null) {
            for (int i = 0; i < portNames.length; i++) {  //判断默认COM端口选择的端口值在系统中是否存在 存在则设置
                if (list.get(i).equals(properties.getProperty("COMDefault"))) {
                    choiceBox.setValue(properties.getProperty("COMDefault"));
                }
            }
            if (choiceBox.getValue() == null) {
                choiceBox.setValue(list.get(0));
            }
        } else {
            for (int i = 0; i < portNames.length; i++) {
                if (list.get(i).equals(string)) {
                    choiceBox.setValue(string);
                } else {
                    choiceBox.setValue(list.get(0));
                }
            }
        }

        for (int i = 0; i < portNames.length; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}