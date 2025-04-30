package com.impostoCalc;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImpostoCalcApplication {
	public static void main(String[] args) {
		Dotenv.configure().ignoreIfMissing().load();
		SpringApplication.run(ImpostoCalcApplication.class, args);
	}
}
