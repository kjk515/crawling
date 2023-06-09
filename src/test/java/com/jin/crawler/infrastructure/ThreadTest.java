package com.jin.crawler.infrastructure;

import com.jin.crawler.config.ThreadTestConfig;
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
        MyThread myThread = new MyThread();
        taskExecutor.execute(myThread);
    }
}
