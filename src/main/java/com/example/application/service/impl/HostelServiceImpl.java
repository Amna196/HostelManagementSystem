package com.example.application.service.impl;

import com.example.application.entity.Hostel;
import com.example.application.entity.Register;
import com.example.application.entity.Student;
import com.example.application.enums.HostelName;
import com.example.application.exception.BedNotAvailableException;
import com.example.application.exception.HostelIsFullException;
import com.example.application.exception.HostelNotValidException;
import com.example.application.exception.RoomIsFullException;
import com.example.application.model.request.RegisterRequest;
import com.example.application.model.response.RegisterResponse;
import com.example.application.repository.*;
import com.example.application.service.HostelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Amna
 * @created 7/12/2022
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class HostelServiceImpl implements HostelService {
    private final StudentRepository studentRepository;
    private final HostelRepository hostelRepository;
    private final RoomRepository roomRepository;
    private final BedspacesRepository bedspacesRepository;
    private final RegisterRepository registerRepository;
    private final ModelMapper modelMapper;
    @Override
    public Student registerStudentInSystem(Student request) throws Throwable{
        if (request.getEmail() != null) {
            if (studentRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateKeyException("Student with emailId:" + request.getEmail() + " already registered");
            }
            studentRepository.save(request);
            return request;
        }
        throw new NullPointerException("Please set all students details");
    }

    @Override
    public RegisterResponse studentRequestBed(RegisterRequest request) throws Throwable{
        if (request.getHostelId() == null || request.getBedspaceId() == null || request.getStudentId() == null || request.getRoomId() == null) {
            throw new NullPointerException("Please set all book details");
        }
        validateBookDetails(request);//validate the exists of the book details
        validateHostel(request);//validate student's gender with the chosen hostel
        validateAvailabilityOfRooms(request);//validate the availability of rooms in hostel
        validateAvailabilityOfBeds(request);//validate the availability of beds in room of hostel
        Register register = modelMapper.map(request, Register.class);
        return modelMapper.map(registerRepository.save(register), RegisterResponse.class);

    }

    @Override
    public List<String> studentsNameByHostelName(HostelName hostelName){
        Hostel hostel = null;
        List<String> studentNames = new ArrayList<>();
        for(HostelName name : HostelName.values()){
            if(name.equals(hostelName)){
                hostel = hostelRepository.findByHostelName(name);
                break;
            }
        }
        if(hostel != null) {
                registerRepository.findAllByHostelId(hostel.getId()).forEach(
                        studentId -> studentNames.add(studentRepository.findNameById(studentId))
                );
                return studentNames;
        }
        throw new HostelNotValidException("Hostel name not exists");
    }

    @Override
    public List<Student> studentsByRoomId(Integer roomId) throws Throwable{
        List<Student> students = new ArrayList<>();
        roomRepository.findById(roomId).orElseThrow(() -> {
            throw new NoSuchElementException("Room id Not Exists");
        });
        List<Integer> studentIds = registerRepository.findStudentIdsByRoomId(roomId);
        for(Integer studentId : studentIds){
            students.add(studentRepository.findById(studentId).orElseThrow(() -> {
                throw new NoSuchElementException("Student is not exists");
            }));
        }
        return students;
    }

    private void validateAvailabilityOfBeds(RegisterRequest request) throws Throwable{
        if(registerRepository.countTheUsedBedsInRoom(request.getHostelId(),request.getRoomId()) >= 4){
            throw new RoomIsFullException("Room is full");
        }
        if(registerRepository.existsByBedId(request.getBedspaceId()) > 0){
            throw new BedNotAvailableException("Bedspace not available");
        }
    }

    private void validateAvailabilityOfRooms(RegisterRequest request) throws HostelIsFullException{
        if(registerRepository.countTheUsedRoomsInHostel(request.getHostelId()) >= 80){
            throw new HostelIsFullException("Hostel is full");
        }
    }

    private void validateHostel(RegisterRequest request) throws Throwable{
        //check hostel gender = student gender
        Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> {
            throw new NoSuchElementException("Student id Not Registered");
        });
        Hostel hostel = hostelRepository.findById(request.getHostelId()).orElseThrow(() -> {
            throw new NoSuchElementException("Hostel id Not Exists");
        });
        if(Objects.nonNull(hostel) && Objects.nonNull(student)) {
            if (!student.getGender().equals(hostel.getCategory())) {
                throw new HostelNotValidException("Hostel Not Valid");
            }
        }
    }

    private void validateBookDetails(RegisterRequest request) throws Throwable{
        studentRepository.findById(request.getStudentId()).orElseThrow(() -> {
            throw new NoSuchElementException("Student id Not Registered");
        });
        hostelRepository.findById(request.getHostelId()).orElseThrow(() -> {
            throw new NoSuchElementException("Hostel id Not Exists");
        });
        roomRepository.findById(request.getRoomId()).orElseThrow(() -> {
            throw new NoSuchElementException("Room id Not Exists");
        });
        bedspacesRepository.findById(request.getBedspaceId()).orElseThrow(() -> {
            throw new NoSuchElementException("Bed id Not Exists");
        });
    }

}
