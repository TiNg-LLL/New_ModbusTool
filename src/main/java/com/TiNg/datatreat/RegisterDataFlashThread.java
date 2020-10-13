package com.TiNg.datatreat;

import com.TiNg.pane.COMConnect;
import com.TiNg.pane.RegisterPane;
import com.TiNg.windows.FirstWindow;

public class RegisterDataFlashThread extends Thread {
    RegisterPane registerPane = FirstWindow.registerPane;
    Modbus modbus = COMConnect.modbus;
    int i;

    public RegisterDataFlashThread(int i) {
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
                    registerPane.getListInt().set(i, registerPane.getList().get(i).getRegisterReadThread().getI1());  //寄存器数据刷新
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
