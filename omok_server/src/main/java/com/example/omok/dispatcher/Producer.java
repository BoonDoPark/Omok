package com.example.omok.dispatcher;

import java.util.concurrent.BlockingQueue;

public class Producer {
    private final BlockingQueue<byte[]> eventQueue;

    public Producer(BlockingQueue<byte[]> eventQueue) {
        this.eventQueue = eventQueue;
    }

    public void produce(byte[] byteArray) {
        try {
            eventQueue.put(byteArray);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
