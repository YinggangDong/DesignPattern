package cn.dyg.designpattern.command.headfirst.simple;

import cn.dyg.designpattern.command.headfirst.simple.command.impl.GarageDoorOpenCommand;
import cn.dyg.designpattern.command.headfirst.simple.entity.GarageDoor;
import cn.dyg.designpattern.command.headfirst.simple.entity.Light;
import cn.dyg.designpattern.command.headfirst.simple.command.impl.LightOnCommand;
import cn.dyg.designpattern.command.headfirst.simple.entity.SimpleRemoteControl;

/**
 * RemoteControlTest 类是 测试简易遥控器的使用
 * 本类为命令模式的客户 client
 *
 * @author dongyinggang
 * @date 2020-07-15 16:16
 **/
public class RemoteControlTest {
    public static void main(String[] args) {
        //遥控器是调用者invoker,后续会设置其命令对象
        SimpleRemoteControl remote = new SimpleRemoteControl();
        //创建一个灯对象,是命令的接收和执行者receiver
        Light light = new Light();
        //创建一个开灯命令对象command,将接收者传入
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        //把命令传给调用者
        remote.setCommand(lightOnCommand);
        //执行开灯命令对象的execute方法,执行开灯命令
        remote.buttonWasPressed();

        //创建一个车库门对象
        GarageDoor garageDoor = new GarageDoor();
        //创建一个车库门打开命令对象
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
        //设置invoker的命令对象为garageDoorOpenCommand
        remote.setCommand(garageDoorOpenCommand);
        //执行开启车库门命令的execute()方法,执行开启车库门命令
        remote.buttonWasPressed();
    }
}
