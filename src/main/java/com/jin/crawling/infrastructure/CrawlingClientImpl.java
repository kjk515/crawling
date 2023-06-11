package com.jin.crawling.infrastructure;

import com.jin.crawling.application.CrawlingClient;
import com.jin.crawling.config.CrawlingProperties;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Component
public class CrawlingClientImpl implements CrawlingClient {

    private final int timeout;
    private final int maxRetry;
    private final ConcurrentMap<String, Integer> retryCount = new ConcurrentHashMap<>();
    private static final int INIT_WAITING_TIME = 100;


    public CrawlingClientImpl(CrawlingProperties crawlingProperties) {
        this.timeout = crawlingProperties.timeout();
        this.maxRetry = crawlingProperties.maxRetry();
    }

    @Override
    @Cacheable(cacheNames = "crawling", key = "#url")
    public String getHtml(String url) {
        log.info("getHtml " + Thread.currentThread().getName());
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(timeout).get();
        } catch (SocketTimeoutException e) {
            this.handleTimeout(url, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document.toString();
    }

    private void handleTimeout(String url, SocketTimeoutException timeoutException) {
        retryCount.putIfAbsent(url, 0);
        int count = retryCount.get(url);

        if (count < maxRetry) {
            retryCount.put(url, count + 1);
            try {
                Thread.sleep(this.randomBackOffTime(count));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.getHtml(url);
        } else {
            throw new RuntimeException(timeoutException);
        }
    }

    private long randomBackOffTime(int count) {
        return (long) (INIT_WAITING_TIME * Math.pow(2, count) + Math.random() * 500);
    }
}
