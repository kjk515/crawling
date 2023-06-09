package com.jin.crawler.infrastructure;

import com.jin.crawler.application.CrawlingStore;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Repository;

@Repository
public class CrawlingStoreImpl implements CrawlingStore {

    private final TaskExecutor taskExecutor; // TODO : thread 생성과 사용로직이 store 계층에 있는것이 맞나?

    public CrawlingStoreImpl(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public String getCrawlingContent() {
        taskExecutor.execute(new MyThread());

        return null;
    }
}
