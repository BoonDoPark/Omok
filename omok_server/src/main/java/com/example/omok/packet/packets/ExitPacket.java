package com.example.omok.packet.packets;

import com.example.omok.packet.PacketType;

public class ExitPacket extends BasicPacket {

    public ExitPacket() {
        super(PacketType.PLAYER_EXIT);
    }
}
