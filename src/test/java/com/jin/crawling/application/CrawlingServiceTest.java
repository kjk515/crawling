package com.jin.crawling.application;

import com.jin.crawling.config.AsyncTestConfig;
import com.jin.crawling.infrastructure.CrawlingClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Import(AsyncTestConfig.class)
//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CrawlingServiceTest {

//    @InjectMocks
//    private CrawlingServiceImpl crawlingService;
    @Autowired
    private CrawlingService crawlingService;

//    @Mock
//    private CrawlingClient crawlingClient;

    private final String url1 = "https://shop.hyundai.com";
    private final String url2 = "https://www.kia.com";
    private final String url3 = "https://www.genesis.com";

    @Test
    public void testGetCrawlingContent() throws IOException, ExecutionException, InterruptedException {
        // given
//        given(crawlingClient.getHtml(any())).willReturn("html124divABCDefgtaBlelmg1");

        // when
        CompletableFuture<String> crawlingContent1 = crawlingService.getCrawlingContent(url1);
        CompletableFuture<String> crawlingContent2 = crawlingService.getCrawlingContent(url2);
        CompletableFuture<String> crawlingContent3 = crawlingService.getCrawlingContent(url3);

        // then
        String content1 = crawlingContent1.get();
        String content2 = crawlingContent2.get();
        String content3 = crawlingContent3.get();

        System.out.println(content1);
    }
}
