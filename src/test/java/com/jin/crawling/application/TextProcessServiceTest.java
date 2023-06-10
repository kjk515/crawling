package com.jin.crawling.application;

import org.junit.jupiter.api.Test;

public class TextProcessServiceTest {

    private TextProcessService textProcessService;

    @Test
    public void testSortAscending() {
        String originText = "html124divABCDefgtaBlelmg1";

        textProcessService = new TextProcessServiceImpl(originText);
        String resultText = textProcessService.sortAscending();

        System.out.println(resultText);
    }

    @Test
    public void testCrossEnglishAndNum() {
//        String originText = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        String originText = "AaBCDdefghilmtv124";

        textProcessService = new TextProcessServiceImpl(originText);
        String resultText = textProcessService.crossEnglishAndNum();

        System.out.println(resultText);
    }
}
