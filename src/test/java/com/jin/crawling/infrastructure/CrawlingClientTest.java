package com.jin.crawling.infrastructure;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CrawlingClientTest {

    CrawlingClient crawlingClient;

    @Test
    public void getTest() throws IOException {
        crawlingClient = new CrawlingClientImpl("https://www.kia.com");

        String crawlingText = crawlingClient.get();
        System.out.println(crawlingText);
    }
}
