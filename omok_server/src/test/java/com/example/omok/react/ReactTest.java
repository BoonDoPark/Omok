package com.example.omok.react;

import com.example.omok.handler.EventHandler;
import com.example.omok.handler.ReadyHandler;
import com.example.omok.packet.PacketType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReactTest {
    private React react;

    @Mock
    private PacketType packetType;

    @Mock
    private EventHandler eventHandler;

    @BeforeEach
    void setUp() {
        react = new React();
    }

    @Test
    void success_registerHandler() {
        //when
        when(
                packetType.getPacketType()
        )
                .thenReturn(
                        PacketType.PLAYER_READY.getPacketType()
                );
        react.registerHandler(packetType);

        //then
        verify(packetType, times(1)).getPacketType();
    }

    @Test
    void success_deregisterHandler() {
        // when
        when(
                packetType.getPacketType()
        )
                .thenReturn(
                        PacketType.PLAYER_READY.getPacketType()
                );
        react.registerHandler(packetType);
        react.deregisterHandler(packetType);

        //then
        verify(packetType, times(2)).getPacketType();
    }

    @Test
    void success_handleEvents() {
        // given
        PacketType readyPacket = PacketType.PLAYER_READY;

        //when
        eventHandler = react.handleEvents(readyPacket);

        //then
        Assertions.assertNotNull(eventHandler);
        Assertions.assertTrue(eventHandler instanceof ReadyHandler);
    }
}