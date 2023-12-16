package org.desolate.Listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;

public class HelloListener implements Listener {

//    ignoreCancelled = true - 意思是如果这个事件被别的插件取消了，那 Nukkit 就不会调用我们的插件的事件监听器的这个方法。
//    priority = EventPriority.NORMAL - 这个决定你的事件监听器的优先级。低优先级的监听器(LOW, LOWEST)会被首先调用，
//    然后高优先级的(HIGH, HIGHER, MONITOR)会在随后调用. 在99%的情况下用普通优先级(NORMAL)就够了.
//    消息带颜色-使用 TextFormat 工具类中的 colorize 函数-
//    示例-getPlayer().sendMessage(TextFormat.colorize("&3[NEWS] &b"+FirstPlugin.getPlugin().helloMessage));
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerJoin (PlayerJoinEvent event){

    }
}
