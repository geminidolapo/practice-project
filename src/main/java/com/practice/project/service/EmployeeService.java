package com.practice.project.service;

import com.practice.project.dao.primary.entity.Employee;
import com.practice.project.dao.primary.repository.EmployeeRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private static Specification<Employee> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    private static Specification<Employee> hasAge(String firstName) {
        return (root, query, cb) -> cb.equal(root.get("firstName"), firstName);
    }

    public List<Employee> findUsersByCriteria(String email, String firstName) {
        Specification<Employee> where = where(hasEmail(email));
        if (firstName != null) {
            where = where.or(hasAge(firstName));
        }

        return employeeRepository.findAll(where);  //findAll is available via JpaSpecificationExecutor
    }

    public void performStreamAction() {
        //I used a supplier to ensure findAll doesn't initialize till its needed
        Supplier<List<Employee>> employees = employeeRepository::findAll;

        //List<Employee> employees = employeeRepository.findAll();

        Map<String, List<Employee>> employeesByDepartment =
                employees.get().stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));


        List<String> strings = employeesByDepartment.keySet().stream().sorted().toList();
        Map<Integer, List<String>> stringsByLength =
                strings.stream()
                        .collect(Collectors.groupingBy(String::length));
    }


//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    public Object findByAccountId(Long accountId) {
//        // Prevent other transactions from modifying this account
//        return null;
//    }

    @Cacheable("employees")
    @Lock(LockModeType.READ)
    public Employee findByEmployeeId(Long id) {
        // Prevent other transactions from modifying this employee
        return employeeRepository.findById(id).orElse(null);
    }
}