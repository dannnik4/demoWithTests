package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
//@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.name = ?1")
    List<Employee> getEmployeeByName(String name);

    @Query(value = "SELECT * FROM users WHERE country = ?", nativeQuery = true)
    List<Employee> getEmployeeByCountry(String country);

    @Query(value = "SELECT name FROM users", nativeQuery = true)
    List<Employee> getAllByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.phone = ?1")
    List<Employee> getEmployeeByPhone (Integer phone);

}