package com.practice.project.dao.primary.entity;

import jakarta.persistence.PreRemove;

public class ActiveUserListener {
    @PreRemove
    public void fetchActiveUser(Object entity) {
        if (entity instanceof Active) {
            ((Active) entity).setActive(true);
        }
    }
}
