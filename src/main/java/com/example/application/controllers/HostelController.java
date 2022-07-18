package com.example.application.controllers;

import com.example.application.entities.Student;
import com.example.application.enums.HostelName;
import com.example.application.models.requests.RegisterRequest;
import com.example.application.models.responses.RegisterResponse;
import com.example.application.services.HostelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Hostel Management System APIs")
public class HostelController {
    private final HostelService service;

    /**
     * It takes a student, creates a student record
     *
     * @param student The request object that will be used to create the student record.
     * @return A response entity with a success response containing a student record.
     */
    @ApiImplicitParam(name = "student", value = "The request object that will be used to create the student record.", dataTypeClass = Student.class)
    @ApiOperation(value = "register new student", tags = {"Hostel Management System APIs"}, httpMethod = "POST")
    @PostMapping("/register")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws Throwable {
        return ResponseEntity.ok().body(service.registerStudentInSystem(student));
    }

    /**
     * It takes a request, creates a registration record
     *
     * @param request The request object that will be used to create the register record.
     * @return A response entity with a success response containing a register response.
     */
    @ApiImplicitParam(name = "request", value = "The request object that will be used to create the register record.", dataTypeClass = RegisterRequest.class)
    @ApiOperation(value = "student request a bed", tags = {"Hostel Management System APIs"}, httpMethod = "POST")
    @PostMapping("/request/bed")
    public ResponseEntity<RegisterResponse> requestABed(@RequestBody RegisterRequest request) throws Throwable {
        return ResponseEntity.ok().body(service.studentRequestBed(request));
    }

    /**
     * It takes a hostelName, retrieve list of student names
     *
     * @param hostelName The string that will be used to retrieve list of student names registered in the provided hostel.
     * @return A response entity with a success response containing list of student names.
     */
    @ApiImplicitParam(name = "hostelName", value = "The string that will be used to retrieve list of student names registered in the provided hostel.", dataTypeClass = HostelName.class)
    @ApiOperation(value = "retrieve list of student names upon hostel name", tags = {"Hostel Management System APIs"}, httpMethod = "GET")
    @GetMapping("/studentNames/{hostelName}")
    public ResponseEntity<List<String>> retrieveStudentNames(@PathVariable HostelName hostelName){
        return ResponseEntity.ok().body(service.studentsNameByHostelName(hostelName));
    }

    /**
     * It takes a roomId, retrieve list of student objects registered in the provided roomId.
     *
     * @param roomId The string that will be used to retrieve list of student names registered in the provided hostel.
     * @return A response entity with a success response containing list of student objects.
     */
    @ApiImplicitParam(name = "roomId", value = "It takes a roomId, retrieve list of student objects registered in the provided roomId.", dataTypeClass = Integer.class)
    @ApiOperation(value = "retrieve list of student objects upon roomId", tags = {"Hostel Management System APIs"}, httpMethod = "GET")
    @GetMapping("/students/room/{roomId}")
    public ResponseEntity<List<Student>> retrieveStudents(@PathVariable Integer roomId) throws Throwable {
        return ResponseEntity.ok().body(service.studentsByRoomId(roomId));
    }
}
