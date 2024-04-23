package com.developers.serviceApi.repo;

import com.developers.serviceApi.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, String> {

    @Query(value = "SELECT salary_id FROM salary WHERE salary_id like ?% " +
            "ORDER BY CAST(SUBSTRING(salary_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String prefix, int i);
    @Query(value = "SELECT * FROM salary WHERE month like %?1% ", nativeQuery = true)
    List<Salary> findBySearchText(String searchText);

    @Query(value = "SELECT * \n" +
            "FROM\n" +
            "salary s\n" +
            "INNER JOIN user_type ut\n" +
            "INNER JOIN employee e\n" +
            "ON \n" +
            "e.user_type_id = ut.user_type_id\n" +
            "AND\n" +
            "e.employee_id=s.employee_id\n" +
            "WHERE s.month=?2 AND ut.user_type_id=?1 ", nativeQuery = true)
    Optional<Salary> findByUserTypeIdAndMonth(String userTypeId, String month);
}
