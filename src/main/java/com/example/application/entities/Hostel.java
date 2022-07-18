package com.example.application.entities;

import com.example.application.enums.Gender;
import com.example.application.enums.HostelName;
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
@Table(name = "hostel")
public class Hostel implements Serializable {
    @ApiModelProperty(value = "HostelId")
    @Id
    @Column(name = "hostel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty(value = "category", required = true)
    @Enumerated(EnumType.STRING)
    private Gender category;
    @ApiModelProperty(value = "hostelName", required = true)
    @Enumerated(EnumType.STRING)
    private HostelName hostelName;

}
