package com.example.omok.handler;

import com.example.omok.packet.PacketType;
import com.example.omok.packet.packets.CoordinatePacket;

public class CoordinateHandler implements EventHandler {
    private final CoordinatePacket coordinatePacket;

    public CoordinateHandler() {
        this.coordinatePacket = new CoordinatePacket();
    }

    @Override
    public boolean isAcceptable(PacketType packetType) {
        return coordinatePacket.getPacketType() == packetType;
    }

    @Override
    public void process() {

    }
}
