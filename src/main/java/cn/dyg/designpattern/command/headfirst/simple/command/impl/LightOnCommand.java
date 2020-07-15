package cn.dyg.designpattern.command.headfirst.simple.command.impl;

import cn.dyg.designpattern.command.headfirst.simple.command.Command;
import cn.dyg.designpattern.command.headfirst.simple.entity.Light;

/**
 * LightOnCommand 类是 开灯命令类
 *
 * @author dongyinggang
 * @date 2020-07-15 16:04
 **/
public class LightOnCommand implements Command {

    /**
     * 灯,接收本对象命令的对象
     */
    private Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    /**
     * execute 方法是 命令方法,所有的命令对象均实现该接口
     *
     * @return void
     * @author dongyinggang
     * @date 2020/7/15 16:03
     */
    @Override
    public void execute() {
        light.on();
    }
}
