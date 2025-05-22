package cl.duoc.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.config.annotation.EnableWs;

@SpringBootApplication
@ComponentScan(basePackages = "cl.duoc.ws")
@EnableWs
public class SoapexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapexampleApplication.class, args);
	}

}
