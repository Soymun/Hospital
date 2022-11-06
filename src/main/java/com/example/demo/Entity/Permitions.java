package com.example.demo.Entity;

public enum Permitions {
    USER("USER"), DOCTOR("DOCTOR"), ADMIN("ADMIN");
    final String p;

    Permitions(String p) {
        this.p = p;
    }
}
