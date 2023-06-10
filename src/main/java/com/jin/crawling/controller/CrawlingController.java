package com.jin.crawling.controller;

import com.jin.crawling.application.CrawlingService;
import com.jin.crawling.application.TextProcessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class CrawlingController {

    private final CrawlingService crawlingService;
    private final TextProcessService textProcessService;

    public CrawlingController(CrawlingService crawlingService, TextProcessService textProcessService) {
        this.crawlingService = crawlingService;
        this.textProcessService = textProcessService;
    }

    @GetMapping("/crawling")
    public CrossedContent crawling() {
        return new CrossedContent();
    }

    public String crawlInParallel() throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<String> crawlingContent = this.crawlingService.getCrawlingContent("https://www.kia.com"); // FIXME
        return crawlingContent.get();
    }
}
