package com.jin.crawling.application;

import com.jin.crawling.infrastructure.CrawlingClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class CrawlingServiceImpl implements CrawlingService {

    private final CrawlingClient crawlingClient;

    public CrawlingServiceImpl(CrawlingClient crawlingClient) {
        this.crawlingClient = crawlingClient;
    }

    @Override
    @Async
    public CompletableFuture<String> getCrawlingContent(String url) throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);
        return CompletableFuture.completedFuture(this.crawlingClient.getHtml(url));
    }
}
