package com.jin.crawling.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(CrawlingProperties.class)
public class PropertyTest {

    @Autowired
    private CrawlingProperties crawlingProperties;

    @Test
    public void testPropertiesLoad() {
        int timeout = crawlingProperties.timeout();
        assertEquals(timeout, 3000);
    }
}
