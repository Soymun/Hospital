package com.example.demo.Repositories;

import com.example.demo.DTO.ScheduleDto;
import com.example.demo.Entity.Troubles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TroublesRepository extends JpaRepository<Troubles, Long> {
}
