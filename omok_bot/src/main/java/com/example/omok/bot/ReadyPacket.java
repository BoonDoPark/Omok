package com.example.omok.bot;

import com.example.omok.deserialize.Deserialization;
import com.example.omok.packet.PacketType;
import com.example.omok.serialize.Serialization;

public class ReadyPacket implements PacketHandler {
    private String userId;

    public ReadyPacket(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean isAcceptable(PacketType packetType) {
        return packetType == PacketType.READY;
    }

    @Override
    public void process() {
        Deserialization deserialization = new Deserialization();
//        ReadyPacket readyPacket = deserialization.deserializeReadyPacket();
    }
}
