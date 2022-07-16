package com.example.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Amna
 * @created 7/12/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bedspaces")
public class Bedspaces implements Serializable {
    @Id
    @Column(name = "bedspace_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bedSize;
}
