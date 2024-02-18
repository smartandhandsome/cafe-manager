package com.cafe.admin.persistence.entity;

import com.cafe.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "admins", indexes = {
        @Index(name = "admin_phone_number_index", columnList = "phone_number", unique = true)
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String encodedPassword;

    @Builder
    private AdminJpaEntity(String phoneNumber, String encodedPassword) {
        this.phoneNumber = phoneNumber;
        this.encodedPassword = encodedPassword;
    }
}
