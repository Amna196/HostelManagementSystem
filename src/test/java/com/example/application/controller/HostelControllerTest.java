package com.example.application.controller;

import com.example.application.entity.Student;
import com.example.application.enums.Gender;
import com.example.application.exception.HostelIsFullException;
import com.example.application.exception.HostelNotValidException;
import com.example.application.exception.RoomIsFullException;
import com.example.application.service.HostelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Amna
 * @created 7/15/2022
 */
@WebMvcTest(HostelController.class)
class HostelControllerTest {
    @MockBean
    private HostelService hostelService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addStudent_successfulRequest_ReturnStudentResponseObject() throws Throwable {
        Student request = new Student(1,"Amna", Gender.FEMALE,"99999999","Amna@gemail.com");
        when(hostelService.registerStudentInSystem(request)).thenReturn(request);
        mockMvc.perform(post("http://localhost:8082/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\":\"Amna\",\n" +
                        "    \"gender\":\"FEMALE\",\n" +
                        "    \"phoneNumber\":\"12121212\",\n" +
                        "    \"email\":\"test@gemail.com\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void addStudent_withoutEmailValue_ReturnNullPointerException() throws Throwable {
        when(hostelService.registerStudentInSystem(any())).thenThrow(NullPointerException.class);
        mockMvc.perform(post("http://localhost:8082/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addStudent_withDublicateEmailValue_ReturnDuplicateKeyException() throws Throwable {
        when(hostelService.registerStudentInSystem(any())).thenThrow(DuplicateKeyException.class);
        mockMvc.perform(post("http://localhost:8082/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Aleen\",\n" +
                                "    \"gender\":\"FEMALE\",\n" +
                                "    \"phoneNumber\":\"12121212\",\n" +
                                "    \"email\":\"test@gemail.com\"\n" +
                                "}"))
                .andExpect(status().isFound());
    }

    @Test
    void requestABed_successfulRequest_ReturnRequestResponseObject() throws Throwable {
        when(hostelService.studentRequestBed(any())).thenReturn(any());
        mockMvc.perform(post("http://localhost:8082/request/bed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"studentId\":1,\n" +
                                "    \"roomId\":1,\n" +
                                "    \"bedspaceId\":1,\n" +
                                "    \"hostelId\":1\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void requestABed_withoutHostelId_ReturnNullPointerException() throws Throwable {
        when(hostelService.studentRequestBed(any())).thenThrow(NullPointerException.class);
        mockMvc.perform(post("http://localhost:8082/request/bed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"studentId\":1,\n" +
                                "    \"roomId\":1,\n" +
                                "    \"bedspaceId\":1,\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void requestABed_withInValidHostelId_ReturnNoSuchElementException() throws Throwable {
        when(hostelService.studentRequestBed(any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(post("http://localhost:8082/request/bed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"studentId\":1,\n" +
                                "    \"roomId\":1,\n" +
                                "    \"bedspaceId\":1,\n" +
                                "    \"hostelId\":12\n" +
                                "}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void requestABed_withInValidHostelCategory_ReturnHostelNotValidException() throws Throwable {
        when(hostelService.studentRequestBed(any())).thenThrow(HostelNotValidException.class);
        mockMvc.perform(post("http://localhost:8082/request/bed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"studentId\":1,\n" +
                                "    \"roomId\":1,\n" +
                                "    \"bedspaceId\":1,\n" +
                                "    \"hostelId\":3\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void requestABed_InFullHostel_ReturnHostelIsFullException() throws Throwable {
        when(hostelService.studentRequestBed(any())).thenThrow(HostelIsFullException.class);
        mockMvc.perform(post("http://localhost:8082/request/bed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"studentId\":1,\n" +
                                "    \"roomId\":1,\n" +
                                "    \"bedspaceId\":1,\n" +
                                "    \"hostelId\":3\n" +
                                "}"))
                .andExpect(status().isConflict());
    }

    @Test
    void requestABed_InFullRoom_ReturnRoomIsFullException() throws Throwable {
        when(hostelService.studentRequestBed(any())).thenThrow(RoomIsFullException.class);
        mockMvc.perform(post("http://localhost:8082/request/bed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"studentId\":1,\n" +
                                "    \"roomId\":1,\n" +
                                "    \"bedspaceId\":1,\n" +
                                "    \"hostelId\":3\n" +
                                "}"))
                .andExpect(status().isConflict());
    }

    @Test
    void retrieveStudentNames_successfulHostelName_ReturnListOfStudentNames() throws Throwable {
        when(hostelService.studentsNameByHostelName(any())).thenReturn(any());
        mockMvc.perform(get("http://localhost:8082/studentNames/{hostelName}","red")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void retrieveStudentNames_InValidHostelName_ReturnHostelNotValidException() throws Throwable {
        when(hostelService.studentsNameByHostelName(any())).thenThrow(HostelNotValidException.class);
        mockMvc.perform(get("http://localhost:8082/studentNames/{hostelName}","anything")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void retrieveStudents_successfulRoomId_ReturnListOfStudents() throws Throwable {
        when(hostelService.studentsByRoomId(any())).thenReturn(any());
        mockMvc.perform(get("http://localhost:8082/students/room/{roomId}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void retrieveStudents_RoomIdNotValid_ReturnNoSuchElementException() throws Throwable {
        when(hostelService.studentsByRoomId(any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get("http://localhost:8082/students/room/{roomId}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}