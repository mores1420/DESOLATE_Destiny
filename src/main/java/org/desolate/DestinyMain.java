package org.desolate;

import cn.nukkit.plugin.PluginBase;
import org.desolate.Listener.HelloListener;

public class DestinyMain extends PluginBase {

    String message;

    //插件加载
    @Override
    public void onEnable() {
        this.initConfig();
        this.loadConfig();
        //注册监听器
        this.getServer().getPluginManager().registerEvents(new HelloListener(), this);
        this.getLogger().info("PLUGIN IS RUNNING");
    }

    //初始化配置文件
    public void initConfig() {
        this.getDataFolder().mkdirs();
        this.saveResource("config.yml");
    }

    //读取配置文件
    public void loadConfig() {
        this.reloadConfig();
        this.message=this.getConfig().getString("message");
    }

}