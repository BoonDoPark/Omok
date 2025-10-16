package com.example.omok.dispatcher;

import com.example.omok.react.React;

import java.util.concurrent.BlockingQueue;

public class Comsumer implements Runnable {
    private final BlockingQueue<byte[]> eventQueue;
    private final React react;

    public Comsumer(BlockingQueue<byte[]> eventQueue, React react) {
        this.eventQueue = eventQueue;
        this.react = react;
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] byteArray = eventQueue.take();

                react.handleEvents(byteArray);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
