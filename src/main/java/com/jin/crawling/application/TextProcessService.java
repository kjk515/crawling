package com.jin.crawling.application;

public interface TextProcessService {

    void initTextProcessService(String originText);
    String filterEnglishAndNum();
    String sortAscending();
    String deduplicate();
    String crossEnglishAndNum();
}
