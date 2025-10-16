package com.example.omok.packet.packets;

import com.example.omok.deserialize.Deserialization;
import com.example.omok.packet.Packet;
import com.example.omok.packet.PacketType;
import com.example.omok.serialize.Serialization;

public class BasicPacket {
    private PacketType packetType;
    private final Serialization serialization;
    private final Deserialization deserialization;

    public BasicPacket(PacketType packetType, Deserialization deserialization) {
        this.packetType = packetType;
        this.deserialization = deserialization;
        this.serialization = new Serialization();

    }

    public BasicPacket() {
        this.serialization = new Serialization();
        this.deserialization = new Deserialization();
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public byte[] serialize() {
        return serialization.int2Bytes(packetType.getPacketType());
    }

    public PacketType deserialize(byte[] byteArray) {
        return PacketType.fromPacketType(deserialization.bytes2Int(byteArray, 0));
    }
}
