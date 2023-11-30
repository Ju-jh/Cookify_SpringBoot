package Cook.Cookify_SpringBoot.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000");  // 프론트엔드 도메인을 여기에 추가
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

