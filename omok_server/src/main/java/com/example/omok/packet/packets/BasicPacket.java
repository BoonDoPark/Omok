package com.example.omok.packet.packets;

import com.example.omok.packet.PacketType;

public class BasicPacket {
    private final PacketType packetType;

    public BasicPacket(PacketType packetType) {
        this.packetType = packetType;
    }

    public PacketType getPacketType() {
        return packetType;
    }
}
