package com.example.omok.packet.packets;

import com.example.omok.packet.PacketType;

public class GameStartPacket extends BasicPacket {

    public GameStartPacket() {
        super(PacketType.SERVER_SEND_GAMESTART);
    }
}
