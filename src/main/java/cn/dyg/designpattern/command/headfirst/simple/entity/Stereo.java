package cn.dyg.designpattern.command.headfirst.simple.entity;

/**
 * Stereo 类是 音响类
 *
 * @author dongyinggang
 * @date 2020-07-20 16:42
 **/
public class Stereo {

    public void on() {
        System.out.println("开启音响");
    }

    public void off() {
        System.out.println("关闭音响");
    }

    public void setCd() {
        System.out.println("播放CD");
    }

    public void setDvd() {
        System.out.println("播放DVD");
    }

    public void setRadio() {
        System.out.println("播放广播");
    }

    public void setVolume(int volume) {
        System.out.println("调整音量为" + volume);
    }
}
