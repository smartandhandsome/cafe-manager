package com.cafe.persistence.admin.repository;

import com.cafe.persistence.admin.entity.AdminJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJpaRepository extends JpaRepository<AdminJpaEntity, Long> {
}
