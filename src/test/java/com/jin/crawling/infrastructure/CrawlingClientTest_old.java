package com.jin.crawling.infrastructure;

import com.jin.crawling.config.CrawlingProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CrawlingClientTest_old {

    @InjectMocks
    private CrawlingClientImpl crawlingClient;

    @Mock
    private CrawlingProperties crawlingProperties;

    @Test
    public void getTest() throws IOException {
        // given
//        given()

        String crawlingText = crawlingClient.getHtml("https://www.kia.com");
        System.out.println(crawlingText);
    }

    @Test
    public void retryTimeTest() {
        assertEquals(100 * Math.pow(2, 0), 100);
        assertEquals(100 * Math.pow(2, 1), 200);
        assertEquals(100 * Math.pow(2, 2), 400);
    }
}
