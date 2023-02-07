package lk.ijse.spring.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@Configuration
@Import(JPAConfig.class)
@ComponentScan(basePackages = "lk.ijse.spring.service")
public class WebRootConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}