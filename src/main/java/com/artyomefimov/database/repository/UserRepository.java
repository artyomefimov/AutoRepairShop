package com.artyomefimov.database.repository;

import com.artyomefimov.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findAllByRole(int role);
}
