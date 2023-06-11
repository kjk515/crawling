package com.jin.crawling.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CrawlingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CrawlingController crawlingController;

    private final String url1 = "https://shop.hyundai.com";
    private final String url2 = "https://www.kia.com";
    private final String url3 = "https://www.genesis.com";

    @Test
    public void testCrawling() throws Exception {
        mvc.perform(get("/crawling"))
                        .andExpect(status().isOk());
    }

    @Test
    public void testCrawlInParallel() {
        List<String> contentList = crawlingController.crawlInParallel(List.of(url1, url2, url3));
    }
}