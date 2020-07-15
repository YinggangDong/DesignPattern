package cn.dyg.designpattern.command.headfirst.simple.command.impl;

import cn.dyg.designpattern.command.headfirst.simple.command.Command;
import cn.dyg.designpattern.command.headfirst.simple.entity.GarageDoor;

/**
 * GarageDoorOpenCommand 类是
 *
 * @author dongyinggang
 * @date 2020-07-15 16:36
 **/
public class GarageDoorOpenCommand implements Command {

    /**
     * 车库门
     */
    private GarageDoor garageDoor;

    /**
     * GarageDoorOpenCommand 方法是 有参构造方法
     *
     * @param garageDoor 车库门对象
     * @return  车库门命令对象
     * @author dongyinggang
     * @date 2020/7/15 16:43
     */
    public GarageDoorOpenCommand(GarageDoor garageDoor){
        this.garageDoor = garageDoor;
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
        garageDoor.up();
    }
}
