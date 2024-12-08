package com.practice.project.dao.primary.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.validation.annotation.Validated;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Builder
@Validated
@Table(
        name="security_user",
        uniqueConstraints = @UniqueConstraint(columnNames = {"firstname", "lastname"}),
        indexes =  {
                @Index(name = "idx_email", columnList = "email"),
                @Index(name = "idx_username", columnList = "username",unique = true),
                @Index(name = "idx_full_name", columnList = "firstname, lastname",unique = true),
        })
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@NoArgsConstructor(access= AccessLevel.PUBLIC)
@SQLDelete(sql="UPDATE security_user SET deleted=true WHERE id=?")
@EntityListeners({SoftDeleteListener.class,ActiveUserListener.class})
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name="username", length=100, unique=true, nullable=false)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(length=50, nullable=false)
    private String firstname;

    @Column(length=50, nullable=false)
    private String lastname;

    @Email @Column(length = 100, nullable=false)
    private String email;

    @Builder.Default
    private boolean deleted = false;

    @Builder.Default
    private boolean active = true;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
}
