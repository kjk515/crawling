package com.jin.crawling.infrastructure;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CrawlingClientTest {

    private CrawlingClient crawlingClient;

    @Test
    public void getTest() throws IOException {
        crawlingClient = new CrawlingClientImpl();

        String crawlingText = crawlingClient.getHtml("https://www.kia.com");
        System.out.println(crawlingText);
    }
}
