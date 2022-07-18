package com.example.application.repositories;

import com.example.application.entities.*;
import com.example.application.enums.Gender;
import com.example.application.enums.HostelName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
class RegisterRepositoryTest {
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void existsRegisterCanBeFound(){
        StudentRegisterationPK studentRegisterationPK = new StudentRegisterationPK();
        Register register = new Register(studentRegisterationPK, LocalDateTime.now(),null);
        entityManager.merge(register);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
        Optional<Register> savedRegister = registerRepository.findById(register.getId());
        assertThat(savedRegister).isPresent();
        assertThat(savedRegister.get().getId()).isEqualTo(register.getId());
    }

    @Test
    void countTheUsedRoomsInHostel(){
        Room room = new Room(1);
        Student student = new Student(1,"Amna",Gender.FEMALE,"90909090","test@gimal.com");
        Bedspaces bedspaces = new Bedspaces(1,"double");
        Hostel hostel = new Hostel(2, Gender.FEMALE, HostelName.BLUE);
        StudentRegisterationPK studentRegisterationPK = new StudentRegisterationPK(student.getId(),hostel.getId(),room.getId(),bedspaces.getId());
        Register register = new Register(studentRegisterationPK, LocalDateTime.now(),null);
        entityManager.merge(register);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
        int NumOfUsedRooms = registerRepository.countTheUsedRoomsInHostel(hostel.getId());
        assertThat(NumOfUsedRooms).isEqualTo(1);
    }

    @Test
    void countTheUsedBedsInRoom(){
        Room room = new Room(1);
        Student student = new Student(1,"Amna",Gender.FEMALE,"90909090","test@gimal.com");
        Bedspaces bedspaces = new Bedspaces(1,"double");
        Hostel hostel = new Hostel(2, Gender.FEMALE, HostelName.BLUE);
        StudentRegisterationPK studentRegisterationPK = new StudentRegisterationPK(student.getId(),hostel.getId(),room.getId(),bedspaces.getId());
        Register register = new Register(studentRegisterationPK, LocalDateTime.now(),null);
        entityManager.merge(register);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
        int NumOfUsedBeds = registerRepository.countTheUsedBedsInRoom(hostel.getId(),room.getId());
        assertThat(NumOfUsedBeds).isEqualTo(1);
    }

    @Test
    void findAllByHostelId(){
        Room room = new Room(1);
        Student student = new Student(4,"Anas",Gender.MALE,"90909090","test@gimal.com");
        Bedspaces bedspaces = new Bedspaces(1,"double");
        Hostel hostel = new Hostel(4, Gender.FEMALE, HostelName.BLACK);
        StudentRegisterationPK studentRegisterationPK = new StudentRegisterationPK(student.getId(),hostel.getId(),room.getId(),bedspaces.getId());
        Register register = new Register(studentRegisterationPK, LocalDateTime.now(),null);
        entityManager.merge(register);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
        List<Integer> studentIds = registerRepository.findAllByHostelId(hostel.getId());
        assertThat(studentIds).contains(4);
    }

    @Test
    void existsByBedId(){
        Room room = new Room(1);
        Student student = new Student(4,"Anas",Gender.MALE,"90909090","test@gimal.com");
        Bedspaces bedspaces = new Bedspaces(1,"double");
        Hostel hostel = new Hostel(4, Gender.FEMALE, HostelName.BLACK);
        StudentRegisterationPK studentRegisterationPK = new StudentRegisterationPK(student.getId(),hostel.getId(),room.getId(),bedspaces.getId());
        Register register = new Register(studentRegisterationPK, LocalDateTime.now(),null);
        entityManager.merge(register);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
        int count = registerRepository.existsByBedId(bedspaces.getId());
        assertThat(count).isPositive();
    }

    @Test
    void findAllByRoomId(){
        Room room = new Room(1);
        Student student = new Student(4,"Anas",Gender.MALE,"90909090","test@gimal.com");
        Bedspaces bedspaces = new Bedspaces(1,"double");
        Hostel hostel = new Hostel(4, Gender.FEMALE, HostelName.BLACK);
        StudentRegisterationPK studentRegisterationPK = new StudentRegisterationPK(student.getId(),hostel.getId(),room.getId(),bedspaces.getId());
        Register register = new Register(studentRegisterationPK, LocalDateTime.now(),null);
        entityManager.merge(register);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
        List<Integer> count = registerRepository.findStudentIdsByRoomId(room.getId());
        assertThat(count).contains(4);
    }
}