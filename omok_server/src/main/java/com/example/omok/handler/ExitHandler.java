package com.example.omok.handler;

import com.example.omok.packet.PacketType;
import com.example.omok.packet.packets.ExitPacket;

public class ExitHandler implements EventHandler {
    private final ExitPacket exitPacket;

    public ExitHandler() {
        this.exitPacket = new ExitPacket();
    }

    @Override
    public boolean isAcceptable(PacketType packetType) {
        return exitPacket.getPacketType() == packetType;
    }

    @Override
    public void process() {

    }
}
