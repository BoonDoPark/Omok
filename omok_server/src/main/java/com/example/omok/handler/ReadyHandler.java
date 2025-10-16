package com.example.omok.handler;

import com.example.omok.packet.PacketType;
import com.example.omok.packet.packets.ReadyPacket;

public class ReadyHandler implements EventHandler {
    private final ReadyPacket readyPacket;

    public ReadyHandler() {
        this.readyPacket = new ReadyPacket();
    }

    @Override
    public boolean isAcceptable(PacketType packetType) {
        return readyPacket.getPacketType() == packetType;
    }

    @Override
    public void process() {

    }
}
