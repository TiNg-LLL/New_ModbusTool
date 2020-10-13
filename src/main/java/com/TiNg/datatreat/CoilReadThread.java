package com.TiNg.datatreat;

import com.TiNg.pane.COMConnect;

public class CoilReadThread extends Thread {
    Modbus modbus = COMConnect.modbus;
    int coilReadAddress;
    boolean[] b1;

    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
            try {
                if (modbus.ModbusisConnected()) {
                    b1 = modbus.ModbusreadCoils(1, coilReadAddress, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //get、set方法


    public void setCoilReadAddress(int coilReadAddress) {
        this.coilReadAddress = coilReadAddress;
    }

    public int getCoilReadAddress() {
        return coilReadAddress;
    }

    public boolean[] getB1() {
        return b1;
    }
}
