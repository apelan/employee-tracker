package com.homework.employeetracker.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homework.employeetracker.data.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByName(String name);

}
