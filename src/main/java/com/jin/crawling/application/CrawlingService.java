package com.jin.crawling.application;

import java.util.concurrent.CompletableFuture;

public interface CrawlingService {

    CompletableFuture<String> getCrawlingContent(String url);
}
