package com.example.demo3.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginChecker {
    private static final Map<String, String> passwords = new HashMap<>();
    private final MessageDigest md;

    static {
        passwords.put("user", "о\u0011Л±ђRд\u000B\u0007ЄАК\u0006\f#о");
        passwords.put("admin", "!#/)zWҐ§C‰J\u000EJЂ\u001FГ");
    }

    public LoginChecker() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("MD5");
    }

    public boolean logIn(String username, String password) {
        String passwordHash = passwords.get(username);
        String h = getHash(password);
        if (passwordHash == null) return false;
        return  passwordHash.equals(getHash(password));
    }

    public String getHash(String toHash){
        md.update(toHash.getBytes());
        return new String(md.digest());
    }
}
