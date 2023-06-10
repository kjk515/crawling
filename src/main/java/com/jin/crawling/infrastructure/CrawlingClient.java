package com.jin.crawling.infrastructure;

import java.io.IOException;

public interface CrawlingClient {

    String getHtml(String url) throws IOException;
}
