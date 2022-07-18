package com.example.application.repositories;

import com.example.application.entities.Register;
import com.example.application.entities.StudentRegisterationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Amna
 * @created 7/14/2022
 */
@Repository
public interface RegisterRepository extends JpaRepository<Register, StudentRegisterationPK> {
    @Query("select count(*) from Register r where r.id.hostelId = :hostelId")
    int countTheUsedRoomsInHostel(Integer hostelId);

    @Query("select count(*) from Register r where r.id.hostelId = :hostelId and r.id.roomId = :roomId")
    int countTheUsedBedsInRoom(Integer hostelId, Integer roomId);

    @Query("select r.id.studentId from Register r where r.id.hostelId = :hostelId")
    List<Integer> findAllByHostelId(Integer hostelId);

    @Query("select count(*) from Register r where r.id.bedspaceId = :bedspaceId")
    int existsByBedId(Integer bedspaceId);

    @Query("select r.id.studentId from Register r where r.id.roomId = :roomId")
    List<Integer> findStudentIdsByRoomId(Integer roomId);
}
