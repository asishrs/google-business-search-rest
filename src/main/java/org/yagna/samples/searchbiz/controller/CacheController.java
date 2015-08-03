package org.yagna.samples.searchbiz.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yagna.samples.searchbiz.config.CacheConfiguration;

@RestController
public class CacheController {
	@CacheEvict(value = CacheConfiguration.CACHE_BUSINESS_DATA, allEntries = true)
    @RequestMapping(value = "/clearCache")
    public ResponseEntity<String> clearCache() {
        return new ResponseEntity<String>("Cache Cleared", HttpStatus.OK);
    }
}
