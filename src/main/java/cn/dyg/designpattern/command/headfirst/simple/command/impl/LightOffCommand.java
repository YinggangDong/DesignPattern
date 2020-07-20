package cn.dyg.designpattern.command.headfirst.simple.command.impl;

import cn.dyg.designpattern.command.headfirst.simple.command.Command;
import cn.dyg.designpattern.command.headfirst.simple.entity.Light;

/**
 * LightOffCommand 类是 关灯命令
 *
 * @author dongyinggang
 * @date 2020-07-20 16:39
 **/
public class LightOffCommand implements Command {
    /**
     * 灯对象
     */
    Light light = new Light();

    /**
     * LightOffCommand 方法是 关灯命令有参构造方法
     *
     * @param light 灯对象
     * @return  关灯命令对象
     * @author dongyinggang
     * @date 2020/7/20 16:40
     */
    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
