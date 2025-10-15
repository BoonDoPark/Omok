package com.example.omok.deserialize;

import com.example.omok.bot.ReadyPacket;
import com.example.omok.packet.Packet;
import com.example.omok.packet.PacketType;

import java.nio.charset.StandardCharsets;

public class Deserialization {
    private final int INTEGER_BYTE_SIZE = 4;
    private final int STRING_BYTE_SIZE = 32;

//    public ReadyPacket deserializeReadyPacket(byte[] data) {
//        int offset = 0;
//
//        int packetTypeInt = bytes2Int(data, offset);
//        PacketType packetType = PacketType.fromPacketType(packetTypeInt);
//
//        byte[] playerIdBytes = new byte[STRING_BYTE_SIZE];
//        System.arraycopy(data, offset, playerIdBytes, 0, STRING_BYTE_SIZE);
//        String playerId = new String(playerIdBytes, StandardCharsets.UTF_8).trim();
//
//    }

    public Packet deserializePacket(byte[] data) {
        int offset = 0;

        // 1. packetType (4 바이트)
        int packetTypeInt = bytes2Int(data, offset);
        PacketType packetType = PacketType.fromPacketType(packetTypeInt);
        offset += INTEGER_BYTE_SIZE;

        // 2. isPlayerReady (4 바이트)
        int isPlayerReady = bytes2Int(data, offset);
        offset += INTEGER_BYTE_SIZE;

        // 3. whoWon (4 바이트)
        int whoWon = bytes2Int(data, offset);
        offset += INTEGER_BYTE_SIZE;

        // 4. x (4 바이트)
        int x = bytes2Int(data, offset);
        offset += INTEGER_BYTE_SIZE;

        // 5. y (4 바이트)
        int y = bytes2Int(data, offset);
        offset += INTEGER_BYTE_SIZE;

        // 6. playerColor (4 바이트)
        int playerColor = bytes2Int(data, offset);
        offset += INTEGER_BYTE_SIZE;

        // 7. playerId (32 바이트)
        // 바이트 배열에서 문자열 데이터를 추출합니다.
        // 역직렬화 시에는 정확히 32바이트를 읽어옵니다.
        byte[] playerIdBytes = new byte[STRING_BYTE_SIZE];
        System.arraycopy(data, offset, playerIdBytes, 0, STRING_BYTE_SIZE);

        // UTF-8로 디코딩하고, 뒤에 붙은 null 바이트는 제거합니다.
        String playerId = new String(playerIdBytes, StandardCharsets.UTF_8).trim();

        // 모든 필드를 포함하여 새로운 Packet 객체를 생성합니다.
        return new Packet(packetType, isPlayerReady, whoWon, x, y, playerColor, playerId);
    }

    // 빅엔디안 (Big Endian)
    private int bytes2Int(byte[] byteArray, int offset) {
        if (offset + 4 > byteArray.length) {
            throw new IllegalArgumentException("바이트 배열의 오프셋이 범위를 벗어납니다.");
        }
        return ((byteArray[offset] & 0xFF) << 24) |
                ((byteArray[offset + 1] & 0xFF) << 16) |
                ((byteArray[offset + 2] & 0xFF) << 8) |
                (byteArray[offset + 3] & 0xFF);
    }
}
