package cn.dyg.designpattern.command.headfirst.simple.command.impl;

import cn.dyg.designpattern.command.headfirst.simple.command.Command;
import cn.dyg.designpattern.command.headfirst.simple.entity.Stereo;

/**
 * StereoOnWithCDCommand 类是 开启音响并播放CD的命令类
 * 音响类的启动包括开启音响,设置播放CD，调整音量为11
 *
 * @author dongyinggang
 * @date 2020-07-20 16:49
 **/
public class StereoOnWithCDCommand implements Command {

    /**
     * 音响对象
     */
    private Stereo stereo;

    /**
     * StereoOnWithCDCommand 方法是 有参构造
     *
     * @param stereo 音响对象
     * @return  音响播放CD的命令对象实例
     * @author dongyinggang
     * @date 2020/7/20 17:00
     */
    public StereoOnWithCDCommand(Stereo stereo){
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCd();
        stereo.setVolume(10);
    }
}
