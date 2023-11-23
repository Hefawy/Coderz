package com.Login.Page.firstApp.repository;


import com.Login.Page.firstApp.model.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<UserApp,Long> {
}
