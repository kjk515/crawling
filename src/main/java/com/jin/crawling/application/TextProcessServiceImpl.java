package com.jin.crawling.application;

import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class TextProcessServiceImpl {

    private final String originText;

    private String text;


    public TextProcessServiceImpl(String originText) {
        this.originText = originText;
        this.text = originText;
    }

    public String filterEnglishAndNum() {
        this.text = text.replaceAll("[^0-9a-zA-Z]", "");
        return text;
    }

    public String sortAscending() {
        // TODO: 정렬을 오름차순이 아닌, 과제 기준으로 해야 함.
        char[] chars = text.toCharArray();
        Arrays.sort(chars);

        text = new String(chars);
        return text;
    }

    public String deduplicate() {
        // TODO: 조금더 효울적인 중복제거 없을까??
        Set<Character> charSet = text.chars()
                .mapToObj(chr -> (char) chr)
                .collect(Collectors.toSet());

        text = charSet.stream().map(String::valueOf).collect(Collectors.joining());

        return text;
    }
}
