package com.practice.project.dao.primary.repository;

import com.practice.project.dao.primary.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

//@RepositoryRestResource(path="members")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> , JpaSpecificationExecutor<Employee> {

    // Using the named query defined in the Employee entity
    @Query(name = "Employee.findByEmail")
    List<Employee> findByEmail(String email);

    @Override
    @EntityGraph(value = "employee.detail", type = EntityGraph.EntityGraphType.LOAD)
    List<Employee> findAll(Specification<Employee> spec);

    @Query(name = "Employee.getAll")
    List<Employee> getAll();
}
