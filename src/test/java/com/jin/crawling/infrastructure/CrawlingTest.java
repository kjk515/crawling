package com.jin.crawling.infrastructure;

import com.jin.crawling.application.TextProcessServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrawlingTest {

    private final String url1 = "https://shop.hyundai.com";
    private final String url2 = "https://www.kia.com";
    private final String url3 = "https://www.genesis.com";


    @Test
    public void test() throws IOException {
        assertEquals("Test", "Test");

        String crawlingText = "";

        CrawlingClient crawler1 = new CrawlingClientImpl();
        crawlingText = crawlingText + crawler1.getHtml(url1);
        System.out.println("crawling1 length: " + crawlingText.length());

        CrawlingClient crawler2 = new CrawlingClientImpl();
        crawlingText = crawlingText + crawler2.getHtml(url2);
        System.out.println("crawling2 length: " + crawlingText.length());

        CrawlingClient crawler3 = new CrawlingClientImpl();
        crawlingText = crawlingText + crawler3.getHtml(url3);
        System.out.println("crawling3 length: " + crawlingText.length());

        TextProcessServiceImpl processor = new TextProcessServiceImpl();
        processor.initTextProcessService(crawlingText);
        String filteredText = processor.filterEnglishAndNum();
        System.out.println("filtered length: " + filteredText.length());

        String deduplicated = processor.deduplicate();
        System.out.println("deduplicated length: " + deduplicated.length());

        String sortedText = processor.sortAscending();
        System.out.println("sorted length: " + sortedText.length());


        System.out.println(sortedText);
    }
}
