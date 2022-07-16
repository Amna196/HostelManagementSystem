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
@Table(name = "room")
public class Room implements Serializable {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @OneToMany(mappedBy = "room")
//    private List<Student> students;
//    @OneToMany(mappedBy = "room")
//    private List<Bedspaces> beds;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "hostel_id", referencedColumnName = "hostel_id", nullable = false)
//    private Hostel hostel;
}
