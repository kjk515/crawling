package com.jin.crawling.application;

import java.io.IOException;

public interface CrawlingClient {

    String getHtml(String url) throws IOException;
}
