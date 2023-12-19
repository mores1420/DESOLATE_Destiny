package org.desolate.Role.Task;

import cn.nukkit.Player;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.utils.TextFormat;
import org.desolate.DestinyMain;
import org.desolate.FileUtil.DataUtil;
import org.desolate.FileUtil.Message;

public class InSunCheckTask extends PluginTask<DestinyMain> {

    private Player player;

    DataUtil dataUtil=new DataUtil();

    Message message=new Message();

    public InSunCheckTask(DestinyMain plugin, Player player) {
        super(plugin);
        this.player = player;
    }

    @Override
    public void onRun(int currentTick) {
        for(Player p : DestinyMain.getInstance().getServer().getOnlinePlayers().values()) {
            int pX= (int) p.getLocation().getX();
            int pZ= (int) p.getLocation().getZ();
            int pY= (int) p.getLocation().getY();
            String name = p.getName();
            String playerRoleName=dataUtil.checkValue(name,"职业");
            if (playerRoleName.equals("僵尸")){
                if (p.getLevel().getBlockLightAt(pX,pY,pZ)>=15){
                    p.setOnFire(10);
                    p.sendMessage(TextFormat.colorize("&3")+message.ZombieFireMessage);
                }
            }
        }
    }
}
