package com.cafe.persistence.util;

import com.cafe.persistence.admin.entity.AdminJpaEntity;

import java.lang.reflect.Field;

public final class EntityIdInjector {

    private EntityIdInjector() {
    }

    public static <T> void inject(T entity, String fieldName, Long id) {
        try {
            Field adminIdField = AdminJpaEntity.class.getDeclaredField(fieldName);
            adminIdField.setAccessible(true);
            adminIdField.set(entity, id);
            adminIdField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
