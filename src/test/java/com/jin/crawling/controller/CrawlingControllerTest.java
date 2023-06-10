package com.jin.crawling.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CrawlingControllerTest {

    @Autowired
    private CrawlingController crawlingController;

    private final String url1 = "https://shop.hyundai.com";
    private final String url2 = "https://www.kia.com";
    private final String url3 = "https://www.genesis.com";

    @Test
    public void testCrawlInParallel() {
        List<String> contentList = crawlingController.crawlInParallel(List.of(url1, url2, url3));
    }
}