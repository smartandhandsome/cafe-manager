package com.cafe.persistence.admin.repository;

import com.cafe.persistence.admin.entity.AdminJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminJpaRepository extends JpaRepository<AdminJpaEntity, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<AdminJpaEntity> findByPhoneNumber(String phoneNumber);

    @Query("SELECT e.adminId FROM AdminJpaEntity e WHERE e.phoneNumber = :phoneNumber")
    Optional<Long> findAdminIdByPhoneNumber(String phoneNumber);

}
