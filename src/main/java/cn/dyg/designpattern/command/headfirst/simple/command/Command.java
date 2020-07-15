package cn.dyg.designpattern.command.headfirst.simple.command;

/**
 * Command 接口是 命令接口
 *
 * 所有的命令对象都会实现这个包含的一个方法的命令接口
 *
 * @author dongyinggang
 * @date 2020-07-15 16:02
 **/
public interface Command {
    /**
     * execute 方法是 命令方法,所有的命令对象均实现该接口
     *
     * @param
     * @return void
     * @author dongyinggang
     * @date 2020/7/15 16:03
     */
    void execute();
}
