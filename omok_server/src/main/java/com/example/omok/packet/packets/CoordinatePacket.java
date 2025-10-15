package com.example.omok.packet.packets;

import com.example.omok.packet.PacketType;

public class CoordinatePacket extends BasicPacket {
    private Integer x;
    private Integer y;
    private Integer playerColor;

    public CoordinatePacket() {
        super(PacketType.PLAYER_COORDINATE);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Integer playerColor) {
        this.playerColor = playerColor;
    }
}
