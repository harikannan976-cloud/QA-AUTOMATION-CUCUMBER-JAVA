package com.demo.utils;

import java.time.Instant;

public class TestDataUtil {
    public String uniqueEmail() {
        return "testuser_" + Instant.now().toEpochMilli() + "@example.com";
    }
}
