package com.jin.crawling.application;

import com.jin.crawling.infrastructure.CrawlingClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CrawlingService {

    private final CrawlingClient crawlingClient;

    public CrawlingService(CrawlingClient crawlingClient) {
        this.crawlingClient = crawlingClient;
    }

    @Async
    public CompletableFuture<String> getCrawlingContent(String url) {
        log.info("Thread name : ${}", Thread.currentThread().getName());

        return CompletableFuture.completedFuture(this.crawlingClient.getHtml(url));
    }
}
