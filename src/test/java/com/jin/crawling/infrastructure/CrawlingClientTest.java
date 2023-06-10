package com.jin.crawling.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CrawlingClientTest {

    @InjectMocks
    private CrawlingClientImpl crawlingClient;

    @Test
    public void getTest() throws IOException {
        // given
//        given()

        String crawlingText = crawlingClient.getHtml("https://www.kia.com");
        System.out.println(crawlingText);
    }
}
