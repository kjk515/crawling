package com.jin.crawling.application;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class TextProcessServiceTest {

    private TextProcessService textProcessService;


    @Before
    public void before() {
        textProcessService = new TextProcessServiceImpl();
    }


    @Test
    @Parameters({
            "<script>812yeh98qw hed0q   23</script>?~@#$^${}[]안녕하세요!, script812yeh98qwhed0q23script",
    })
    public void testFilterEnglishAndNum(String originText, String expected) {

        textProcessService.initTextProcessService(originText);
        String resultText = textProcessService.filterEnglishAndNum();

        assertEquals(expected, resultText);
    }

    @Test
    @Parameters({
            "html124divABCDefgtaBlelmg1, AaBBCDdeefgghilllmmttv1124",
            "13474, 13447",
            "AcbBa, AaBbc",
            ",",
    })
    public void testSortAscending(String originText, String expected) {

        textProcessService.initTextProcessService(originText);
        String resultText = textProcessService.sortAscending();

        assertEquals(expected, resultText);
    }

    @Test
    @Parameters({
            "html124divABCDefgtaBlelmg1, AaBCdDefghilmtv124",
    })
    public void testDeduplicate(String originText, String expected) {

        textProcessService.initTextProcessService(originText);
        String resultText = textProcessService.deduplicate();

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
    public void crossEnglishAndNum(String originText, String expected) {

        textProcessService.initTextProcessService(originText);
        String resultText = textProcessService.crossEnglishAndNum();

        assertEquals(expected, resultText);
    }
}
