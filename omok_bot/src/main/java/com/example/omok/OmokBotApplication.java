package com.example.omok;

import com.example.omok.thread.MultiThread;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmokBotApplication implements CommandLineRunner {
	private final MultiThread multiThread;

	public OmokBotApplication(MultiThread multiThread) {
		this.multiThread = multiThread;
	}

	public static void main(String[] args) {
		SpringApplication.run(OmokBotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		multiThread.start(2000);
	}
}
