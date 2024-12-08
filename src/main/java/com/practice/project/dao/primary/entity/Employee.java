package com.practice.project.dao.primary.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;


@Entity
@Table(
        name = "employees",
        uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}),
        indexes = @Index(name = "idx_first_name", columnList = "first_name")
)
@NamedEntityGraph(
        name = "employee.detail",
        attributeNodes = {@NamedAttributeNode("detail")}
)
@NamedQuery(
        name = "Employee.findByEmail",
        query = "SELECT u FROM Employee u WHERE u.email = :email"
)
@NamedQuery(
        name = "Employee.getAll",
        query = "SELECT u FROM Employee u"
)

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column(name = "first_name")
    private String firstName;

    //    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    private String department;

    @OneToOne
    private EmployeeDetail detail;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Employee employee = (Employee) o;
        return getId() != null && Objects.equals(getId(), employee.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}