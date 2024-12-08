package com.practice.project.dao.primary.entity;

import jakarta.persistence.PreRemove;

public class SoftDeleteListener {

    @PreRemove
    public void preventPhysicalDeletion(Object entity) {
        if (entity instanceof Deletable) {
            ((Deletable) entity).setDeleted(true);
        }
    }
}
