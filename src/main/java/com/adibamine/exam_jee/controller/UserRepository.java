package com.adibamine.exam_jee.controller;

import com.adibamine.exam_jee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    /*User findByUserName(String username);*/
    User findByUsername(String username);
}
