package com.example.application.entities;

import com.example.application.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Amna
 * @created 7/12/2022
 */
@ApiModel(description = "")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {
    @ApiModelProperty(value = "StudentId")
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty(value = "name", required = true)
    private String name;
    @ApiModelProperty(value = "gender", required = true)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ApiModelProperty(value = "phoneNumber", required = true)
    private String phoneNumber;
    @ApiModelProperty(value = "email", required = true)
    private String email;
}
