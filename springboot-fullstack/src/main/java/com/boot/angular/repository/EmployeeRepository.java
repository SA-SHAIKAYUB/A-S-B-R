package com.boot.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.angular.pojo.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
