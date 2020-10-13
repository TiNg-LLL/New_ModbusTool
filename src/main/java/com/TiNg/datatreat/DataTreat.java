package com.TiNg.datatreat;

public class DataTreat {
    int coilAddress;

    public int[] tenToBinary(int i) {  //寄存器写入转换
        String s = Integer.toBinaryString(i);
        String s1 = s.substring(s.length() - 16, s.length());
        String s2 = s.substring(0, s.length() - 16);
        int i1 = Integer.valueOf(s1, 2);
        int i2 = Integer.valueOf(s2, 2);
        int[] i3 = {i1, i2};
        return i3;
    }

    public int readtenToBinary(int[] i) {  //寄存器读取转换
        String s = Integer.toBinaryString(i[0]);
        while (s.length() < 16) {
            s = "0" + s;
        }
        String s1 = Integer.toBinaryString(i[1]);
        String s2 = s1 + s;
        int i1 = Integer.valueOf(s2, 2);
        return i1;
    }

/*    public float readtenToBinarytoMM(int[] i, NewJPanel newJPanel) {
        String s = Integer.toBinaryString(i[0]);
        while (s.length() < 16) {
            s = "0" + s;
        }
        String s1 = Integer.toBinaryString(i[1]);
        String s2 = s1 + s;
        int i2 = Integer.valueOf(s2, 2);
        //System.out.println(i2);
        float i4 = (float) i2;
        float i3 = (i4 * 5 / newJPanel.getwulisubi() / newJPanel.getbujinxifen());
        return i3;
    }*/

    public int coilAddressTransform(String MXY, int coilAddress) {
        if (MXY.equals("M")) {
            if (coilAddress < 8000) {
                this.coilAddress = coilAddress;
            } else {
                this.coilAddress = coilAddress + 16576;
            }
        } else if (MXY.equals("Y")) {
            if (coilAddress < 8) {
                coilAddress = coilAddress + 18432;
            } else {
                coilAddress = coilAddress + 18430;
            }
        } else {
            if (coilAddress < 8) {
                coilAddress = coilAddress + 16384;
            } else {
                coilAddress = coilAddress + 16382;
            }
        }

        return coilAddress;
    }
}
