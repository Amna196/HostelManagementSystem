package com.example.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Amna
 * @created 7/13/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    private Integer studentId;
    private Integer roomId;
    private Integer bedspaceId;
    private Integer hostelId;
}
