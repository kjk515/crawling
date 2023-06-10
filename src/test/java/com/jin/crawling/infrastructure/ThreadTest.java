package com.jin.crawling.infrastructure;

import com.jin.crawling.application.CrawlingService;
import com.jin.crawling.application.CrawlingServiceImpl;
import com.jin.crawling.config.ThreadTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;

@Import(ThreadTestConfig.class)
public class ThreadTest {

    @Autowired
    private TaskExecutor taskExecutor;

    @Test
    public void testTaskExecutor() {
        CrawlingService myThread = new CrawlingServiceImpl(new CrawlingClientImpl("http://www.kia.com"));
        taskExecutor.execute(myThread);
    }
}
