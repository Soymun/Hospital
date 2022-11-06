package com.example.demo.Repositories;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordService extends JpaRepository<User, Long> {
}
