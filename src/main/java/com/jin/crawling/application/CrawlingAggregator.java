package com.jin.crawling.application;

import com.jin.crawling.util.TextProcessor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CrawlingAggregator {

    private final CrawlingService crawlingService;

    public CrawlingAggregator(CrawlingService crawlingService) {
        this.crawlingService = crawlingService;
    }

    public String getCrawlingContent(List<String> urls) {
        List<CompletableFuture<String>> completableFutures = urls.stream().map(this.crawlingService::getCrawlingContent).toList();
        List<String> contents = completableFutures.stream().map(CompletableFuture::join).toList();
        String merged = contents.stream().map(String::valueOf).collect(Collectors.joining());

        return TextProcessor
                .parsedTextProcessor(merged)
                .distinct()
                .sort()
                .crossEnglishAndNum()
                .buildString();
    }
}
