package com.practice.project.dao.primary.repository;

import com.practice.project.dao.primary.entity.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //cached the frequently used query
    @Query("SELECT a FROM User a WHERE a.username = :username")
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<User> findByUsername(@Param("username") String username);
}
