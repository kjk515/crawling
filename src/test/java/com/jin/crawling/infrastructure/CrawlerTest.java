package com.jin.crawling.infrastructure;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CrawlerTest {


    @Test
    public void getTest() throws IOException {
        Crawler crawler = new Crawler("https://www.kia.com");

        String crawlingText = crawler.get();
        System.out.println(crawlingText);
    }
}
