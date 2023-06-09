package com.jin.crawler.presentation;

import com.jin.crawler.application.CrawlingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlingController {

    private CrawlingService crawlingService;

    public CrawlingController(CrawlingService crawlingService) {
        this.crawlingService = crawlingService;
    }

    @GetMapping("/crawling")
    public CrossedContent crawling() {
        return new CrossedContent();
    }
}
