package com.jin.crawling.application;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class TextProcessServiceTest {

    private TextProcessServiceImpl textProcessService;


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
            "html124divABCDefgtaBlelmg1, html124divABCDefga",
    })
    public void testDeduplicateStream(String originText, String expected) {

        textProcessService.initTextProcessService(originText);
        String resultText = textProcessService.deduplicateStream().buildString();

        assertEquals(expected, resultText);
    }

    @Test
    @Parameters({
            "html124divABCDefgtaBlelmg1, html124divABCDefga",
    })
    public void testDeduplicateStream3(String originText, String expected) {

        textProcessService.initTextProcessService(originText);
        String resultText = textProcessService.deduplicateStream3().buildString();

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
    public void testCrossEnglishAndNum(String originText, String expected) {

        textProcessService.initTextProcessService(originText);
        String resultText = textProcessService.crossEnglishAndNum();

        assertEquals(expected, resultText);
    }

    @Test
    @Parameters({
            "<script>812yeh98qw hed0q   23</script>?~@#$^${}[]안녕하세요!, c0d1e2h3i8p9qrstwy"
    })
    public void testTextProcess(String originText, String expected) {

        textProcessService.initTextProcessService(originText);

        textProcessService.filterEnglishAndNum();
        textProcessService.deduplicate();
        textProcessService.sortAscending();
        String resultText = textProcessService.crossEnglishAndNum();

        assertEquals(expected, resultText);
    }
}
