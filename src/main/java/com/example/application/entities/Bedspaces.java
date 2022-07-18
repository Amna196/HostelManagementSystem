package com.example.application.entities;

import com.sun.istack.NotNull;
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
@ApiModel(description = "Bedspaces Table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bedspaces")
public class Bedspaces implements Serializable {
    @ApiModelProperty(value = "bedspacesId")
    @Id
    @Column(name = "bedspace_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty(value = "bedSize", required = true)
    @NotNull
    private String bedSize;
}
