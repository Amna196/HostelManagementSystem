package com.example.application.repositories;

import com.example.application.entities.Hostel;
import com.example.application.enums.Gender;
import com.example.application.enums.HostelName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Amna
 * @created 7/15/2022
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
class HostelRepositoryTest {
    @Autowired private HostelRepository hostelRepository;
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(hostelRepository).isNotNull();
    }
    @Test
//    @Sql("/data.sql")
    void existsHostelCanBeFound(){
        Hostel hostel = new Hostel(1, Gender.FEMALE, HostelName.RED);
        hostelRepository.save(hostel);
        assertThat(hostelRepository.findById(1).get().getHostelName()).isEqualTo(HostelName.RED);
    }

    @Test
//    @Sql("data.sql")
    void findHostelByHostelName(){
        Hostel hostel = new Hostel(1, Gender.FEMALE, HostelName.RED);
        hostelRepository.save(hostel);
        Hostel actual = hostelRepository.findByHostelName(HostelName.RED);
        assertThat(actual).isNotNull();
    }

}