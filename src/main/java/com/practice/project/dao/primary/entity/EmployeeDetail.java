package com.practice.project.dao.primary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

@Data
@Entity
public class EmployeeDetail {
    @Id
    private Long id;

    @Lazy
    @OneToOne(mappedBy = "detail", fetch = FetchType.LAZY)
    private Employee employee;
}
