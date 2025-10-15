package com.example.omok.packet.packets;

import com.example.omok.packet.PacketType;

public class ReadyPacket extends BasicPacket {
    private int isPlayerReady;
    private String userId;
    private int playerColor;

    public ReadyPacket() {
        super(PacketType.PLAYER_READY);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(int playerColor) {
        this.playerColor = playerColor;
    }

    public int getIsPlayerReady() {
        return isPlayerReady;
    }

    public void setIsPlayerReady(int isPlayerReady) {
        this.isPlayerReady = isPlayerReady;
    }
}
