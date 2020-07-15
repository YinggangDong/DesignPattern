package cn.dyg.designpattern.command.headfirst.simple.entity;

/**
 * Light 类是 灯类,命令的接收者
 *
 * @author dongyinggang
 * @date 2020-07-15 16:00
 **/
public class Light {

    /**
     * on 方法是 开灯
     *
     * @param
     * @return void
     * @author dongyinggang
     * @date 2020/7/15 16:07
     */
    public void on() {
        System.out.println("entity is on !");
    }

    /**
     * off 方法是 关灯
     *
     * @param
     * @return void
     * @author dongyinggang
     * @date 2020/7/15 16:08
     */
    public void off() {
        System.out.println("entity is off !");
    }
}
