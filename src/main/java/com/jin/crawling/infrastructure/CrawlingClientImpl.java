package com.jin.crawling.infrastructure;

import com.jin.crawling.application.CrawlingClient;
import lombok.Getter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@Getter
public class CrawlingClientImpl implements CrawlingClient {

//    private final TaskExecutor taskExecutor; // TODO : thread 생성과 사용로직이 store 계층에 있는것이 맞나?
//
//    public CrawlingClientImpl(TaskExecutor taskExecutor) {
//        this.taskExecutor = taskExecutor;
//    }
//
//    public String getCrawlingContent() {
//        taskExecutor.execute(new MyThread());
//
//        return null;
//    }

    final private int timeout = 3 * 1000;
    final private String url;


    public CrawlingClientImpl(String url) {
        this.url = url;
    }

    public String get() throws IOException {
        Connection conn = Jsoup.connect(this.url).timeout(timeout);
        return conn.get().toString();
    }
}
