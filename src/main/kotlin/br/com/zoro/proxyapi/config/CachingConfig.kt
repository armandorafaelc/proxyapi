package br.com.zoro.proxyapi.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CachingConfig {
    @Bean
    fun cacheManager(): CacheManager? {
        return ConcurrentMapCacheManager("ddd", "banks", "banco")
    }
}