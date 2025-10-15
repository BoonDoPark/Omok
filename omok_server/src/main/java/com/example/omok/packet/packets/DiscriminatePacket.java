package com.example.omok.packet.packets;

import com.example.omok.packet.PacketType;

public class DiscriminatePacket extends BasicPacket {
    private int whoWon;

    public DiscriminatePacket() {
        super(PacketType.SERVER_SEND_DISCRIMINATE);
    }

    public int getWhoWon() {
        return whoWon;
    }

    public void setWhoWon(int whoWon) {
        this.whoWon = whoWon;
    }
}
