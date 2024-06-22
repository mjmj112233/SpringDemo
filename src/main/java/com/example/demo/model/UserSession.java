package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@SessionScope
public class UserSession {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static final Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("marcus", "password1");
        userMap.put("joaquin", "password2");
        userMap.put("paler", "password3");
    }

}

