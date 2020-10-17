package com.TiNg.datatreat;

import com.TiNg.pane.*;
import com.TiNg.pane.coils.CoilsPane;
import com.TiNg.pane.registers.RegistersPane;
import com.TiNg.windows.FirstWindow;

public class ReadThread extends Thread {
    RegistersPane registersPane = FirstWindow.registersPane;
    CoilsPane coilsPane = FirstWindow.coilsPane;
    Modbus modbus = COMConnect.modbus;
    DataTreat dataTreat = RegistersPane.dataTreat;


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
            if (modbus.ModbusisConnected()) {
                try {
                    for (int i = 0; i < registersPane.getRegisterPaneQuantity(); i++) {
                        registersPane.getListInt().set(i, modbus.ModbusreadHoldingRegisters(1, dataTreat.registerAddressTransform(registersPane.getList().get(i).getRegisterReadAddress()), 2));
                    }

                    registersPane.getRegisterLabelPane().setI(modbus.ModbusreadHoldingRegisters(1, dataTreat.registerAddressTransform(registersPane.getRegisterLabelPane().getRegisterReadAddress()), 2));

                    for (int i = 0; i < coilsPane.getCoilsPaneQuantity(); i++) {
                        coilsPane.getListBoolean().set(i, modbus.ModbusreadCoils(1, dataTreat.coilAddressTransform(coilsPane.getList().get(i).getCoilReadAddressMXY(), coilsPane.getList().get(i).getCoilsReadAddress()), 1));
                    }
                } catch (Exception e) {

                }
            }
        }
    }
}