package com.jin.crawling.application;

public interface TextProcessService {

    TextProcessService initTextProcessService(String originText);
    TextProcessService sort();
    TextProcessService deduplicate();
    TextProcessService crossEnglishAndNum();
    String buildString();
}
