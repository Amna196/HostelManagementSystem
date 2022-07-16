package com.example.application.repository;

import com.example.application.entity.Hostel;
import com.example.application.enums.HostelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Amna
 * @created 7/13/2022
 */
@Repository
public interface HostelRepository extends JpaRepository<Hostel, Integer> {
    Hostel findByHostelName(HostelName hostelName);
}
