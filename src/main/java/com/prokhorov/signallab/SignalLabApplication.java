package com.prokhorov.signallab;

import com.prokhorov.signallab.sound.SoundHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SignalLabApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SignalLabApplication.class, args);
		SoundHandler handler = new SoundHandler();
		handler.play();
	}

}
