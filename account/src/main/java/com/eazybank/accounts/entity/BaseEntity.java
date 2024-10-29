package com.eazybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Column(updatable = false)  // this column will not be updated
    private LocalDateTime createdAt;

    @Column(updatable = false)  // this column will not be updated
    private String createdBy;

    @Column(insertable = false)  // this column will not be inserted
    private LocalDateTime updatedAt;

    @Column(insertable = false)  // this column will not be inserted
    private String updatedBy;


}
