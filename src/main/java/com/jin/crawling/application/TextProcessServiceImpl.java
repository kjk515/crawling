package com.jin.crawling.application;

import lombok.Getter;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class TextProcessServiceImpl implements TextProcessService {

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
//        char[] chars = text.toCharArray();

        Character[] chars = text.chars()
                .mapToObj(chr -> (char) chr)
                .toArray(Character[]::new);

        Arrays.sort(chars, (c1, c2) -> {
            char lower1 = Character.toLowerCase(c1);
            char lower2 = Character.toLowerCase(c2);

            if (c1.equals(c2)) {
                return 0;
            }
            else if (lower1 == lower2) {
                return c1 - c2;
            }
            else {
                boolean c1digit = Character.isDigit(c1);
                return lower1 - lower2;
            }
        });

        // TODO: Apache Common Lang 유틸로 변경?
        text = Arrays.stream(chars)
                .map(Object::toString)
                .collect( Collectors.joining() );

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
