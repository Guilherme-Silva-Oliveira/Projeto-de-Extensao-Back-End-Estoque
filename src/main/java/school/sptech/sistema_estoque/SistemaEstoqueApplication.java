package school.sptech.sistema_estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SistemaEstoqueApplication {
	public static void main(String[] args) {
		SpringApplication.run(SistemaEstoqueApplication.class, args);
	}
}
