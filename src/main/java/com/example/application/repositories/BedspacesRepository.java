package com.example.application.repositories;

import com.example.application.entities.Bedspaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Amna
 * @created 7/13/2022
 */
@Repository
public interface BedspacesRepository extends JpaRepository<Bedspaces, Integer> {
}
