package com.example.libraryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheInspectService {
    @Autowired
    public CacheManager cacheManager;

    public void printCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            System.out.println(cache.getName());
            System.out.println("Cache: " + Objects.requireNonNull(cache.getNativeCache()).getClass().getName());
        } else {
            System.out.println("No cache found for name: " + cacheName);
        }
    }
}
