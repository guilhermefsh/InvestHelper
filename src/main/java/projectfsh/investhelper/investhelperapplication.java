package projectfsh.investhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class investhelperapplication {

	public static void main(String[] args) {
		SpringApplication.run(investhelperapplication.class, args);
	}

}
