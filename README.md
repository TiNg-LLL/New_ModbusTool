V0.0.1  
1、实现波特率、数据位等连接参数的选择；  
2、实现COM连接功能；  
3、待实现COM连接功能区域的背景颜色渐变功能；

V0.0.2  
1、COM连接功能由程序实现；  
2、寄存器功能外观由fxml文件实现；  
3、重大问题：javaFX不支持在多线程中对组件进行编辑修改，在多线程中报错，暂不能实现实时读取功能；  

V0.0.3  
1、暂时解决javaFX不支持多线程中编辑修改的问题，现通过多线程功能不断读取modbus相应数据，然后在RegisterPane中将数据整合为集合，使用timeline无限循环功能，将集合中的值遍历给每一个Label；  
2、待上PLC测试上述解决方案是否存在问题；

V0.0.4  
1、上述方案已上机测试成功；  
2、实现线圈功能，由fxml文件实现；  
3、现有两种线程，一种线程为Bodbus读取发送线程，一种线程为数据刷新线程，每个pane独立使用各自线程；  
4、因各pane独立线程方式，可能造成独立线程过多，造成modbus发送堵塞，现发现plc改变在0.5s下，会有读取不畅现象，原有程序方案也有此现象；  
5、待改变多线程modbus发送方式，改为单线程所有pane for循环发送方式，测试堵塞效果；  

V0.0.5  
1、已将所有modbus读取功能整合进单一读取线程中，单一读取等待延时大致为18ms，数据越多，灵敏度越低；  
2、实现寄存器是否mm单位转换功能；  
3、实现只读取功能pane，加入当前位置；  
4、待实现线圈点动功能、详细参数修改功能；（功能隐藏可使用setManaged方法）；

V0.0.6  
1、实现线圈点动功能；  
2、正在实现寄存器地址修改功能；

V0.0.7  
1、实现寄存器地址修改功能；  
2、实现寄存器数据单位转换选择功能；  
3、待实现线圈地址修改等功能；  

V0.0.8  
1、实现线圈地址修改功能；  
2、实现线圈点动切换转换功能；  
3、待实现将地址等信息保存本地功能；

V0.0.9  
1、加入窗口图标；  
2、public static DataTreat在FirstWindow下；  
3、public static properties在DataTreat下；

V0.1.0  
1、完成在软件端将地址等信息保存至本地功能，在修改完地址后，保存至本地；  
2、名称信息保存在address.properties内，并没有做软件端保存处理；  
3、地址等信息，没有做从address.properties读取功能，此功能可能会出现使用上的麻烦，将所有地址等信息都只将在软件端做保存；  
4、地址保存本地功能在SettingPane内的按钮实现；  
5、在DataTreat内增加propertiesAuto；  
6、待实现密码权限功能；

V0.1.1  
1、暂时完成密码权限功能；  
2、待完成功能区权限；  

V1.0.0  No.1.0---发布版本  
1、完成功能区权限；  
2、完成操作信息显示；  
3、已实现主要使用功能，20201022 第一版本；  

V1.1.0  No.1.1---发布版本  
1、微调；  
2、各个按钮操作增加信息打印；  

V1.1.1  No.1.2---发布版本  
1、微调打印信息内容；  

V1.1.2  No.1.3---发布版本  
1、将寄存器和线圈的功能数量写入properties中，实现本地修改；  
2、微调Admin打印信息，将不会把正确密码打印出来；  

V1.1.3  No.1.4---发布版本  
1、细化；