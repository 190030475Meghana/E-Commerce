package com.publicissapient.ecommerce.repository;

import com.publicissapient.ecommerce.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserId(Long userId);

    Optional<UserInfo> findByEmail(String email);



}
