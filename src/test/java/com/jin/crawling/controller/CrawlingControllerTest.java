package com.jin.crawling.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CrawlingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CrawlingController crawlingController;

    private static final String URL1 = "https://shop.hyundai.com";
    private static final String URL2 = "https://www.kia.com";
    private static final String URL3 = "https://www.genesis.com";

    @Test
    public void testCrawling() throws Exception {
        mvc.perform(get("/crawling-example"))
                        .andExpect(status().isOk());
    }
}