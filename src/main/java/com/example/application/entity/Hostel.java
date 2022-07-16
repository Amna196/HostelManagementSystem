package com.example.application.entity;

import com.example.application.enums.Gender;
import com.example.application.enums.HostelName;
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
@Table(name = "hostel")
public class Hostel implements Serializable {
    @Id
    @Column(name = "hostel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Gender category;
    @Enumerated(EnumType.STRING)
    private HostelName hostelName;

}
