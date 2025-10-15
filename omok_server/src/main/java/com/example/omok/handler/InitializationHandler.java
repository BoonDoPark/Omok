package com.example.omok.handler;

import com.example.omok.packet.PacketType;
import com.example.omok.packet.packets.InitializationPacket;

public class InitializationHandler implements EventHandler {
    private final InitializationPacket initializationPacket;

    public InitializationHandler() {
        this.initializationPacket = new InitializationPacket();
    }

    @Override
    public boolean isAcceptable(PacketType packetType) {
        return initializationPacket.getPacketType() == packetType;
    }

    @Override
    public void process() {

    }
}
