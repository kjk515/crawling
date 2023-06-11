package com.jin.crawling.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(CrawlingProperties.class)
//@SpringBootTest(classes = { // configuration이 아닌 일반 빈으로 등록된다고 오류남
//        CrawlingProperties.class,
//})
//@SpringBootTest
public class PropertyTest {

    @Autowired
    private CrawlingProperties crawlingProperties;

    @Test
    public void testPropertiesLoad() {
        int timeout = crawlingProperties.timeout();
        assertEquals(3000, timeout);
    }
}
