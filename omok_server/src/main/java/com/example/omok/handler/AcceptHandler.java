package com.example.omok.handler;

import com.example.omok.packet.PacketType;

import java.net.Socket;

public class AcceptHandler implements EventHandler {
    private final Socket socket;

    public AcceptHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean isAcceptable(PacketType packetType) {
        return false;
    }

    @Override
    public void process() {

    }
}
