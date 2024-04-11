package com.martijn.CompCheckV2.presistence.repository;

import com.martijn.CompCheckV2.presistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

}
