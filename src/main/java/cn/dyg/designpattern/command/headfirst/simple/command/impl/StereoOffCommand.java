package cn.dyg.designpattern.command.headfirst.simple.command.impl;

import cn.dyg.designpattern.command.headfirst.simple.command.Command;
import cn.dyg.designpattern.command.headfirst.simple.entity.Stereo;

/**
 * StereoOffCommand 类是
 *
 * @author dongyinggang
 * @date 2020-07-20 16:58
 **/
public class StereoOffCommand implements Command {

    private Stereo stereo;

    public StereoOffCommand(Stereo stereo){
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }
}
