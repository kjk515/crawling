package com.jin.crawling.application;

import com.jin.crawling.infrastructure.CrawlingClientImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TextProcessServicePerformanceTest {

    private TextProcessServiceImpl textProcessService;

    @Autowired
    private CrawlingClientImpl crawlingClient;


    @Before
    public void before() {
        textProcessService = new TextProcessServiceImpl();
    }

    @Test
    public void testPer() {

        String crawlingText = crawlingClient.getHtml("https://shop.hyundai.com");
        System.out.println("crawling1 length: " + crawlingText.length());
    }
}
