package com.papaco.papacomemberservice.acceptance;

import java.util.*;
import java.util.stream.Collectors;

public class TechStackFixture {
    public static final Map<String, Object> JAVA = new HashMap<>();
    public static final Map<String, Object> JAVASCRIPT = new HashMap<>();
    public static final Map<String, Object> MYSQL = new HashMap<>();
    public static final Map<String, Object> AWS = new HashMap<>();
    public static final Map<String, Object> DOCKER = new HashMap<>();
    public static final List<Map<String, Object>> ALL_TECH_STACKS;
    public static final List<String> ALL_TECH_STACK_NAMES;


    static {
        JAVA.put("name", "java");
        JAVASCRIPT.put("name", "javascript");
        MYSQL.put("name", "mysql");
        AWS.put("name", "aws");
        DOCKER.put("name", "docker");

        ALL_TECH_STACKS = Arrays.asList(JAVA, JAVASCRIPT, MYSQL, AWS, DOCKER);

        ALL_TECH_STACK_NAMES = ALL_TECH_STACKS.stream()
                .map(stack -> String.valueOf(stack.get("name")))
                .collect(Collectors.toList());
    }
}
