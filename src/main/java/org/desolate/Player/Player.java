package org.desolate.Player;

import com.alibaba.fastjson.annotation.JSONField;

public class Player{
    @JSONField(name = "玩家昵称")
    private String PlayerName;

    @JSONField(name = "玩家职业契合度")
    private String PlayerFit;

    @JSONField(name = "玩家职业")
    private String PlayerOccupation;

    @JSONField(name = "玩家职业等级")
    private String PlayerLevel;

    @JSONField(name = "玩家职业经验")
    private String PlayerExp;

    @JSONField(name = "更新时间")
    private String UpdateTime;

    //玩家对象
    public Player(String playerName, String playerOccupation, String playerLevel, String playerExp, String playerFit,String updateTime) {
        super();
        this.PlayerName = playerName;
        this.PlayerOccupation = playerOccupation;
        this.PlayerFit = playerFit;
        this.PlayerLevel = playerLevel;
        this.PlayerExp = playerExp;
        this.UpdateTime = updateTime;
    }

    public String getPlayerOccupation() {
        return PlayerOccupation;
    }

    public void setPlayerOccupation(String playerOccupation) {
        PlayerOccupation = playerOccupation;
    }

    public String getPlayerExp() {
        return PlayerExp;
    }

    public void setPlayerExp(String playerExp) {
        PlayerExp = playerExp;
    }

    public String getPlayerLevel() {
        return PlayerLevel;
    }

    public void setPlayerLevel(String playerLevel) {
        PlayerLevel = playerLevel;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public String getPlayerFit() {
        return PlayerFit;
    }

    public void setPlayerFit(String playerFit) {
        PlayerFit = playerFit;
    }

}
