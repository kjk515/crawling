package com.jin.crawling.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlingClientTest {

    @Autowired
    private CrawlingClientImpl crawlingClient;

    private static final String URL1 = "https://shop.hyundai.com";
    private static final String URL2 = "https://www.kia.com";
    private static final String URL3 = "https://www.genesis.com";

    @Test
    public void getHtmlTest() {
        String crawlingText = crawlingClient.getHtml(URL1);
        assertNotNull(crawlingText);
    }

    @Test
    public void cacheTest() {
        crawlingClient.getHtml(URL1);
        crawlingClient.getHtml(URL1);
    }

    @Test
    public void retryTimeTest() {
        assertEquals(100 * Math.pow(2, 0), 100);
        assertEquals(100 * Math.pow(2, 1), 200);
        assertEquals(100 * Math.pow(2, 2), 400);
    }
}
