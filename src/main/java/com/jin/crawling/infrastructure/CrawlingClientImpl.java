package com.jin.crawling.infrastructure;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class CrawlingClientImpl implements CrawlingClient {

    @Value("${crawling.timeout}")
    private int timeout;

    public CrawlingClientImpl() {
    }

    @Override
    public String getHtml(String url) throws IOException {
        Connection conn = Jsoup.connect(url).timeout(timeout);
        return conn.get().toString();
    }
}
