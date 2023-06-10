package com.jin.crawling.infrastructure;

import com.jin.crawling.config.CrawlingProperties;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class CrawlingClientImpl implements CrawlingClient {

    private final int timeout;

    public CrawlingClientImpl(CrawlingProperties crawlingProperties) {
        this.timeout = crawlingProperties.timeout();
    }

    @Override
    public String getHtml(String url) throws IOException {
        Connection conn = Jsoup.connect(url).timeout(timeout);
        return conn.get().toString();
    }
}
