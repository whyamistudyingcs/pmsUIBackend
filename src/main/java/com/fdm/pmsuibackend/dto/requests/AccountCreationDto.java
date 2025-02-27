package com.fdm.pmsuibackend.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountCreationDto {
    @NotBlank(message = "Account name is mandatory")
    @Size(min = 3, max = 50, message = "Account name must be between 3 and 50 characters")
    private String accountName;

    @NotBlank(message = "Department name is mandatory")
    @Size(min = 3, max = 50, message = "Department name must be between 3 and 50 characters")
    private String departmentName;
}
