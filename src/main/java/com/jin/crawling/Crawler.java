package com.jin.crawling;

import lombok.Getter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

@Getter
public class Crawler {

    final private int timeout = 3 * 1000;
    final private String url;


    public Crawler(String url) {
        this.url = url;
    }

    public String get() throws IOException {
        Connection conn = Jsoup.connect(this.url).timeout(timeout);
        return conn.get().toString();
    }
}
