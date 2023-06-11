package com.jin.crawling.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CrawlingServiceImpl implements CrawlingService {

    private final CrawlingClient crawlingClient;

    public CrawlingServiceImpl(CrawlingClient crawlingClient) {
        this.crawlingClient = crawlingClient;
    }

    @Override
    @Async
    public CompletableFuture<String> getCrawlingContent(String url) throws IOException {
        log.info(Thread.currentThread().getName());
        return CompletableFuture.completedFuture(this.crawlingClient.getHtml(url));
    }
}
