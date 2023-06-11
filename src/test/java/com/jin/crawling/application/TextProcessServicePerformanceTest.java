package com.jin.crawling.application;

import com.jin.crawling.infrastructure.CrawlingClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextProcessServicePerformanceTest {

    private TextProcessServiceImpl textProcessService;

    @Autowired
    private CrawlingClientImpl crawlingClient;

    long currentTime;
    String crawlingText;


    @Before
    public void before() {
        textProcessService = new TextProcessServiceImpl();
        crawlingText = crawlingClient.getHtml("https://www.kia.com");
        System.out.println("crawling length: " + crawlingText.length() + "=================");
    }

    public void beforeMethod() {
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
        int loopCount = 100;
        textProcessPrepare();

        startTime("textProcess String");
        for (int i = 0; i < loopCount; i++) {
            textProcess();
        }
        endTime();
    }

    public void textProcessPrepare() {
        startTime("prepare");
        textProcess();
        endTime();
    }

    public void textProcess() {
        beforeMethod();
        textProcessService
                .sort()
                .distinct()
                .buildString();
    }


    @Test
    public void testFilter() {

        int loopCount = 500;
        filterPrepare();

        // 987ms
        startTime("filter");
        for (int i = 0; i < loopCount; i++) {
            filter();
        }
        endTime();
    }

    public void filterPrepare() {
        filter();
    }

    public void filter() {
        beforeMethod();
    }

    @Test
    public void testDistinct() {

        int loopCount = 2000;
        distinctPrepare();

        startTime("deduplicate stream");
        for (int i = 0; i < loopCount; i++) {
            distinct();
        }
        endTime();
    }

    public void distinctPrepare() {
        startTime("prepare");
        distinct();
        endTime();
    }

    public void distinct() {
        beforeMethod();
        textProcessService.distinct().buildString();
    }
}
