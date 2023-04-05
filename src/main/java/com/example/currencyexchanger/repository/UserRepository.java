package com.example.currencyexchanger.repository;

import com.example.currencyexchanger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String username);


    void deleteByUserName(String username);

    boolean existsByUserName(String username);
}
