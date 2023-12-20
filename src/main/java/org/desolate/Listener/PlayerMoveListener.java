package org.desolate.Listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.utils.TextFormat;
import org.desolate.FileUtil.DataUtil;
import org.desolate.FileUtil.Message;

public class PlayerMoveListener implements Listener {

    DataUtil dataUtil=new DataUtil();

    Message message=new Message();
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player=event.getPlayer();
        String playerName=player.getName();
        int playerX= (int) player.getLocation().getX();
        int playerY= (int) player.getLocation().getY();
        int playerZ= (int) player.getLocation().getZ();
        String playerRole=dataUtil.checkValue(playerName,"职业");
        if (playerRole.equals("僵尸")){
            if (player.getLevel().getBlockLightAt(playerX,playerY,playerZ)>=15){
                player.setOnFire(10);
                player.sendMessage(TextFormat.colorize("&3")+message.ZombieFireMessage);
            }
        }
    }
}
