package com.webank.wecube.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class DemoController {
    private static final String QUERY_SQL = "SELECT `name` FROM `name_log`";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DemoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/")
    public String hello() {
        List<String> names = retrieveNames();
        return buildGreetingMessage(names);
    }

    private List<String> retrieveNames() {
        return this.jdbcTemplate.queryForList(QUERY_SQL, String.class);
    }

    private String buildGreetingMessage(List<String> names) {
        if(CollectionUtils.isEmpty(names)) return "Hello, world!";

        return String.format("Hello, %s!", String.join(", ", names));
    }

}
