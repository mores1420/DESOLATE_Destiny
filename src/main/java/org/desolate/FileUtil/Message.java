package org.desolate.FileUtil;

import org.desolate.DestinyMain;

public class Message {
    DestinyMain dest=new DestinyMain();

    public String ZombieFireMessage=dest.config.getString(
            "僵尸着火信息","您着火了！请避免阳光直射！");

}
