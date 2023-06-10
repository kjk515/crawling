package com.jin.crawling.controller;

import com.jin.crawling.application.CrawlingService;
import com.jin.crawling.application.TextProcessService;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlingController {

    private final CrawlingService crawlingService;
    private final TextProcessService textProcessService;
    private final TaskExecutor taskExecutor;

    public CrawlingController(CrawlingService crawlingService, TextProcessService textProcessService, TaskExecutor taskExecutor) {
        this.crawlingService = crawlingService;
        this.textProcessService = textProcessService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping("/crawling")
    public CrossedContent crawling() {
        return new CrossedContent();
    }
}
