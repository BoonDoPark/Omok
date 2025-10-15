package com.example.omok.bot;

import com.example.omok.packet.PacketType;

public interface PacketHandler {
    boolean isAcceptable(PacketType packetType);
    void process();
}
