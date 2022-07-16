package com.example.application.repository;

import com.example.application.entity.Student;
import com.example.application.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Amna
 * @created 7/15/2022
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void existsByEmail(){
        Student student = new Student(1,"Amna", Gender.FEMALE,"90909090","test@gimal.com");
        entityManager.merge(student);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
        boolean actual = studentRepository.existsByEmail(student.getEmail());
        assertThat(actual).isTrue();
    }

    @Test
    void findNameById(){
        Student student = new Student(2,"Anfal",Gender.FEMALE,"90909090","test@gimal.com");
        entityManager.merge(student);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
        String studentName = studentRepository.findNameById(student.getId());
        assertThat(studentName).isEqualTo(student.getName());
    }
}