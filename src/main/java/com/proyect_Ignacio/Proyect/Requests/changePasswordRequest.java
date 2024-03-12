package com.proyect_Ignacio.Proyect.Requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class changePasswordRequest {
    String currentPassword;
    String newPassword;
    String validatePassword;
}
