package com.TiNg.datatreat;

import javafx.scene.control.Label;

public class RegisterReadThread extends Thread {
    Modbus modbus = new Modbus();
    DataTreat dataTreat = new DataTreat();
    int registerReadAddress;
    Label label = new Label();

    public RegisterReadThread(int registerReadAddress, Label label) {
        this.registerReadAddress = registerReadAddress;
        this.label = label;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            try {
                if (modbus.ModbusisConnected()) {
                    int[] i1 = modbus.ModbusreadHoldingRegisters(1, registerReadAddress, 2);
                    label.setText(Integer.toString(dataTreat.readtenToBinary(i1)));
                } else {
                    label.setText("  ");
                }
            } catch (Exception e) {
                label.setText("null");
            }
        }
    }


    //get、set方法
    public void setRegisterReadAddress(int registerReadAddress) {
        this.registerReadAddress = registerReadAddress;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
