package cn.dyg.designpattern.command.headfirst.simple.entity;

import cn.dyg.designpattern.command.headfirst.simple.command.Command;

/**
 * SimpleRemoteControl 类是 简易遥控器类
 * <p>
 * 命令模式中的调用者invoker
 *
 * @author dongyinggang
 * @date 2020-07-15 16:12
 **/
public class SimpleRemoteControl {

    /**
     * 命令对象
     */
    private Command slot;

    public SimpleRemoteControl() {

    }

    /**
     * setCommand 方法是 设置命令对象
     *
     * @param command 命令对象
     * @return void
     * @author dongyinggang
     * @date 2020/7/15 16:15
     */
    public void setCommand(Command command) {
        this.slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
