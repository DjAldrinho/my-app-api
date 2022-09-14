package com.devsoft.myhotelapi.models.generics;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

@MappedSuperclass
public class ModelTimestamp {

    public LocalDateTime createdAt;

    @FutureOrPresent
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
