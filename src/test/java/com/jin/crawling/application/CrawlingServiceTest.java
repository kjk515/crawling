package com.jin.crawling.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class CrawlingServiceTest {

    @Autowired
    private CrawlingService crawlingService;

    private final String url1 = "https://shop.hyundai.com";
    private final String url2 = "https://www.kia.com";
    private final String url3 = "https://www.genesis.com";

    @Test
    public void testGetCrawlingContent() throws ExecutionException, InterruptedException {
        // when
        CompletableFuture<String> crawlingContent1 = crawlingService.getCrawlingContent(url1);
        CompletableFuture<String> crawlingContent2 = crawlingService.getCrawlingContent(url2);
        CompletableFuture<String> crawlingContent3 = crawlingService.getCrawlingContent(url3);

        // then
        String content1 = crawlingContent1.get();
        String content2 = crawlingContent2.get();
        String content3 = crawlingContent3.get();
    }
}
