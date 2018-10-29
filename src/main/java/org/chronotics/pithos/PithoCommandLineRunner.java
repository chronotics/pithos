package org.chronotics.pithos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PithoCommandLineRunner implements CommandLineRunner {
	private static final Logger logger = 
			LoggerFactory.getLogger(PithoCommandLineRunner.class);
	
	@Override
	public void run(String... arg0) throws Exception {
	}
}

