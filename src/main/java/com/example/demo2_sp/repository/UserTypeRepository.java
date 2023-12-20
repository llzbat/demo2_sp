package com.example.demo2_sp.repository;

import com.example.demo2_sp.entity.UserType.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByUID(int UID);
}
