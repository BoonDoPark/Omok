package com.example.omok.react;

import com.example.omok.handler.*;
import com.example.omok.packet.PacketType;
import com.example.omok.packet.packets.BasicPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class React {
    private final Map<Integer, EventHandler> handlerMap = new ConcurrentHashMap<>();

    public void registerHandler(PacketType packetType) {
        switch (packetType) {
            case PLAYER_READY ->
                    handlerMap.put(
                            packetType.getPacketType(),
                            new ReadyHandler()
                    );
            case PLAYER_COORDINATE ->
                    handlerMap.put(
                            packetType.getPacketType(),
                            new CoordinateHandler()
                    );
            case PLAYER_INITIALIZE ->
                    handlerMap.put(
                            packetType.getPacketType(),
                            new InitializationHandler()
                    );
            case PLAYER_EXIT ->
                    handlerMap.put(
                            packetType.getPacketType(),
                            new ExitHandler()
                    );
        }
    }

    public void deregisterHandler(PacketType packetType) {
        handlerMap.remove(packetType.getPacketType());
    }

    public EventHandler handleEvents(byte[] byteArray) {
        PacketType packetType = new BasicPacket().deserialize(byteArray);
        return handlerMap.get(packetType.getPacketType());
    }
}
