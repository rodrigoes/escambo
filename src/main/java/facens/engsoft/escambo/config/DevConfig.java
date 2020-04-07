package facens.engsoft.escambo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instanciaBancoDeDados() {

        if ("create".equals(strategy)) {
            dbService.instanciaBancoDeDadosParaTestes();
            return true;
        }
        return false;
    }
}
