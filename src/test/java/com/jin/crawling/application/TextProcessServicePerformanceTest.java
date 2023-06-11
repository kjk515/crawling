package com.jin.crawling.application;

import com.jin.crawling.infrastructure.CrawlingClientImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TextProcessServicePerformanceTest {

    private TextProcessServiceImpl textProcessService;

    @Autowired
    private CrawlingClientImpl crawlingClient;

    long currentTime;
    String crawlingText;


    @BeforeEach
    public void before() {
        textProcessService = new TextProcessServiceImpl();
        crawlingText = crawlingClient.getHtml("https://www.kia.com");
        System.out.println("crawling length: " + crawlingText.length() + "=================");
    }

    public void testBeforeMethod() {
        textProcessService = new TextProcessServiceImpl();
        textProcessService.initTextProcessService(crawlingText);
    }

    public void startTime(String name) {
        System.out.println("Start " + name + " ------------");
        currentTime = System.currentTimeMillis();
    }

    public void endTime() {
        System.out.println("End : " + (System.currentTimeMillis() - currentTime));
    }


    @Test
    public void testTextProcess() {
        int loopCount = 1;
        textProcessPrepare();

        startTime("textProcess String");
        for (int i = 0; i < loopCount; i++) {
            testTextProcessString();
        }
        endTime();

        startTime("textProcess Stream");
        for (int i = 0; i < loopCount; i++) {
            testTextProcessStream();
        }
        endTime();
    }

    public void textProcessPrepare() {
        startTime("prepare");
        testTextProcessString();
        testTextProcessStream();
        endTime();
    }

    public void testTextProcessString() {
        testBeforeMethod();
        textProcessService.sort();
        textProcessService.deduplicate();
    }

    public void testTextProcessStream() {
        testBeforeMethod();
        textProcessService
                .sort()
                .deduplicate()
                .buildString();
    }


    @Test
    public void testFilter() {

        int loopCount = 500;
        filterPrepare();

        // 987ms
        startTime("filter");
        for (int i = 0; i < loopCount; i++) {
            filterEnglishAndNum();
        }
        endTime();
    }

    public void filterPrepare() {
        filterEnglishAndNum();
    }

    public void filterEnglishAndNum() {
        testBeforeMethod();
    }

    @Test
    public void testDeduplicate() {

        int loopCount = 2000;
        deduplicatePrepare();


        // Winner, 1731ms
        startTime("deduplicate stream");
        for (int i = 0; i < loopCount; i++) {
            deduplicateStream();
        }
        endTime();
    }

    public void deduplicatePrepare() {
        startTime("prepare");
        deduplicateStream();
        endTime();
    }

    public void deduplicateStream() {
        testBeforeMethod();
        textProcessService.deduplicate().buildString();
    }
}
