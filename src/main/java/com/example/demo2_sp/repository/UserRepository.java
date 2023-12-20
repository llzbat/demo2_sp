package com.example.demo2_sp.repository;

import com.example.demo2_sp.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);
    User findByUID(int uid);
    @Query("SELECT u FROM User u WHERE u.id_card = :id_card")
    User findByIdCard(@Param("id_card") String idCard);
}
