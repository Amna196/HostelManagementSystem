package com.example.application.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Amna
 * @created 7/13/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "register")
@EntityListeners(AuditingEntityListener.class)
public class Register implements Serializable {
    @EmbeddedId
    private StudentRegisterationPK id;
    @Column(name = "registered_at")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
}
