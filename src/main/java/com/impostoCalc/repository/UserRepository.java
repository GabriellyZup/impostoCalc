package com.impostoCalc.repository;

import com.impostoCalc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { // Alterado o tipo do ID para Long
    Optional<User> findByUsername(String username);


    //userRepository.findByUsername("admin")).thenReturn(new org.springframework.security.core.userdetails.User("admin", "password",Collections.singletonList(new SimpleGrantedAuthority(UserRole.ADMIN.Username()))));
}