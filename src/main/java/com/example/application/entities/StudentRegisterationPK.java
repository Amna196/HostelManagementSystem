package com.example.application.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Amna
 * @created 7/13/2022
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegisterationPK implements Serializable {
    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;
    @Column(name = "hostel_id", insertable = false, updatable = false)
    private Integer hostelId;
    @Column(name = "room_id", insertable = false, updatable = false)
    private Integer roomId;
    @Column(name = "bedspace_id", insertable = false, updatable = false)
    private Integer bedspaceId;
}
