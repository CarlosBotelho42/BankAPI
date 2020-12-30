package com.carlos.bankAPI.repositories;

import com.carlos.bankAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
