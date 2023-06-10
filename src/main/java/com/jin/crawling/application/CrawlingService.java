package com.jin.crawling.application;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface CrawlingService {

    CompletableFuture<String> getCrawlingContent(String url) throws IOException, InterruptedException;
}
