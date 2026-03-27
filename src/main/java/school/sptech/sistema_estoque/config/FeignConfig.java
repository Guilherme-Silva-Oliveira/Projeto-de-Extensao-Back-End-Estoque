package school.sptech.sistema_estoque.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Value("${classapp.token}")
    private String token;

    @Bean
    public RequestInterceptor classAppInterceptor(){
        return requestTemplate -> requestTemplate.header("Authorization", "Bearer " + token);
    }
}
