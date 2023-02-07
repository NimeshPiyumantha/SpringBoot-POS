package lk.ijse.spring.config;

import lk.ijse.spring.advisor.AppWideExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {AppWideExceptionHandler.class}, basePackages = "lk.ijse.spring.controller")
public class WebAppConfig {
}
