package com.example.omok.thread;

import com.example.omok.bot.OmokBot;
import org.springframework.stereotype.Component;

@Component
public class MultiThread {
    private final OmokBot omokBot;

    public MultiThread(OmokBot omokBot) {
        this.omokBot = omokBot;
    }

    public void start(int threads) throws InterruptedException {
        int interval = 1000;
        for(int i=0; i<threads; i++) {
            new Thread(new OmokBot()).start();
            System.out.println("omockBot-"+i);
            Thread.sleep(interval);
        }
    }
}
