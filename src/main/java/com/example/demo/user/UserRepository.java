package com.example.demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = :username and u.password = :password")
    Optional<User> findfindByUsernameAndPassword(@Param("username") String username,
            @Param("password") String password);

}
