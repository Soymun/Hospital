package com.example.demo.Entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permitions.USER)), DOCTOR(Set.of(Permitions.USER, Permitions.DOCTOR)), ADMIN(Set.of(Permitions.USER, Permitions.DOCTOR, Permitions.ADMIN));
    final Set<Permitions> set;

    Role(Set<Permitions> set) {
        this.set = set;
    }

    public Set<SimpleGrantedAuthority> getAuthority(){
        return set.stream().map(n -> new SimpleGrantedAuthority(n.p)).collect(Collectors.toSet());
    }
}
