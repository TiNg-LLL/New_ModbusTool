package com.TiNg.datatreat;

import java.io.*;
import java.util.Properties;

public class DataTreat {
    int coilAddress;
    int registerAddress;

    public static Properties properties = new Properties();
    FileInputStream fileInputStream;
    BufferedReader bufferedReader;

    public static Properties propertiesAuto = new Properties();
    FileInputStream fileInputStreamAuto;
    BufferedReader bufferedReaderAuto;

    public static Double bujinxifen;
    public static Double wulisubi;
    public static Double luoju;

    public DataTreat() {
        try {
            fileInputStream = new FileInputStream("src/main/resources/address.properties");  //properties文件设置
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            properties.load(bufferedReader);

            fileInputStreamAuto = new FileInputStream("src/main/resources/Autoaddress.properties");  //properties文件设置
            bufferedReaderAuto = new BufferedReader(new InputStreamReader(fileInputStreamAuto, "UTF-8"));
            propertiesAuto.load(bufferedReaderAuto);

            bujinxifen = Double.parseDouble(properties.getProperty("bujinxifen"));
            wulisubi = Double.parseDouble(properties.getProperty("wulisubi"));
            luoju = Double.parseDouble(properties.getProperty("luoju"));
            System.out.println("步进细分" + bujinxifen);
            System.out.println("物理速比" + wulisubi);
            System.out.println("螺距" + luoju);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] tenToBinary(int i) {  //寄存器写入转换
        String s = Integer.toBinaryString(i);
        String s1 = s.substring(s.length() - 16, s.length());
        String s2 = s.substring(0, s.length() - 16);
        int i1 = Integer.valueOf(s1, 2);
        int i2 = Integer.valueOf(s2, 2);
        int[] i3 = {i1, i2};
        return i3;
    }

    public int readTenToBinary(int[] i) {  //寄存器读取转换
        String s = Integer.toBinaryString(i[0]);
        while (s.length() < 16) {
            s = "0" + s;
        }
        String s1 = Integer.toBinaryString(i[1]);
        String s2 = s1 + s;
        int i1 = Integer.valueOf(s2, 2);
        return i1;
    }

    public double registerDataToMM(int[] i) {  //寄存器数据转换为mm单位方法
        String s = Integer.toBinaryString(i[0]);
        while (s.length() < 16) {
            s = "0" + s;
        }
        String s1 = Integer.toBinaryString(i[1]);
        String s2 = s1 + s;
        int i2 = Integer.valueOf(s2, 2);
        //System.out.println(i2);
        double i4 = i2;
        double i3 = (i4 * luoju / wulisubi / bujinxifen);  //速比脉冲数
        i3 = (double) (Math.round(i3 * 100)) / 100;  //保留两位小数
        return i3;
    }

    public int registerAddressTransform(int registerAddress) {  //线圈输入地址转换
        if (registerAddress < 8000) {
            this.registerAddress = registerAddress;
        } else {
            this.registerAddress = registerAddress + 8384;
        }
        return this.registerAddress;
    }

    public int coilAddressTransform(String MXY, int coilAddress) {  //线圈输入地址转换
        if (MXY.equals("M")) {
            if (coilAddress < 8000) {
                this.coilAddress = coilAddress;
            } else {
                this.coilAddress = coilAddress + 16576;
            }
        } else if (MXY.equals("Y")) {
            if (coilAddress < 8) {
                this.coilAddress = coilAddress + 18432;
            } else {
                this.coilAddress = coilAddress + 18430;
            }
        } else {
            if (coilAddress < 8) {
                this.coilAddress = coilAddress + 16384;
            } else {
                this.coilAddress = coilAddress + 16382;
            }
        }

        return this.coilAddress;
    }


}