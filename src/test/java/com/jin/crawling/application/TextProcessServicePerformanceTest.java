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
//        crawlingText = crawlingClient.getHtml("https://shop.hyundai.com");
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
        textProcessService.filterEnglishAndNum();
        textProcessService.sortAscending();
        textProcessService.deduplicate();
    }

    public void testTextProcessStream() {
        testBeforeMethod();
        textProcessService
                .filterEnglishAndNumStream()
                .sortAscendingStream()
                .deduplicateStream()
                .buildString();
    }


    @Test
    public void testFilter() {

        int loopCount = 100;
        filterPrepare();

        // Winner
        startTime("filter stream");
        for (int i = 0; i < loopCount; i++) {
            testFilterEnglishAndNumStream();
        }
        endTime();

        startTime("filter");
        for (int i = 0; i < loopCount; i++) {
            testFilterEnglishAndNum();
        }
        endTime();
    }

    public void filterPrepare() {
        testFilterEnglishAndNum();
        testFilterEnglishAndNumStream();
    }

    public void testFilterEnglishAndNum() {
        testBeforeMethod();
        textProcessService.filterEnglishAndNum();
    }

    public void testFilterEnglishAndNumStream() {
        testBeforeMethod();
        textProcessService.filterEnglishAndNumStream().buildString();
    }

    @Test
    public void testDeduplicate() {

        int loopCount = 100;
        deduplicatePrepare();


        // Winner
        startTime("deduplicate stream");
        for (int i = 0; i < loopCount; i++) {
            testDeduplicateStream();
        }
        endTime();

        startTime("deduplicate stream 2");
        for (int i = 0; i < loopCount; i++) {
            testDeduplicateStream2();
        }
        endTime();

        startTime("deduplicate stream 3");
        for (int i = 0; i < loopCount; i++) {
            testDeduplicateStream3();
        }
        endTime();
    }

    public void deduplicatePrepare() {
        startTime("prepare");
        testDeduplicateStream();
        testDeduplicateStream2();
        testDeduplicateStream3();
        endTime();
    }

    public void testDeduplicateStream() {
        testBeforeMethod();
        textProcessService.deduplicateStream().buildString();
    }

    public void testDeduplicateStream2() {
        testBeforeMethod();
        textProcessService.deduplicateStream2().buildString();
    }

    public void testDeduplicateStream3() {
        testBeforeMethod();
        textProcessService.deduplicateStream3().buildString();
    }
}
