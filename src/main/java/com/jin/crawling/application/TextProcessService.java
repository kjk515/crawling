package com.jin.crawling.application;

public interface TextProcessService {

    TextProcessService initTextProcessService(String originText);
    TextProcessService sort();
    TextProcessService distinct();
    TextProcessService crossEnglishAndNum();
    String buildString();
}
