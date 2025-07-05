package org.openapitools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootTest
class OpenApiGeneratorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void listBeans() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
            .filter(name -> name.contains("Audit"))
            .sorted()
            .forEach(System.out::println);
}

}
