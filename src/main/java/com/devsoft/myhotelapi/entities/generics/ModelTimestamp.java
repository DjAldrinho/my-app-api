package com.devsoft.myhotelapi.entities.generics;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class ModelTimestamp {

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    @PrePersist
    public void preCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }


    @PreUpdate
    private void preUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }


}
