package cat.tecnocampus.tinder2122.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8088")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*")
                .exposedHeaders("authorization");

        // Add more mappings...
    }
}