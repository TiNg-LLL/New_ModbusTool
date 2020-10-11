package com.TiNg.datatreat;

import com.TiNg.pane.COMConnect;

public class RegisterReadThread extends Thread {
    Modbus modbus = COMConnect.modbus;
    int registerReadAddress;
    int[] i1;

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            try {
                if (modbus.ModbusisConnected()) {
                    i1 = modbus.ModbusreadHoldingRegisters(1, registerReadAddress, 2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //get、set方法
    public void setRegisterReadAddress(int registerReadAddress) {
        this.registerReadAddress = registerReadAddress;
    }

    public int[] getI1() {
        return i1;
    }


}
