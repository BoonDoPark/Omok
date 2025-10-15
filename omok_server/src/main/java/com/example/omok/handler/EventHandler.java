package com.example.omok.handler;

import com.example.omok.packet.PacketType;

public interface EventHandler {
    boolean isAcceptable(PacketType packetType);
    void process();
}
