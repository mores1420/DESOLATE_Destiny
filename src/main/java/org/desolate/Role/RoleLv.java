package org.desolate.Role;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import org.desolate.FileUtil.DataUtil;
import org.desolate.FileUtil.Message;

import java.io.IOException;

/**
 * 职业等级系统
 * #####
 * 在前面开设服务器的实验中，经验等级多作于一种显示手段来呈现，没有展现出其应有的作用，玩家对于等级经验的感知度也不大
 * 首先对于经验，其起源于RPG作为衡量角色能力的一种标准，而现阶段RPG的模式大多超出等级，等级也只作为很小的一个限制手段--限制武器装备的使用
 * 但是等级经验应该是游戏内角色经历的一种呈现。经验和等级是量变到质变的一种关系，但是切莫不可想象经验和等级之间的转变是一种均匀变化的关系。
 * 搭建经验标准模型：1，升级节奏；2，标准模型；3，总量；4，产出分配
 * 最高等级: Lv500，预期时间：一年
 * 前期玩家多为单体活动，但是服务器的发展为多人为宜
 * 需要保持升级为一种可持续发展的状态：经验设计模板涉及的升级节奏是考虑一个正常游玩玩家的升级走势，而对于低活跃，时不时回游的玩家来说，升级节奏会比预期要慢，相应的，
 * 高活跃玩家的升级节奏会比升级预期要快，个体玩家呈现出来的成长趋势会偏离我们的预期，拉大同时期玩家之间的等级差距，容易流失落后玩家，且不宜于集中同阶段的玩家
 * #
 */

public class RoleLv implements Listener {

    DataUtil dataUtil = new DataUtil();
    Message message = new Message();
    /**
     * 基础杀怪经验值
     */
    double killMonsterExp = message.ExpByKillMonster;

    /**
     * 修正值
     */
    double correctExp = message.CorrectExpByKillMonster;

    int startLevel, targetLevel;

    Player player;

    @EventHandler
    public void onEntityDeath(EntityDamageByEntityEvent event) throws IOException {

        Entity deathEntity = event.getEntity();
        Entity killEntity = event.getDamager();

        if (deathEntity.isAlive() && event.getEntity().getHealth() <= 0) {
            if (killEntity instanceof Player) {
                String PlayerName = ((Player) killEntity).getPlayer().getName();
                String MonsterName = deathEntity.getName();
                int MonsterLevel = Integer.parseInt(getDigit(MonsterName));
                int PlayerLevel = Integer.parseInt(dataUtil.checkValue(PlayerName, "职业等级"));
                double PlayerExp = Double.parseDouble(dataUtil.checkValue(PlayerName, "职业经验值"));
                int num = PlayerLevel - MonsterLevel;

                //如果玩家等级等于怪物等级
                if (num == 0) {
                    double NowExp = PlayerExp + killMonsterExp;
                    dataUtil.setValue(PlayerName, "职业经验值", NowExp);
                    ((Player) killEntity).sendMessage("您因击杀 " + MonsterName + " 获得了 " + NowExp + " 经验值");
                }
                //如果玩家等级高于怪物等级
                else if (num > 0) {
                    double NumExp = killMonsterExp - correctExp * num;
                    if (NumExp <= 0) {
                        ((Player) killEntity).sendMessage("您因击杀 " + MonsterName + " 获得了0经验值");
                    } else {
                        double NowExp = PlayerExp + NumExp;
                        dataUtil.setValue(PlayerName, "职业经验值", NowExp);
                        ((Player) killEntity).sendMessage("您因击杀 " + MonsterName + " 获得了 " + NowExp + " 经验值");
                    }
                }
                //如果怪物等级高于玩家等级
                else {
                    int Num = MonsterLevel - PlayerLevel;
                    double NumExp = killMonsterExp + correctExp * Num;
                    double NowExp = PlayerExp + NumExp;
                    dataUtil.setValue(PlayerName, "职业经验值", NowExp);
                    ((Player) killEntity).sendMessage("您因击杀 " + MonsterName + " 获得了 " + NowExp + " 经验值");
                }
            }
        }
    }


    /**
     * 从字符串中提取数字
     *
     * @param str 输入String字符串
     * @return 字符串中的数字
     */
    private static String getDigit(String str) {
        String result = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    result = result + str.charAt(i) + ",";
                }
            }
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
}
