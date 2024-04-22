package com.developers.serviceApi.repo;

import com.developers.serviceApi.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, String> {
}
