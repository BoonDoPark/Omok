package com.example.omok.packet.packets;

import com.example.omok.packet.PacketType;

public class CoordinateErrorPacket extends BasicPacket {
    private int playerColor;

    public CoordinateErrorPacket(PacketType packetType) {
        super(packetType);
    }

    public int getPlayerColor() {
        return playerColor;
    }
}
