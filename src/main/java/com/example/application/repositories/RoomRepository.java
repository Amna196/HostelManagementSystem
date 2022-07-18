package com.example.application.repositories;

import com.example.application.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Amna
 * @created 7/13/2022
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
