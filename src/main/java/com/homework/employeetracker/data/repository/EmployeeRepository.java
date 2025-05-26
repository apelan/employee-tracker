package com.homework.employeetracker.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.homework.employeetracker.data.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
        SELECT e FROM Employee e
        LEFT JOIN e.team t
        LEFT JOIN t.lead l
        WHERE (LOWER(e.name) LIKE CONCAT('%', LOWER(:name), '%') OR :name IS NULL)
          AND (LOWER(t.name) LIKE CONCAT('%', LOWER(:teamName), '%') OR :teamName IS NULL)
          AND (LOWER(l.name) LIKE CONCAT('%', LOWER(:teamLead), '%') OR :teamLead IS NULL)
    """)
    Page<Employee> searchEmployees(@Param("name") String name,
        @Param("teamName") String teamName,
        @Param("teamLead") String teamLead,
        Pageable pageable);

}
