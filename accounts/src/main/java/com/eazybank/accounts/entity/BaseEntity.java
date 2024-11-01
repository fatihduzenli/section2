package com.eazybank.accounts.entity;

import com.eazybank.accounts.audit.AuditAwareImpl;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)  // this column will not be updated
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(updatable = false)  // this column will not be updated
    private String createdBy;
    @LastModifiedDate
    @Column(insertable = false)  // this column will not be inserted
    private LocalDateTime updatedAt;
    @LastModifiedBy
    @Column(insertable = false)  // this column will not be inserted
    private String updatedBy;


}
