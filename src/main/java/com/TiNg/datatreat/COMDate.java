package com.TiNg.datatreat;

import com.fazecast.jSerialComm.SerialPort;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

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

    public void flashCOMDate(ChoiceBox<String> choiceBox) {


        String string = choiceBox.getValue();
        list.clear();
        SerialPort[] portNames = SerialPort.getCommPorts();
        for (int i = 0; i < portNames.length; i++) {
            list.add(portNames[i].getSystemPortName());
        }
        Collections.sort(list);
        for (int i = 0; i < portNames.length; i++) {
            choiceBox.setItems(FXCollections.observableArrayList(list));
            System.out.println(list.get(i));
        }
        if (string == null) {
            choiceBox.setValue(properties.getProperty("COMDefault"));
        } else {
            choiceBox.setValue(string);
        }
    }
}