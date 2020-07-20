package cn.dyg.designpattern.command.headfirst.simple.entity;

import cn.dyg.designpattern.command.headfirst.simple.command.Command;
import cn.dyg.designpattern.command.headfirst.simple.command.impl.NoCommand;

/**
 * RemoteControl 类是 完整的遥控器对象
 *
 * @author dongyinggang
 * @date 2020-07-20 16:19
 **/
public class RemoteControl {
    /**
     * 开命令
     */
    private Command[] onCommands;
    /**
     * 关命令
     */
    private Command[] offCommands;

    /**
     * 无参构造方法
     */
    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        //空命令对象
        Command noCommand = new NoCommand();
        //初始化遥控器的所有按钮为空命令对象
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    /**
     * setCommand 方法是 给一对开、关按钮设置命令
     *
     * @param slot 按钮位置
     * @param onCommand 开命令
     * @param offCommand 关命令
     * @return void
     * @author dongyinggang
     * @date 2020/7/20 16:37
     */
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    /**
     * onButtonWasPushed 方法是 开按钮触发的命令
     *
     * @param slot 按钮位置
     * @return void
     * @author dongyinggang
     * @date 2020/7/20 16:38
     */
    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }

    /**
     * offButtonWasPushed 方法是 关按钮触发的命令
     *
     * @param slot 按钮位置
     * @return void
     * @author dongyinggang
     * @date 2020/7/20 16:38
     */
    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }
}
