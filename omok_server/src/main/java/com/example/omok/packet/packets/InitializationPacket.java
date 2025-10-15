package com.example.omok.packet.packets;

import com.example.omok.packet.PacketType;

public class InitializationPacket extends BasicPacket {

    public InitializationPacket() {
        super(PacketType.PLAYER_INITIALIZE);
    }
}
