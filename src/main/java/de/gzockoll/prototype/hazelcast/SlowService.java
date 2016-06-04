package de.gzockoll.prototype.hazelcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static java.util.concurrent.TimeUnit.SECONDS;

@RestController
@Slf4j
public class SlowService {
    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/doit")
    @Cacheable("doit")
    public String doit() throws InterruptedException {
        SECONDS.sleep(5);
        return Instant.now().toString();
    }

    @Scheduled(fixedRate = 5000)
    public void showCache() {
        log.debug("Hurz {}",cacheManager.getCacheNames());
    }
}
