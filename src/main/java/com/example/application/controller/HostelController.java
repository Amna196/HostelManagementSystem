package com.example.application.controller;

import com.example.application.entity.Student;
import com.example.application.enums.HostelName;
import com.example.application.model.request.RegisterRequest;
import com.example.application.model.response.RegisterResponse;
import com.example.application.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Amna
 * @created 7/14/2022
 */
@RestController
@RequiredArgsConstructor
public class HostelController {
    private final HostelService service;

    @PostMapping("/register")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws Throwable {
        return ResponseEntity.ok().body(service.registerStudentInSystem(student));
    }

    @PostMapping("/request/bed")
    public ResponseEntity<RegisterResponse> requestABed(@RequestBody RegisterRequest request) throws Throwable {
        return ResponseEntity.ok().body(service.studentRequestBed(request));
    }
    @GetMapping("/studentNames/{hostelName}")
    public ResponseEntity<List<String>> retrieveStudentNames(@PathVariable HostelName hostelName){
        return ResponseEntity.ok().body(service.studentsNameByHostelName(hostelName));
    }
    @GetMapping("/students/room/{roomId}")
    public ResponseEntity<List<Student>> retrieveStudents(@PathVariable Integer roomId) throws Throwable {
        return ResponseEntity.ok().body(service.studentsByRoomId(roomId));
    }
}
