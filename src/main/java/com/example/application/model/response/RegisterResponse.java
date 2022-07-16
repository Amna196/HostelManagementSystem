package com.example.application.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Amna
 * @created 7/14/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponse {
    private Integer studentId;
    private Integer roomId;
    private Integer bedspaceId;
    private Integer hostelId;
}