package com.TiNg.datatreat;

import com.TiNg.pane.COMConnect;
import com.TiNg.pane.CoilsPane;
import com.TiNg.windows.FirstWindow;

public class CoilDataFlashThread extends Thread {
    CoilsPane coilsPane = FirstWindow.coilsPane;
    Modbus modbus = COMConnect.modbus;
    int i;

    public CoilDataFlashThread(int i) {
        this.i = i;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(5);
            } catch (Exception e) {
            }
            if (modbus.ModbusisConnected()) {
                try {
                    coilsPane.getListBoolean().set(i, coilsPane.getList().get(i).getCoilReadThread().getB1());//线圈状态刷新
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
