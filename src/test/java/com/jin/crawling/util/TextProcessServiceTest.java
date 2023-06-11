package com.jin.crawling.util;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(JUnitParamsRunner.class)
public class TextProcessServiceTest {

    @Test
    @Parameters({
            "<script>812yeh98qw hed0q   23</script>?~@#$^${}[]안녕하세요!, script812yeh98qwhed0q23script",
    })
    public void testInitAndFilter(String text, String expected) {

        String resultText = TextProcessor
                .parsedTextProcessor(text)
                .buildString();

        assertEquals(expected, resultText);
    }

    @Test
    @Parameters({
            "html124divABCDefgtaBlelmg1, AaBBCDdeefgghilllmmttv1124",
            "13474, 13447",
            "AcbBa, AaBbc",
            ",",
    })
    public void testSortAscending(String text, String expected) {

        String resultText = TextProcessor
                .parsedTextProcessor(text).sort().buildString();

        assertEquals(expected, resultText);
    }

    @Test
    @Parameters({
            "html124divABCDefgtaBlelmg1, html124divABCDefga",
    })
    public void testDeduplicate(String text, String expected) {

        String resultText = TextProcessor
                .parsedTextProcessor(text).distinct().buildString();

        assertEquals(expected, resultText);
    }

    @Test
    @Parameters({
            "AaBCDdefghIilmtv124, Aa1B2C4DdefghIilmtv",
            "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789, Aa0Bb1Cc2Dd3Ee4Ff5Gg6Hh7Ii8Jj9KkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz",
            "AaBCdet, AaBCdet",
            "1358, 1358",
            ",",
    })
    public void testCrossEnglishAndNum(String text, String expected) {

        String resultText = TextProcessor
                .parsedTextProcessor(text).crossEnglishAndNum().buildString();

        assertEquals(expected, resultText);
    }

    @Test
    @Parameters({
            "<script>812yeh98qw hed0q   23</script>?~@#$^${}[]안녕하세요!, c0d1e2h3i8p9qrstwy"
    })
    public void testTextProcess(String text, String expected) {

        String resultText = TextProcessor
                .parsedTextProcessor(text)
                .sort()
                .distinct()
                .crossEnglishAndNum()
                .buildString();

        assertEquals(expected, resultText);
    }
}
