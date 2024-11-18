package console.infrastructure.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {
    @Bean
    public Cache<String, Object> caffeineCache() {
        return Caffeine.newBuilder()
                       .expireAfterWrite(1, TimeUnit.MINUTES)  // 默認過期時間
                       .maximumSize(100)                               // 最大緩存條目數
                       .build();
    }
}
