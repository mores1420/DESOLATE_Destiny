package org.desolate.FileUtil;

import org.desolate.DestinyMain;

public class Message {
    DestinyMain dest=new DestinyMain();

    public String ZombieFireMessage=dest.config.getString(
            "僵尸着火信息","您着火了！请避免阳光直射！");

    public Double ExpByKillMonster=dest.config.getDouble("击杀怪物所得经验值",5.0);

    public Double CorrectExpByKillMonster=dest.config.getDouble("经验值修正值",3.0);
}
