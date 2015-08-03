package org.yagna.samples.searchbiz.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import com.google.common.cache.CacheBuilder;

@org.springframework.context.annotation.Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer {

	public final static String CACHE_BUSINESS_DATA = "Business-Data";

	private final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);

	@Bean
	@Override
	public CacheManager cacheManager() {
		log.info("Initializing simple Guava Cache manager.");
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		GuavaCache businessCache = new GuavaCache(CACHE_BUSINESS_DATA, CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES)
				.build());
		cacheManager.setCaches(Arrays.asList(businessCache));
		return cacheManager;
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}

}
