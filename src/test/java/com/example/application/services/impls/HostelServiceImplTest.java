package com.example.application.services.impls;

import com.example.application.entities.*;
import com.example.application.enums.Gender;
import com.example.application.enums.HostelName;
import com.example.application.exceptions.BedNotAvailableException;
import com.example.application.exceptions.HostelIsFullException;
import com.example.application.exceptions.HostelNotValidException;
import com.example.application.exceptions.RoomIsFullException;
import com.example.application.models.requests.RegisterRequest;
import com.example.application.models.responses.RegisterResponse;
import com.example.application.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

/**
 * @author Amna
 * @created 7/12/2022
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) // to allow redundant stubbing
class HostelServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private HostelRepository hostelRepository;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private BedspacesRepository bedspacesRepository;
    @Mock
    private RegisterRepository registerRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private HostelServiceImpl service;

    private final Student student = new Student();
    private final Hostel hostel = new Hostel();
    private final Room room = new Room();
    private final Bedspaces bedspaces = new Bedspaces();
    private RegisterRequest registerRequest;
    private final RegisterResponse registerResponse = new RegisterResponse();
    private final Register register = new Register();

    @Test
    void registerStudentToSystem_addStudentRecordsInStudentTable_ReturnStudentRecord() throws Throwable {
        //given
        student.setName("Amna");
        student.setGender(Gender.FEMALE);
        student.setEmail("new@gemail.com");
        student.setPhoneNumber("909909090");
        //when
        when(studentRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        //then
        assertThat(service.registerStudentInSystem(student)).isEqualTo(student);
    }
    @Test
    void registerDuplicateStudentToSystem_addStudentRecordsInStudentTable_ReturnDuplicateKeyException(){
        //given
        student.setName("Amna");
        student.setGender(Gender.FEMALE);
        student.setEmail("new@gemail.com");
        student.setPhoneNumber("909909090");
        //when
        when(studentRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        //then
        assertThatThrownBy(() -> service.registerStudentInSystem(student))
                .isInstanceOf(DuplicateKeyException.class);
    }
    @Test
    void registerStudentWithMissingData_addStudentRecordsInStudentTable_ReturnNullPointerException(){
        //given student with null email value
        student.setName("Amna");
        student.setGender(Gender.FEMALE);
        student.setPhoneNumber("909909090");
        //then
        Assertions.assertThrows(NullPointerException.class,
                () -> service.registerStudentInSystem(student));
    }
    @Test
    void getStudentNamesFromHostelId_retrieveStudentsNameByHostelName_ReturnListOfString(){
        //given
        List<Integer> studentIds = new ArrayList<>();
        studentIds.add(student.getId());
        //when
        when(hostelRepository.findByHostelName(Mockito.any(HostelName.class))).thenReturn(hostel);
        when(registerRepository.findAllByHostelId(hostel.getId())).thenReturn(studentIds);
        when(studentRepository.findNameById(student.getId())).thenReturn(student.getName());
        //then
        assertThat(service.studentsNameByHostelName(HostelName.RED)).contains(student.getName());
    }
    @Test
    void getStudentNamesFromInValidHostelName_retrieveStudentsNameByInValidHostelName_ReturnHostelNotValidException(){
        //when
        when(hostelRepository.findByHostelName(Mockito.any(HostelName.class))).thenReturn(null);
        //then
        Assertions.assertThrows(HostelNotValidException.class,
                () -> service.studentsNameByHostelName(HostelName.RED));
    }

    @Test
    void studentsByValidRoomId_studentsByRoomId_ReturnListOfStudents() throws Throwable {
        //given
        room.setId(1);
        List<Integer> studentIds = new ArrayList<>();
        studentIds.add(student.getId());
        //when
        when(roomRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(room));
        when(registerRepository.findStudentIdsByRoomId(room.getId())).thenReturn(studentIds);
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        //then
        assertThat(service.studentsByRoomId(room.getId())).contains(student);
    }
    @Test
    void studentsByInValidRoomId_studentsByRoomId_ReturnNoSuchElementException() throws Throwable {
        //when
        when(roomRepository.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
        //then
        Assertions.assertThrows(NoSuchElementException.class,
                () -> service.studentsByRoomId(room.getId()));
    }

    @Test
    void studentsByInValidStudentId_studentsByRoomId_ReturnNoSuchElementException() throws Throwable {
        //given
        room.setId(1);
        List<Integer> studentIds = new ArrayList<>();
        studentIds.add(student.getId());
        //when
        when(studentRepository.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
        //then
        Assertions.assertThrows(NoSuchElementException.class,
                () -> service.studentsByRoomId(room.getId()));
    }

    @Test
    void studentRequestBedWithValidDetails_studentRequestBed_ReturnRegisterResponse() throws Throwable {
        //given
        registerRequest = new RegisterRequest(1,1,1,1);
        student.setGender(Gender.FEMALE);
        hostel.setCategory(Gender.FEMALE);
        //when
        when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
        when(hostelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hostel));
        when(bedspacesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bedspaces));
        when(roomRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(room));
        when(modelMapper.map(RegisterRequest.class,Register.class)).thenReturn(register);
        when(service.studentRequestBed(registerRequest)).thenReturn(registerResponse);
        //then
        assertThat(service.studentRequestBed(registerRequest)).isInstanceOf(RegisterResponse.class);
    }

    @Test
    void studentRequestBedWithoutRegisterDetails_studentRequestBed_ReturnNullPointerException() throws Throwable {
        registerRequest = new RegisterRequest();
        assertThatThrownBy(() -> service.studentRequestBed(registerRequest))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void studentRequestBedWithInValidStudentId_studentRequestBed_ReturnNoSuchElementException() throws Throwable {
        //given
        registerRequest = new RegisterRequest(1,1,1,1);
        //when
        when(studentRepository.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
        when(hostelRepository.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
        when(bedspacesRepository.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
        when(roomRepository.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
        //then
        assertThatThrownBy(() -> service.studentRequestBed(registerRequest))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void studentRequestBedWithInValidHostelDetails_studentRequestBed_ReturnHostelNotValidException() throws Throwable {
        //given
        registerRequest = new RegisterRequest(1,1,1,1);
        student.setGender(Gender.FEMALE);
        hostel.setCategory(Gender.MALE);
        //when
        when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
        when(hostelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hostel));
        when(bedspacesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bedspaces));
        when(roomRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(room));
        //then
        assertThatThrownBy(() -> service.studentRequestBed(registerRequest))
                .isInstanceOf(HostelNotValidException.class);
    }

    @Test
    void validateAvailabilityOfRooms_InStudentRequestBed_ReturnHostelIsFullException() throws HostelIsFullException {
        //given
        registerRequest = new RegisterRequest(1,1,1,1);
        student.setGender(Gender.FEMALE);
        hostel.setCategory(Gender.FEMALE);
        //when
        when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
        when(hostelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hostel));
        when(bedspacesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bedspaces));
        when(roomRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(room));
        when(registerRepository.countTheUsedRoomsInHostel(Mockito.anyInt())).thenReturn(81);
        //then
        assertThatThrownBy(() -> service.studentRequestBed(registerRequest))
                .isInstanceOf(HostelIsFullException.class);
    }

    @Test
    void validateAvailabilityOfBeds_InStudentRequestBed_ReturnRoomIsFullException() throws Throwable {
        //given
        registerRequest = new RegisterRequest(1,1,1,1);
        student.setGender(Gender.FEMALE);
        hostel.setCategory(Gender.FEMALE);
        //when
        when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
        when(hostelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hostel));
        when(bedspacesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bedspaces));
        when(roomRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(room));
        when(registerRepository.countTheUsedBedsInRoom(Mockito.anyInt(),Mockito.anyInt())).thenReturn(4);
        //then
        assertThatThrownBy(() -> service.studentRequestBed(registerRequest))
                .isInstanceOf(RoomIsFullException.class);
    }

    @Test
    void validateAvailabilityOfBeds_InStudentRequestBed_ReturnBedNotAvailableException() throws Throwable {
        //given
        registerRequest = new RegisterRequest(1,1,1,1);
        student.setGender(Gender.FEMALE);
        hostel.setCategory(Gender.FEMALE);
        //when
        when(studentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(student));
        when(hostelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(hostel));
        when(bedspacesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bedspaces));
        when(roomRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(room));
        when(registerRepository.existsByBedId(Mockito.anyInt())).thenReturn(1);
        //then
        assertThatThrownBy(() -> service.studentRequestBed(registerRequest))
                .isInstanceOf(BedNotAvailableException.class);
    }

}