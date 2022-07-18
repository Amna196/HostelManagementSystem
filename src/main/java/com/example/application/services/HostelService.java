package com.example.application.services;

import com.example.application.entities.Student;
import com.example.application.enums.HostelName;
import com.example.application.models.requests.RegisterRequest;
import com.example.application.models.responses.RegisterResponse;

import java.util.List;

/**
 * @author Amna
 * @created 7/12/2022
 */
public interface HostelService {

    Student registerStudentInSystem(Student student) throws Throwable;

    RegisterResponse studentRequestBed(RegisterRequest request) throws Throwable;

    List<String> studentsNameByHostelName(HostelName hostelName);

    List<Student> studentsByRoomId(Integer roomId) throws Throwable;
}
