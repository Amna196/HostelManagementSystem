package com.example.application.service;

import com.example.application.entity.Student;
import com.example.application.enums.HostelName;
import com.example.application.model.request.RegisterRequest;
import com.example.application.model.response.RegisterResponse;

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
