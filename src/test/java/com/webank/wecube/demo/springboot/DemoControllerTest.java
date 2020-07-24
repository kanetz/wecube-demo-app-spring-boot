package com.webank.wecube.demo.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemoControllerTest {

    private DemoController demoController;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void givenDemoController() {
        this.demoController = new DemoController(this.jdbcTemplate);
    }

    @Nested
    class HelloTest {
        static final String DEFAULT_MESSAGE = "Hello, world!";

        @Test
        void should_return_hello_world_when_name_list_is_NULL() {
            givenNameList(null);
            String message = demoController.hello();
            assertEquals(DEFAULT_MESSAGE, message);
        }

        @Test
        void should_return_hello_world_when_name_list_is_EMPTY() {
            givenNameList(Collections.emptyList());
            String message = demoController.hello();
            assertEquals("Hello, world!", message);
        }

        @Test
        void should_return_greetings_given_one_name() {
            givenNameList(Collections.singletonList("name1"));
            String message = demoController.hello();
            assertEquals("Hello, name1!", message);
        }

        @Test
        void should_return_greetings_given_multiple_names() {
            givenNameList(Arrays.asList("name1", "name2"));
            String message = demoController.hello();
            assertEquals("Hello, name1, name2!", message);
        }

        private void givenNameList(List<String> nameList) {
            when(jdbcTemplate.queryForList(any(String.class), eq(String.class)))
                    .thenReturn(nameList);
        }

    }

}