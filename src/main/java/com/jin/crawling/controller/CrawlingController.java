package com.jin.crawling.controller;

import com.jin.crawling.application.CrawlingAggregator;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrawlingController {

    private final CrawlingAggregator crawlingAggregator;

    public CrawlingController(CrawlingAggregator crawlingAggregator) {
        this.crawlingAggregator = crawlingAggregator;
    }

    @GetMapping("/crawling-example")
    public CrawlingResponse crawlingExample() {
        return new CrawlingResponse(Response.SC_OK,
                this.crawlingAggregator.getCrawlingContent(List.of("https://shop.hyundai.com", "https://www.kia.com", "https://www.genesis.com"))
        );
    }
}
