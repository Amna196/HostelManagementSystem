package com.example.application.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "register")
@EntityListeners(AuditingEntityListener.class)
public class Register implements Serializable {
    @ApiModelProperty(value = "RegisterId", required = true)
    @EmbeddedId
    private StudentRegisterationPK id;
    @ApiModelProperty(value = "createdAt")
    @Column(name = "registered_at")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "modifiedAt")
    @Column(name = "modified_at")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
}
